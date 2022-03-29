package com.example.CinemaEBooking.model.customer;

import javax.validation.*;

import com.example.CinemaEBooking.model.address.Address;
import com.example.CinemaEBooking.model.address.AddressRepository;
import com.example.CinemaEBooking.model.payment_method.PaymentMethod;
import com.example.CinemaEBooking.model.payment_method.PaymentMethodRepository;

import org.springframework.stereotype.*;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.beans.factory.annotation.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.*;

/**
 * This class represents the controller for the registration form. It handles
 * validation by setting its Validator to the RegistrationValidator via
 * initBinder.  
 */
@Controller
public class RegistrationController implements WebMvcConfigurer {

    @Autowired 
    AddressRepository addressRepository;

    @Autowired
    PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private CustomerService customerService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/registration").setViewName("registration");
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new RegistrationValidator());
    }

    @GetMapping("/registration")
    public String showEditProfile(RegistrationForm registrationForm) {
        return "registration";
    }

    @PostMapping("/registration")
    public String checkEditProfile(@Valid RegistrationForm form,
            BindingResult bindingResult, String URL, HttpServletRequest request) throws Exception{

        if (customerService.userExists(form.getEmail())) {
            bindingResult.rejectValue("email", "field.duplicate");
        }
        if (bindingResult.hasErrors()) {
            
            // Returning this page fills out the error fields in the template
            // with the bindingResult error values.
            return "registration";
        }
       
        Customer customer = new Customer();
        customer.setFirstName(form.getFirstName());
        customer.setLastName(form.getLastName());
        customer.setPhoneNumber(form.getPhoneNumber());
        customer.setEmail(form.getEmail());
        customer.setEncryptedPassword(form.getPassword());
        // If form is valid and one address field was filled out, it means all
        // of them were. 
        if (!form.getStreetAddress().isBlank()) {
            Address address = new Address();
            address.setStreetAddress(form.getStreetAddress());
            address.setCity(form.getCity());
            address.setState(form.getState());
            address.setZipCode(form.getZipCode());
            addressRepository.save(address);
            customer.setAddress(address);
        } 
        customerService.register(customer, customerService.getSiteURL(request));

        // If form is valid and one CC field was filled out, it means all of
        // them were.
        if (!form.getCardNumber().isBlank()) {
            // Billing Address
            Address address = new Address();
            address.setStreetAddress(form.getBillingStreetAddress());
            address.setCity(form.getBillingCity());
            address.setState(form.getBillingState());
            address.setZipCode(form.getBillingZipCode());
            addressRepository.save(address);

            // PaymentMethod
            PaymentMethod paymentMethod = new PaymentMethod();
            paymentMethod.setEncryptedNumber(form.getCardNumber());
            paymentMethod.setCcv(form.getCcv());
            paymentMethod.setExpirationDateString(form.getExpirationDateString());
            paymentMethod.setEndingDigit();
            paymentMethod.setAddress(address);
            paymentMethod.setCustomer(customer);
            paymentMethodRepository.save(paymentMethod);
        }

        // Use "redirect:/[page name] so that it does not get just return the
        // page, but rather goes to the actual URL and maintains the logical
        // flow of events.
        return "redirect:/registrationConfirmation";
    }
}
