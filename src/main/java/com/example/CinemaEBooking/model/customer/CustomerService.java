package com.example.CinemaEBooking.model.customer;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import com.example.CinemaEBooking.model.address.Address;
import com.example.CinemaEBooking.model.address.AddressRepository;
import com.example.CinemaEBooking.model.payment_method.PaymentMethod;
import com.example.CinemaEBooking.model.payment_method.PaymentMethodRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;


import net.bytebuddy.utility.RandomString;

@Service
public class CustomerService {
    @Autowired
	private CustomerRepository customerRepository;

	@Autowired
    private AddressRepository addressRepository;

	@Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private JavaMailSender mailSender;

    // process register
    public void register(Customer customer, String URL) throws Exception{
		// encrypt password to the db
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(customer.getEncryptedPassword());
        customer.setEncryptedPassword(encodedPassword);
        
        // check if the user email is already taken / print an error message if it is 
        // generate random verification code
        String randomCode = RandomString.make(64);
        customer.setVerificationCode(randomCode);
        
        // save address only if all fields are filled
        // is not filled all  or  is not all empty 
        customerRepository.save(customer); // add the customer to the db

        // send verification email
        sendVerificationEmail(customer.getEmail(), customer.getFirstName(), customer.getVerificationCode(), URL);   
    }



    
    public void updateProfile(Customer customer) throws Exception {
        Customer dbCustomer = getCurrentUser();
        //customerRepository.findByEmail(getCurrentUser().getEmail());
		dbCustomer.setPhoneNumber(customer.getPhoneNumber());
		dbCustomer.setFirstName(customer.getFirstName());
		dbCustomer.setLastName(customer.getLastName());
        dbCustomer.setSubscribed(customer.getSubscribed());

        Address address;
        if(addressRepository.findByCustomerId(dbCustomer.getId()) != null) { // if db user already has a address associated to him/her
            address = addressRepository.findByCustomerId(dbCustomer.getId()); // get current user's address
            address.setStreetAddress(customer.getAddress().getStreetAddress());
            address.setCity(customer.getAddress().getCity());
            address.setState(customer.getAddress().getState());
            address.setZipCode(customer.getAddress().getZipCode());
        } else { // if db user doesn't have a address associated to him/her
            if (customer.getAddress().isEmpty()) { // if user decides to remove home address
                addressRepository.deleteById(dbCustomer.getAddress().getId()); // delete this address from db
            } // if
            address = new Address(
                customer.getAddress().getStreetAddress(), 
                customer.getAddress().getZipCode(), 
                customer.getAddress().getCity(),
                customer.getAddress().getState());
        } // if-else

        // save address only if all its fields are filled out
        if(address.isAddress()) {
            addressRepository.save(address);
            dbCustomer.setAddress(address);
        } else if(address.isEmpty()) {
            dbCustomer.setAddress(null); // if none of the field is filled out, consider as if the address does not exist                
        } else {
            throw new Exception(); // if only some of the fiels is filled 
        } // if-else
        
        // save the customer regardless 
		customerRepository.save(dbCustomer);

		try {
			sendEmail(dbCustomer.getEmail(), dbCustomer.getFirstName(), "Profile has been updated ", "Your profile has been updated.");
		} catch (Exception e) {
			System.out.println("Error occurred while sending email");
		} // try-catch
    }


    public void addCard(PaymentMethod paymentMethod) throws Exception {
         // generate a new card object based on the input fields

         if(!userCanAddMoreCard()) {
            throw new Exception();
         }

        PaymentMethod dbCard = new PaymentMethod
        (paymentMethod.getUnencryptedNumber(), 
        paymentMethod.getExpirationDateString(), 
        paymentMethod.getCcv());

		// generate a new address object based on the input fields
        Address address = paymentMethod.getAddress();
        addressRepository.save(address); // save address in the db first 
		dbCard.setAddress(address); // map the addres to this card 
        dbCard.setCustomer(getCurrentUser()); // get current user

        paymentMethodRepository.save(dbCard); // save this card to the db
    }


    
    public void deleteCard(Long paymentMethodId) throws Exception {
		paymentMethodRepository.deleteById(paymentMethodId);
    }


     

