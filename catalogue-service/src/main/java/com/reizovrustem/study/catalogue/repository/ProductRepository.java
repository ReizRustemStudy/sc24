package com.reizovrustem.study.catalogue.repository;

import java.util.List;
import java.util.Optional;

import com.reizovrustem.study.catalogue.entity.Product;

public interface ProductRepository {

	List<Product> findAll();

	Product save(Product product);

	Optional<Product> findById(Integer productId);

	void deleteById(Integer id);

}
