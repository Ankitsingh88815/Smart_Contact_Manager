package com.scm.scm20.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.scm20.Entities.UserClient;
import com.scm.scm20.form.UserForm;
import com.scm.scm20.helper.MessageTypes;
import com.scm.scm20.helper.Message;
import com.scm.scm20.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
@RequestMapping()
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "redirect:/home"; // Redirect to home page
    }

    @GetMapping("/home")
    public String home(Model model) {
        System.out.println("This is home page handler");
        model.addAttribute("name", "Substring technology");
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
    public String registerpage(Model model, HttpSession session) {
        Message msg = (Message) session.getAttribute("message");
        if (msg != null) {
            System.out.println("Message is removed from session ");
            model.addAttribute("message", msg);
            session.removeAttribute("message"); // ✅ removes it after first use
        }
        UserForm userForm = new UserForm();
        // System.out.println(userForm);
        model.addAttribute("userForm", userForm);

        return "register";
    }

    @PostMapping("/login")
    public String loginpage() {
        return "login";
    }

    // processing register
    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm,BindingResult bindingResult, HttpSession session, Model model) {
        System.out.println("processing registeration");
        System.out.println(userForm);

        if(bindingResult.hasErrors()) {
            System.out.println("Errors in form submission");
            //model.addAttribute("userForm", userForm);
            return "register"; // Return to the registration page with errors
        }
        // Validate form data
        // save user form
        // save to database
        // make service java file

        // userform --> user

        UserClient user = new UserClient();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("/static/image/man.png");

        userService.saveUser(user);

        System.out.println("user saved");
        Message msg = Message.builder().content("Registration successful").type(MessageTypes.green).build();
        session.setAttribute("message", msg);

        // message "Regestration successful"
        // redirect to login page
        return "redirect:/register";
    }



    
}
