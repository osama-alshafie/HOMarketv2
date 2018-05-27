package com.jwt.controller.admin;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jwt.model.Product;
import com.jwt.service.CustomerService;
import com.jwt.service.ProductService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private ProductService productService;
	private Path path;
	@Autowired
	private CustomerService customerService;

	@RequestMapping(method = RequestMethod.GET)
	public String adminPage(Model model) {
		model.addAttribute("title", "Admin Page");
		model.addAttribute("products", productService.getAllProducts());

		return "home-02";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String adminLogout() {

		return "redirect:/j_spring_security_logout";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String adminLogin(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model) {
		if (error != null) {
			model.addAttribute("error", "username or password is wrong.");
		}

		if (logout != null) {
			model.addAttribute("msg", "you have to logout.");
		}
		return "adminLogin";
	}

	@RequestMapping(value = "/productInventory", method = RequestMethod.GET)
	public String productInventory(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "product";
	}

	@RequestMapping(value = "/product/create", method = RequestMethod.GET)
	public String createProduct(Model model) {
		model.addAttribute("title", "New Product");
		model.addAttribute("headerMSG", "create a new Product");
		model.addAttribute("product", new Product());
		return "newProduct";
	}

	@RequestMapping(value = "/product/add", method = RequestMethod.POST)
	public String addProduct(@Valid @ModelAttribute Product product, BindingResult br, HttpServletRequest req) {
		if (br.hasErrors()) {
			return "newProduct";
		}
		MultipartFile productImage = product.getProductImage();

		String rootDir = req.getSession().getServletContext().getRealPath("/");

		path = Paths.get(rootDir + "\\WEB-INF\\resources\\images\\" + product.getId() + ".png");

		if (productImage != null && !productImage.isEmpty()) {

			try {
				productImage.transferTo(new File(path.toString()));
			} catch (Exception e) {

				throw new RuntimeException("saving file is failed", e);
			}
		}
		productService.AddProduct(product);
		return "redirect:/admin";
	}

	@RequestMapping(value = "/product/delete/{id}", method = RequestMethod.GET)
	public String deleteProduct(Model model, @PathVariable("id") int id) {
		// Product product = productService.getProductById(id);
		productService.deleteProduct(id);

		return "redirect:/admin/productInventory";
	}

	@RequestMapping(value = "/product/edit/{id}", method = RequestMethod.GET)
	public String editProduct(Model model, @PathVariable("id") int id) {
		Product product = productService.getProductById(id);
		model.addAttribute("title", "Edit Product");
		model.addAttribute("headerMSG", "Edit the Product");
		model.addAttribute("product", product);

		return "newProduct";
	}

	@RequestMapping(value = "/product/edit", method = RequestMethod.POST)
	public String editProduct(@Valid @ModelAttribute Product product, BindingResult br) {
		if (br.hasErrors()) {
			return "newProduct";
		}
		productService.updateProduct(product);
		return "redirect:/admin/productInventory";
	}

	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public String customerManagement(Model model) {
		model.addAttribute("customers", customerService.getAllCustomers());
		return "customers";
	}

}
