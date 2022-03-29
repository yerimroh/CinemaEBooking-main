package com.example.CinemaEBooking.model.customer;

import org.springframework.validation.*;

public class EditProfileValidator implements Validator {

    public boolean supports(Class clazz) {
        return EditProfileForm.class.equals(clazz);
    }

    public void validate(Object obj, Errors e) {

        EditProfileForm form = (EditProfileForm) obj; 

        // All non-address fields are required.
        ValidationUtils.rejectIfEmptyOrWhitespace(
                e, "firstName", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(
                e, "lastName", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(
                e, "phoneNumber", "field.required");

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
    }
}
