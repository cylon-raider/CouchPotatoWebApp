package com.gcu.CouchPotatoWebApp.controller;

import com.gcu.CouchPotatoWebApp.business.UserBusinessService;
import com.gcu.CouchPotatoWebApp.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



/**
 * RegistrationController is responsible for handling web requests related to user registration.
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserBusinessService userBusinessService;

    /**
     * Display the registration form.
     *
     * @return ModelAndView containing the registration view and related data.
     */
    @GetMapping("/")
    public ModelAndView display() {
        ModelAndView modelAndView = new ModelAndView();
        UserModel userModel = new UserModel();
        modelAndView.addObject("title", "Registration Form");
        modelAndView.addObject("userModel", userModel);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    /**
     * Handle the registration of a new user.
     *
     * @param userModel The user data.
     * @param bindingResult Validation results.
     * @param model The model to add attributes.
     * @return String indicating the appropriate view based on the operation result.
     */
    @PostMapping("/register")
    public String register(UserModel userModel, BindingResult bindingResult, Model model) {
        // Check validation errors
        ModelAndView modelAndView = new ModelAndView();
        if(bindingResult.hasErrors())
        {
            modelAndView.addObject("title", "Registration Form");
            return "registration";
        }
        if (userBusinessService.createUser(userModel)){
            return "login";
        }else {
            return "registration";
        }
    }
}
