package com.scm.scm20.Controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.scm20.Entities.UserClient;
import com.scm.scm20.form.UserForm;
import com.scm.scm20.helper.MessageTypes;
import com.scm.scm20.helper.message;
import com.scm.scm20.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping()
public class PageController {

    @Autowired 
    private UserService userService;

    @GetMapping("/home")
    public String home(Model model) {
        System.out.println("This is home page handler");
        model.addAttribute("name", "Substring technology");
        model.addAttribute("Youtube_chennel", "Learn code with durgesh");
        model.addAttribute("github", "https://github.com/ankitsingh88815/webproject");
        return "home";
    }

    @GetMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("islogin", true);
        System.out.println("about page loading ");
        return "about";
    }

    @GetMapping("/services")
    public String servicesPage() {
        System.out.println("services page loading ");
        return "services";
    }

    @GetMapping("/contact")
    public String contactpage() {
        return "contact";
    }

    @GetMapping("/register")
    public String registerpage(Model model) {
        UserForm userForm = new UserForm();
        // System.out.println(userForm);
        model.addAttribute("userForm", userForm);
        return "register";
    }

    @GetMapping("/login")
    public String loginpage() {
        return "login";
    }

    //processing register
    @RequestMapping(value = "/do-register", method= RequestMethod.POST)
    public String processRegister(@ModelAttribute UserForm userForm, HttpSession session){
    System.out.println("processing registeration");
    System.out.println(userForm);

        // Validate form data
        // save user form 
        //save to database 
        // make service java file 

        // userform --> user

        // UserClient user = UserClient.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .profilePic("/static/image/man.png")
        // .build();

        UserClient user = new UserClient();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("/static/image/man.png");

        UserClient saveduser = userService.saveUser(user);

        System.out.println("user saved");
        message msg = message.builder().content("Registration successful").type(MessageTypes.blue).build();
        session.setAttribute("message", msg);
        
        //message "Regestration successful"
        // redirect to login page
    return "redirect:/register";
    }

    // @PostMapping("/do-register")
    // public String registerUser(@ModelAttribute UserForm userForm) {
    //     // registration logic
    //     return "redirect:/login"; // or return some success page
    // }
}
