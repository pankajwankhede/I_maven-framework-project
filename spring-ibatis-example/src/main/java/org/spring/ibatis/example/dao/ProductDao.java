package org.spring.ibatis.example.dao;

import java.util.List;

import org.spring.ibatis.example.model.Product;

public interface ProductDao {
	
	List<Product> listAll();

	List<Product> selectLikeName(String name);
	
	boolean save(Product product);
	
	boolean update(Product product);
	
	Product getModel(int id);
}
