package com.example.CinemaEBooking.model.securityConfig;

import com.example.CinemaEBooking.model.admin.AdminRepository;
import com.example.CinemaEBooking.model.customer.CustomerRepository;
import com.example.CinemaEBooking.model.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

 
public class CustomUserDetailsService implements UserDetailsService {
 
    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private AdminRepository adminRepo;
     
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = customerRepo.findByEmail(username);
        UserDetails userDetail;
        if(user == null) {
            user = adminRepo.findByEmail(username);
        } else {

        }


        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }


        return new CustomUserDetails(user);
    }
 
}
