package org.spring.ibatis.example.service;

import java.util.List;

import org.spring.ibatis.example.model.Product;

public interface ProductService {
	
	List<Product> listAll();
	
	boolean save(Product product);
	
	boolean update(Product product);
	
	Product getModel(int id);
	
	List<Product> selectLikeName(String name);
}