    // verify account: change a user account into ACTIVE status 
    public boolean verify(String verificationCode) {
        Customer customer = customerRepository.findByVerificationCode(verificationCode);

        // if this user doesn't exist in the db or is already at active status
        if(customer == null || customer.isVerified()) {
            return false;
        } else {
            customer.setVerificationCode(null);
            customer.setStatus(CustomerStatus.ACTIVE);

            customerRepository.save(customer);
            return true;
        }
    } // verify


    


    // send verification email
    private void sendVerificationEmail(String recipientEmail, String recipientName, String verificationCode, String siteURL)
        throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
    
        String content = "Hello, " + "<b>" + recipientName + "</b>,<br>"
                + "Click the link below to verify your registration!<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">Verify Account</a></h3>";
                
        String verifyURL = siteURL + "/verify?code=" + verificationCode;
        content = content.replace("[[URL]]", verifyURL);
        
        helper.setFrom(new InternetAddress("dreammorelikedream@gmail.com"," Cinema E-Booking "));
        helper.setTo(recipientEmail);
        helper.setSubject("Cinema E-Booking: Verify account");
        helper.setText(content, true);
        
        mailSender.send(message);
    } // sendVerificationEmail



    // process reset password
    public void processResetPassword(String email, String URL, Model model) throws Exception {
        String token = RandomString.make(20);
        
        updateResetPasswordToken(token, email);
        String resetPasswordLink = URL + "/new_password?token=" + token;

        String messageContent = "<p>Click the link below to reset your password.</p>"
                                + "<p> <a href = \"" + resetPasswordLink + "\">" + " Reset Password </a> </p>";

        sendEmail(email, "Hello", "Reset Password Request ", messageContent); // send this email
            
    } // processResetPassword



    // generate new resetpasswordtoken and save it to the db
    public void updateResetPasswordToken(String token, String email) throws Exception {
        Customer customer = customerRepository.findByEmail(email);

        if(customer == null) { // customer with this email does not exist
            throw new Exception("Customer with email " + email + " does not exist.");
        } else { // customer exists 
            customer.setResetPasswordToken(token); // modify the token value for the customer with this email
            customerRepository.save(customer); // update db
        }
    } //updateResetPasswordToken



    // update password
    public void updatePassword(Customer customer, String newPassword) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);

        customer.setEncryptedPassword(encodedPassword); // modify the password value for this customer 
        customer.setResetPasswordToken(null); // password token reset to null / generated newly when the user forgets password again
        customerRepository.save(customer);
    } // updatePassword
    


    // get current password 
    
    public boolean passwordMatches(String input) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();  
        return encoder.matches(input, getCurrentUser().getEncryptedPassword());  
    }





    /*
    * helper methods  
    */
    // based on the session get the current user (Customer object)
    public Customer getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		UserDetails userDetails = (UserDetails)principal; 

        return customerRepository.findByEmail(userDetails.getUsername());
    }

    


	// send email    
	// throws exception to the caller method if there occurs any error  
    public void sendEmail(String recipientEmail, String recipientName, String subject, String text) throws Exception {

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		boolean html = true;

		helper.setFrom(new InternetAddress("dreammorelikedream@gmail.com"," Cinema E-Booking "));
		helper.setTo(recipientEmail);	
		helper.setSubject(subject);
		helper.setText("<b>"+ recipientName +"</b>, <br><i>" + text + "</i>", html);
		
		mailSender.send(message);
    } // sendEmail


    
    // get this site url
	public String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }  


    // check if this user Exists
	public boolean userExists(String email) {
		return customerRepository.findByEmail(email) !=null ? true : false;
	}

    // check if user has any cards
    public boolean userHasAnycards() {
        List<PaymentMethod> paymentMethods = getCardLists(); 

        if(paymentMethods.size() == 0 || paymentMethods == null) {
            return false; // no payment
        } else {
            return true;
        }
    }

    public boolean userCanAddMoreCard() {
        List<PaymentMethod> paymentMethods = getCardLists();

        return (paymentMethods.size() < 3);
    }


    // get the user's card lists 
    public List<PaymentMethod> getCardLists() {
        Customer customer = getCurrentUser();
        
        //customerRepository.findByEmail(getCurrentUser().getEmail()); // get the current user
		List<PaymentMethod> paymentMethods = paymentMethodRepository.getPaymentMethodsByCustomer(customer); // get this user's paymentmethods

        return paymentMethods;
    }




	
}
