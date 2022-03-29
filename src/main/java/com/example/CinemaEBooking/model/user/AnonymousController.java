package com.example.CinemaEBooking.model.user;

import javax.servlet.http.HttpServletRequest;

import com.example.CinemaEBooking.model.address.AddressRepository;
import com.example.CinemaEBooking.model.customer.Customer;
import com.example.CinemaEBooking.model.customer.CustomerRepository;
import com.example.CinemaEBooking.model.customer.CustomerService;
import com.example.CinemaEBooking.model.payment_method.PaymentMethodRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/*
* This class dedicated to non-logged in user
*/
@Controller
public class AnonymousController {
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


	/*
	* Some default pages 
	*/
	@GetMapping("")
    public String viewHomePage() {
        return "index";	
    } // viewHomePage

	@GetMapping("/forbidden")
	public String showForbiddenPage() {
		return "forbidden";
	}
    


	// show registration confirmation page
	@GetMapping("/registrationConfirmation")
	public String showRegistrationConfirmation() {
		return "RegistrationConfirmation";
	} // showRegistrationConfirmation


	/*
	* Account Verification
	*/
	// process verify and return html pages depending on its success or failure
	@GetMapping("/verify")
	public String showVerificationResult(@Param("code") String code) {
		if (customerService.verify(code)) {
			return "verify_success";
		} else {
			return "verify_fail";
		} // if-else
	} // showVerificationResult




	/*
	* reset password
	*/
	@GetMapping("/reset_password")
	public String showResetPasswordForm() {
		return "reset_password";
	} // showResetPasswordForm


	@PostMapping("/reset_password")
	public String processForgotPassword(HttpServletRequest request, Model model) {
		String email = request.getParameter("email");

		try {
			customerService.processResetPassword(email, customerService.getSiteURL(request), model);
			model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
            
		} catch (Exception e) {
			model.addAttribute("error", "Error occurred while sending the reset password");
		}
		return "reset_password";
	} // processResetPassword

	@GetMapping("/new_password")
	public String showNewPasswordForm(@Param(value = "token") String token, Model model) {
		Customer customer = customerRepository.findByResetPasswordToken(token);
		model.addAttribute("token", token);

		if(customer == null) {
			model.addAttribute("error", "Invalid Token");
			return "new_password";
		}
		return "new_password";
	}

	@PostMapping("/new_password")
	public String updateToNewPassword(HttpServletRequest request, Model model) {
		String token = request.getParameter("token");
		String password = request.getParameter("password");

		try {
			customerService.updatePassword(customerRepository.findByResetPasswordToken(token), password);
			model.addAttribute("message", "Changed password!");
		} catch (Exception e) {
			//e.printStackTrace();
			model.addAttribute("error", "Error occurred while changing the password.");
			return "new_password";
		}
		return "login";
	} // updateToNewPassword




	


}
