package com.reizovrustem.study.manager.repository;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.reizovrustem.study.manager.entity.Product;

@Repository
public class InMemoryProductRepository implements ProductRepository {

	private final List<Product> products = Collections.synchronizedList(new LinkedList<>());
	
	@Override
	public List<Product> findAll() {
		return Collections.unmodifiableList(products);
	}

	@Override
	public Product save(Product product) {
		product.setId(products.stream()
				.max(Comparator.comparingInt(Product::getId))
				.map(Product::getId)
				.orElse(0) + 1);
		products.add(product);
		return product;
	}

	@Override
	public Optional<Product> findById(Integer productId) {
		return products.stream().filter(product -> Objects.equals(productId, product.getId()))
				.findFirst();
	}

	@Override
	public void deleteById(Integer id) {
		products.removeIf(product -> Objects.equals(id, product.getId()));
		
	} 
}
