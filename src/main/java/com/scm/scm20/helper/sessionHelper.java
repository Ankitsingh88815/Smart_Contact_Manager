package com.scm.scm20.helper;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

@Component
public class sessionHelper {

    public static void removeMessage() {
        try{
            System.out.println("Removing session attribute: message");
            HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest()
                .getSession();
            session.removeAttribute("message");
        }catch (Exception e) {
            System.out.println("Error in removing session attribute: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
