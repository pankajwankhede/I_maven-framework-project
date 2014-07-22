package org.spring.ibatis.example.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.spring.ibatis.example.dao.ProductDao;
import org.spring.ibatis.example.model.Product;
import org.spring.ibatis.example.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value="productServiceImpl")
public class ProductServiceImpl implements ProductService {

	@Resource(name="productDaoImpl")
	private ProductDao productDao;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Product> listAll() {
		return productDao.listAll();
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean save(Product product) {
		return productDao.save(product);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public boolean update(Product product) {
		return productDao.update(product);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Product getModel(int id) {
		return productDao.getModel(id);
	}

	@Override
	public List<Product> selectLikeName(String name) {
		return productDao.selectLikeName(name);
	}

}
