package com.example.CinemaEBooking.model.customer;

import org.springframework.validation.*;

public class RegistrationValidator implements Validator {

    public boolean supports(Class clazz) {
        return RegistrationForm.class.equals(clazz);
    }

    public void validate(Object obj, Errors e) {

        RegistrationForm form = (RegistrationForm) obj; 

        // All non-address, non-payment-method fields are required.
        ValidationUtils.rejectIfEmptyOrWhitespace(
                e, "firstName", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(
                e, "lastName", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(
                e, "phoneNumber", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(
                e, "email", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(
                e, "password", "field.required");

        // Address must either be specified completely or not at all.
        // str.isBlank() checks if it is either empty or whitespace.

        boolean streetAddressProvided = !form.getStreetAddress().isBlank();
        boolean cityProvided = !form.getCity().isBlank();
        boolean stateProvided = !form.getState().isBlank();
        boolean zipCodeProvided = !form.getZipCode().isBlank();
        if ((streetAddressProvided && cityProvided && stateProvided && 
                zipCodeProvided) || !(streetAddressProvided || cityProvided ||
                stateProvided || zipCodeProvided)) {
            // Do nothing
        }
        else {
            if (!streetAddressProvided) {
                e.rejectValue("streetAddress", "field.required");
            }
            if (!cityProvided) {
                e.rejectValue("city", "field.required");
            }
            if (!stateProvided) {
                e.rejectValue("state", "field.required");
            }
            if (!zipCodeProvided) {
                e.rejectValue("zipCode", "field.required");
            }
        }

        boolean cardNumberProvided = !form.getCardNumber().isBlank();
        boolean expirationDateStringProvided =
            !form.getExpirationDateString().isBlank();
        boolean ccvProvided = !form.getCcv().isBlank();
        boolean billingStreetAddressProvided =
            !form.getBillingStreetAddress().isBlank();
        boolean billingCityProvided = !form.getBillingCity().isBlank();
        boolean billingStateProvided = !form.getBillingState().isBlank();
        boolean billingZipCodeProvided = !form.getBillingZipCode().isBlank();

        if ((cardNumberProvided && expirationDateStringProvided &&
                    ccvProvided && billingStreetAddressProvided &&
                    billingCityProvided && billingStateProvided &&
                    billingZipCodeProvided) || 
                !(cardNumberProvided || expirationDateStringProvided ||
                    ccvProvided || billingStreetAddressProvided ||
                    billingCityProvided || billingStateProvided ||
                    billingZipCodeProvided)) {
            // Do nothing.
        }
        else {
            if (!cardNumberProvided) {
                e.rejectValue("cardNumber", "field.required");
            }
            if (!expirationDateStringProvided) {
                e.rejectValue("expirationDateString", "field.required");
            }
            if (!ccvProvided) {
                e.rejectValue("ccv", "field.required");
            }
            if (!billingStreetAddressProvided) {
                e.rejectValue("billingStreetAddress", "field.required");
            }
            if (!billingCityProvided) {
                e.rejectValue("billingCity", "field.required");
            }
            if (!billingStateProvided) {
                e.rejectValue("billingState", "field.required");
            }
            if (!billingZipCodeProvided) {
                e.rejectValue("billingZipCode", "field.required");
            }
        }
    }
}
