package com.reizovrustem.study.manager.controller;

import java.util.Locale;
import java.util.NoSuchElementException;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.reizovrustem.study.manager.controller.payload.UpdateProductPayload;
import com.reizovrustem.study.manager.entity.Product;
import com.reizovrustem.study.manager.service.ProductService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products/{productId:\\d+}")
public class ProductController {
	
	private final ProductService productService;
	
	private final MessageSource messageSource;
	
	@ModelAttribute("product")
	public Product product(@PathVariable("productId") int productId) {
		return productService.findProduct(productId)
				.orElseThrow(() -> new NoSuchElementException("catalogue.errors.product.not_found"));
	}
	
	@GetMapping
	public String getProduct() {
		return "catalogue/products/product";
	}
	
	@GetMapping("edit")
	public String getProductEditPage() {
		return "catalogue/products/edit";
	}
	
	@PostMapping("edit")
	public String updateProduct(@ModelAttribute("product") Product product, UpdateProductPayload payload) {
		productService.updateProduct(product.getId(), payload.title(), payload.details());
		return "redirect:/catalogue/products/%d".formatted(product.getId());
	}
	
	@PostMapping("delete")
	public String deleteProduct(@ModelAttribute("product") Product product) {
		productService.deleteProduct(product.getId());
		return "redirect:/catalogue/products/list";
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public String handleNoSuchElementException(NoSuchElementException exception, Model model, 
			HttpServletResponse response, Locale locale) {
		response.setStatus(HttpStatus.NOT_FOUND.value());
		model.addAttribute("error", 
				messageSource.getMessage(exception.getMessage(), new Object[0], 
						exception.getMessage(), locale));
		return "error/404";
	}
	
}