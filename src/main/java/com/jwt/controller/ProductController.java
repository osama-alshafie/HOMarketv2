package com.jwt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jwt.model.Cart;
import com.jwt.model.CartItem;
import com.jwt.model.Customer;
import com.jwt.model.Product;

@Controller
@RequestMapping(value = "/product")
public class ProductController {
	
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public String Products(ModelAndView model, Model models) {

		// int counter = 0;
		// model.addObject(counter);
		if (productService.getAllProducts() != null) {
			models.addAttribute("products", productService.getAllProducts());
		} else {
			System.out.println("It's Empty");

		}

		return "product";
	}

	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public String Product() {

		return "product";
	}
	
	@RequestMapping(value = "/product-detail/{id}", method = RequestMethod.GET)
	public String ProductDetails(Model model, @PathVariable("id") int id) {
		Product product = productService.getProductById(id);

		model.addAttribute("product", product);

		return "product-detail";
	}

	@RequestMapping(value = "/product-detail/{id}", method = RequestMethod.POST)
	public String ProductDetailsPost(
			/* @ModelAttribute("product") Product product, */ @PathVariable("id") int id, Model model,
			@RequestParam("counter") int counter, HttpServletRequest request) {
		CartItem cartItem = new CartItem();

		cartItem.setProduct(productService.getProductById(id));
		cartItem.setQuantity(counter);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		Customer customUser = customerService.getCustomerByName(user.getUsername());
		Cart cart = customUser.getCart();

		cart.getCartItemList().add(cartItem);
		cartItem.setCart(cart);
		// cartDao.AddCartItem(cartItem);
		cartDao.EditCart(cart);

		return "redirect:/cart";
	}

}
