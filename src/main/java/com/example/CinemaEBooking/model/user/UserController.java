package com.example.CinemaEBooking.model.user;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
public class UserController {


    
    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }
    


    
    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }




    // possible roles: admin and customer
    @ModelAttribute("roles")
    public Map<String, Role> roles() {
        Map<String, Role> map = new LinkedHashMap<>();
        map.put("ADMIN", Role.ROLE_ADMIN);
        map.put("CUSTOMER", Role.ROLE_MEMBER);
        return map;
    }
    
    

}
