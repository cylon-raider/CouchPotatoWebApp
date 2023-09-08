package com.gcu.CouchPotatoWebApp.controller;

import com.gcu.CouchPotatoWebApp.business.CategoryBusinessService;
import com.gcu.CouchPotatoWebApp.business.ProductBusinessService;
import com.gcu.CouchPotatoWebApp.business.UserBusinessService;
import com.gcu.CouchPotatoWebApp.model.ProductModel;
import com.gcu.CouchPotatoWebApp.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * ProductController is responsible for handling web requests related to product functionalities.
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private UserBusinessService userBusinessService;

    @Autowired
    private ProductBusinessService productBusinessService;

    @Autowired
    private CategoryBusinessService categoryBusinessService;

    /**
     * Display the product page with a list of all products.
     *
     * @param user The authenticated user.
     * @return ModelAndView containing the product view and related data.
     */
    @GetMapping("/")
    public ModelAndView display(Principal user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "Product Page");
        modelAndView.addObject("products", productBusinessService.getAll());
        UserModel activeUser = userBusinessService.getUserAuthority(user.getName());
        boolean isAdmin = activeUser.isActive() && activeUser.getRoleId() == 1;
        modelAndView.addObject("isAdmin", isAdmin);
        modelAndView.setViewName("products");
        return modelAndView;
    }

    /**
     * Display the add product page.
     *
     * @return ModelAndView containing the add product view and related data.
     */
    @GetMapping("/add")
    public ModelAndView displayAddProducts(){
        ModelAndView modelAndView = new ModelAndView();
        ProductModel productModel = new ProductModel();
        modelAndView.addObject("title", "Add Product Page");
        modelAndView.addObject("productModel", productModel);
        modelAndView.addObject("categories", categoryBusinessService.getAllCategories());
        modelAndView.setViewName("addProduct");
        return modelAndView;
    }

    /**
     * Display the edit product page.
     *
     * @return ModelAndView containing the edit product view and related data.
     */
    @GetMapping("/update")
    public ModelAndView displayEditProducts(){
        ModelAndView modelAndView = new ModelAndView();
        ProductModel productModel = new ProductModel();
        modelAndView.addObject("title", "Edit Product Page");
        modelAndView.addObject("productModel", productModel);
        modelAndView.addObject("categories", categoryBusinessService.getAllCategories());
        modelAndView.setViewName("updateProduct");
        return modelAndView;
    }

    /**
     * Display the delete product page.
     *
     * @return ModelAndView containing the delete product view and related data.
     */
    @GetMapping("/delete")
    public ModelAndView displayDeleteProducts(){
        ModelAndView modelAndView = new ModelAndView();
        ProductModel productModel = new ProductModel();
        modelAndView.addObject("title", "Delete Product Page");
        modelAndView.addObject("productModel", productModel);
        modelAndView.setViewName("deleteProduct");
        return modelAndView;
    }

    /**
     * Handle the addition of a new product.
     *
     * @param productModel The product data.
     * @param productCategory The category of the product.
     * @param bindingResult Validation results.
     * @param model The model to add attributes.
     * @param user The authenticated user.
     * @return ModelAndView containing the appropriate view based on the operation result.
     */
    @PostMapping("/addProduct")
    public ModelAndView addProduct(@Valid ProductModel productModel, String productCategory, BindingResult bindingResult, Model model, Principal user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addProduct");
        productModel.setProductCategory(productCategory);
        if(bindingResult.hasErrors()){
            model.addAttribute("title", "Add Product Page");
            return modelAndView;
        }
        if(productBusinessService.addProduct(productModel)){
            return this.display(user);
        }else {
            return modelAndView;
        }
    }

    /**
     * Handle the update of an existing product.
     *
     * @param productModel The updated product data.
     * @param productCategory The category of the product.
     * @param bindingResult Validation results.
     * @param model The model to add attributes.
     * @param user The authenticated user.
     * @return ModelAndView containing the appropriate view based on the operation result.
     */
    @PostMapping("/updateProduct")
    public ModelAndView updateProduct(@Valid ProductModel productModel, String productCategory, BindingResult bindingResult, Model model, Principal user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("updateProduct");
        productModel.setProductCategory(productCategory);
        if(bindingResult.hasErrors()){
            model.addAttribute("title", "Add Product Page");
            return modelAndView;
        }
        if(productBusinessService.updateProduct(productModel)){
            return this.display(user);
        }else {
            return modelAndView;
        }
    }

    /**
     * Handle the deletion of a product.
     *
     * @param productModel The product to be deleted.
     * @param user The authenticated user.
     * @return ModelAndView containing the appropriate view based on the operation result.
     */
    @PostMapping("/deleteProduct")
    public ModelAndView deleteProduct(ProductModel productModel, Principal user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("deleteProduct");

        if(productBusinessService.deleteProduct(productModel))
        {
            return this.display(user);
        } else {
            return modelAndView;
        }
    }

    /**
     * Display the search results for products based on a query.
     *
     * @param q The search query.
     * @param model The model to add attributes.
     * @param user The authenticated user.
     * @return ModelAndView containing the search results view and related data.
     */
    @GetMapping("/search")
    public ModelAndView showSearchForm(@Valid String q, Model model, Principal user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");
        List<ProductModel> products = productBusinessService.findByNameContainingIgnoreCase(q);
        UserModel activeUser = userBusinessService.getUserAuthority(user.getName());
        boolean isAdmin = activeUser.isActive() && activeUser.getRoleId() == 1;
        modelAndView.addObject("isAdmin", isAdmin);
        model.addAttribute("title", "Product Search");
        model.addAttribute("products", products);
        return modelAndView;
    }
}
