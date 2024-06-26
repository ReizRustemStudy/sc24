package com.reizovrustem.study.catalogue.service;

import java.util.List;
import java.util.Optional;

import com.reizovrustem.study.catalogue.entity.Product;

public interface ProductService {

	List<Product> findAllProducts();

	Product createProduct(String title, String details);

	Optional<Product> findProduct(int productId);

	void updateProduct(Integer id, String title, String details);

	void deleteProduct(Integer id);

}
