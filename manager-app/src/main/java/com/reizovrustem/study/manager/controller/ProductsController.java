package com.reizovrustem.study.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.reizovrustem.study.manager.controller.payload.NewProductPayload;
import com.reizovrustem.study.manager.entity.Product;
import com.reizovrustem.study.manager.service.ProductService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products")
public class ProductsController {

	private final ProductService productService;
	
	@GetMapping("list")
	public String getProductList(Model model) {
		model.addAttribute("products", productService.findAllProducts());
		return "catalogue/products/list";
	}
	
	@GetMapping("create")
	public String getNewProductPage() {
		return "catalogue/products/new_product";
	}
	
	@PostMapping("create")
	public String createProduct(NewProductPayload payload) {
		Product product = productService.createProduct(payload.title(), payload.details());
		return "redirect:/catalogue/products/list";
	}	
}
