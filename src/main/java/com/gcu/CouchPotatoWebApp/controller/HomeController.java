package com.gcu.CouchPotatoWebApp.controller;

import com.gcu.CouchPotatoWebApp.business.ProductBusinessService;
import com.gcu.CouchPotatoWebApp.business.UserBusinessService;
import com.gcu.CouchPotatoWebApp.model.ProductModel;
import com.gcu.CouchPotatoWebApp.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

/**
 * HomeController is responsible for handling web requests related to the home page.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserBusinessService userBusinessService;

    @Autowired
    private ProductBusinessService productBusinessService;

    /**
     * Display the index page with a list of products.
     * Determines if the logged-in user is an admin and passes this information to the view.
     *
     * @param user The authenticated user.
     * @return ModelAndView containing the index view and related data.
     */
    @GetMapping("/index")
    public ModelAndView index(Principal user) {
        ModelAndView modelAndView = new ModelAndView();

        // Get the authority of the logged-in user to determine if they are an admin.
        UserModel activeUser = userBusinessService.getUserAuthority(user.getName());
        boolean isAdmin = activeUser.isActive() && activeUser.getRoleId() == 1;
        modelAndView.addObject("isAdmin", isAdmin);

        // Fetch all products to display on the index page.
        List<ProductModel> products = productBusinessService.getAll();
        modelAndView.addObject("products", products);

        // Set the view name to "index".
        modelAndView.setViewName("index");

        return modelAndView;
    }
}
