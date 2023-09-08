package com.gcu.CouchPotatoWebApp.controller;

import com.gcu.CouchPotatoWebApp.model.LoginModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * LoginController is responsible for handling web requests related to the login page.
 */
@Controller
public class LoginController {

    /**
     * Display the login page with an initialized LoginModel object.
     *
     * @return ModelAndView containing the login view and related data.
     */
    @GetMapping("/")
    public ModelAndView display() {
        ModelAndView modelAndView = new ModelAndView();

        // Initialize a new LoginModel object for the login form.
        LoginModel loginModel = new LoginModel();

        // Add the title and the LoginModel object to the ModelAndView.
        modelAndView.addObject("title", "Login Form");
        modelAndView.addObject("loginModel", loginModel);

        // Set the view name to "login".
        modelAndView.setViewName("login");

        return modelAndView;
    }
}
