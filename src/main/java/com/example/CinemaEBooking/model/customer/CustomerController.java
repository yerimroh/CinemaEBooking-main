package com.example.CinemaEBooking.model.customer;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import com.example.CinemaEBooking.model.address.AddressRepository;
import com.example.CinemaEBooking.model.payment_method.PaymentMethod;
import com.example.CinemaEBooking.model.payment_method.PaymentMethodRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.bytebuddy.utility.RandomString;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/* 
* This class dedicated to pages with /user/ prefix
*/
@Controller
public class CustomerController {
	/*
	* Repos
	*/ 
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
    private AddressRepository addressRepository;

	@Autowired
    private PaymentMethodRepository paymentMethodRepository;

	@Autowired
    private CustomerService customerService;





	@RequestMapping("/user/home")
	public String getLoggedHome() {
		return "LoggedIn_HomePage";
	}

	// Edit Credit Card Info
	@GetMapping("/user/edit_profile_CC")
	public String editCreditCardInfo(Model model) {

        Customer customer = customerService.getCurrentUser(); // get the current user
		List<PaymentMethod> paymentMethods = paymentMethodRepository.getPaymentMethodsByCustomer(customer); // get this user's paymentmethods

		if(paymentMethods.size() == 0 || paymentMethods == null) {
			model.addAttribute("existingCards", new ArrayList<PaymentMethod>()); // if this user has no card saved in
		} else {
			model.addAttribute("existingCards", paymentMethods); // if this user has some cards saved in
		} // if-else
		model.addAttribute("paymentMethod", new PaymentMethod()); // if this user does not have a payment method
		return "edit_profile_addCC";
    }

   	// add later on
    @PostMapping("/user/addPayment")
    public String addPaymentMethod(PaymentMethod paymentMethod, Model model) {
		try {
			customerService.addCard(paymentMethod);
			model.addAttribute("message", "Added a card.");	
		} catch(Exception e) {
			model.addAttribute("error", "Error occurred. (User Cannot add more than 3 paymennt methods.)");
		} // try-catch
		// update view 
		if(customerService.userHasAnycards()) {
			model.addAttribute("existingCards", customerService.getCardLists()); // if this user has some cards saved in
		} else {
			model.addAttribute("existingCards", new ArrayList<PaymentMethod>()); // if this user has no card saved in
		} // if-else

		model.addAttribute("paymentMethod", new PaymentMethod()); // empty the fields
		
		return "edit_profile_addCC";
    }


	// delete a card
	@GetMapping("/user/deleteCard")
	public String deleteCard(@RequestParam Long PaymentMethodId, Model model) {
		try {
			paymentMethodRepository.deleteById(PaymentMethodId);			
		} catch(Exception e) {
			model.addAttribute("error", "Error occurred");
			return "edit_profile_addCC";
		} // try-catch
		
		// update view 
		if(customerService.userHasAnycards()) {
			model.addAttribute("existingCards", customerService.getCardLists()); // if this user has some cards saved in
		} else {
			model.addAttribute("existingCards", new ArrayList<PaymentMethod>()); // if this user has no card saved in
		}
		model.addAttribute("paymentMethod", new PaymentMethod());

		// inform the user
		model.addAttribute("message", "Deleted the card.");
		return "edit_profile_addCC";
	}


	// check current password before changing it
	@GetMapping("/user/current_password")
	public String checkCurrentPasswordPage() {
		return "check_password";
	} // checkCurrentPasswordPage

	@PostMapping("/user/current_password")
	public String checkCurrentPassword(HttpServletRequest request, Model model) {
		String input = request.getParameter("current_password");

		if(customerService.passwordMatches(input)) {
			return "new_password2"; // if input matches then redirect to change password page
		} else {
			model.addAttribute("error", "Password is incorrect.");
			return "check_password"; // if input does not match then let the user know
		} // if-else
	} // checkCurrentPassword




	// get new password to saave to db
	@GetMapping("/user/set_new_password")
	public String saveNewPasswordPage() {
		return "new_password2";
	} //saveNewPasswordPage


	@PostMapping("/user/set_new_password") 
	public String saveNewPassword(HttpServletRequest request, Model model) {
		String input = request.getParameter("new_password");

		try {
			customerService.updatePassword(customerService.getCurrentUser(), input);
		} catch (Exception e) {
			model.addAttribute("error", "An error occurred while updating password.");
		}

		model.addAttribute("message", "Password updated successfully.");
		return "new_password2";
	} // saveNewPassword






	@GetMapping(path="/viewCustomers")
	public @ResponseBody Iterable<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
	
	@GetMapping(path="/viewCustomer")
	public @ResponseBody Customer getCustomer(@RequestParam long id) {
		return customerRepository.findById(id);
	}

	
}
