package com.gcu.CouchPotatoWebApp.controller;

import com.gcu.CouchPotatoWebApp.business.CartBusinessService;
import com.gcu.CouchPotatoWebApp.business.UserBusinessService;
import com.gcu.CouchPotatoWebApp.model.CartItem;
import com.gcu.CouchPotatoWebApp.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private UserBusinessService userBusinessService;

    @Autowired
    private CartBusinessService cartBusinessService;

    /**
     * Display the cart page with all items in the cart.
     *
     * @param user The authenticated user.
     * @return ModelAndView containing the cart view and related data.
     */
    @GetMapping("/")
    public ModelAndView display(Principal user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "Cart Page");
        List<CartItem> items = cartBusinessService.getCartItemsByUsername(user.getName());
        modelAndView.addObject("items", items);
        UserModel activeUser = userBusinessService.getUserAuthority(user.getName());
        boolean isAdmin = activeUser.isActive() && activeUser.getRoleId() == 1;
        modelAndView.addObject("isAdmin", isAdmin);
        modelAndView.setViewName("cart");
        return modelAndView;
    }

    /**
     * Add an item to the cart.
     *
     * @param productId The ID of the product to add.
     * @param user      The authenticated user.
     * @return RedirectView to the cart page.
     */
    @GetMapping("/addItem")
    public RedirectView addItemToCart(@RequestParam int productId, Principal user) {
        try {
            cartBusinessService.addItem(user.getName(), productId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RedirectView("/cart/");
    }

    /**
     * Delete an item from the cart.
     *
     * @param productId The ID of the product to delete.
     * @param user      The authenticated user.
     * @return RedirectView to the cart page.
     */
    @GetMapping("/deleteItem")
    public RedirectView deleteItem(@RequestParam int productId, Principal user) {
        try {
            cartBusinessService.deleteItem(user.getName(), productId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RedirectView("/cart/");
    }

    /**
     * Decrement the quantity of an item in the cart.
     *
     * @param productId The ID of the product to decrement.
     * @param itemQty   The current quantity of the item.
     * @param user      The authenticated user.
     * @return RedirectView to the cart page.
     */
    @GetMapping("/decrementItem")
    public RedirectView decrementItem(@RequestParam int productId, @RequestParam int itemQty, Principal user) {
        try {
            if (itemQty - 1 == 0) {
                cartBusinessService.deleteItem(user.getName(), productId);
            } else {
                cartBusinessService.updateItem(user.getName(), productId, itemQty - 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RedirectView("/cart/");
    }

    /**
     * Increment the quantity of an item in the cart.
     *
     * @param productId The ID of the product to increment.
     * @param itemQty   The current quantity of the item.
     * @param user      The authenticated user.
     * @return RedirectView to the cart page.
     */
    @GetMapping("/incrementItem")
    public RedirectView incrementItem(@RequestParam int productId, @RequestParam int itemQty, Principal user) {
        try {
            cartBusinessService.updateItem(user.getName(), productId, itemQty + 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RedirectView("/cart/");
    }
}
