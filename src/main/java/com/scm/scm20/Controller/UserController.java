package com.scm.scm20.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    // user dashboard page
    
    @RequestMapping("/dashboard")
    public String userDashboard(){
        return "user/dashboard";
    }

    // user profile page 
    @RequestMapping("profile")
    public String userProfile() {
        return "user/profile";
    }
    // user add contact page 

    // user view contacts page
    
    // user edit contact page 

    // user delete contact page
}
