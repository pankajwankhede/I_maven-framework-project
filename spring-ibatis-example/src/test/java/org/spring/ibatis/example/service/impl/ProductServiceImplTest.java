package org.spring.ibatis.example.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.ibatis.example.model.Product;
import org.spring.ibatis.example.service.ProductService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ContextConfiguration(locations={"classpath:spring-ibatis.xml"})
@RunWith(value=SpringJUnit4ClassRunner.class)
public class ProductServiceImplTest {
	
	@Resource(name="productServiceImpl")
	private ProductService productService;
	
	@Test
	public void listAll(){
		List<Product> lists=productService.listAll();
		for (Product product : lists) {
			System.out.println(product.getName()+"\t"+product.getDescription()+"\t"+product.getCategory().getId()+"\t"+product.getCategory().getName());
		}
	}
	
	@Test
	public void save(){
		Product product=new Product();
		product.setName("ipad2");
		product.setPrice(3000.f);
		product.setDescription("ipad2");
		product.setCategoryid("1");
		boolean b=productService.save(product);
		System.out.println(b);
	}
	
	
	@Test
	public void update(){
		Product product=productService.getModel(3);
		product.setName("戴尔");
		product.setDescription("戴尔笔记本");
		boolean b=productService.update(product);
		System.out.println(b);
	}
	
	
	@Test
	public void getModel(){
		Product product1=productService.getModel(2);
		System.out.println(product1.getName()+","+product1.getCategory().getName());
		Product product2=productService.getModel(2);
		System.out.println(product2.getName()+","+product2.getCategory().getName());
		Product product3=productService.getModel(2);
		System.out.println(product3.getName()+","+product3.getCategory().getName());
		Product product4=productService.getModel(2);
		System.out.println(product4.getName()+","+product4.getCategory().getName());
	}
	
	
	
	@Test
	public void selectLikeName(){
		List<Product> lists=productService.selectLikeName("pad");
		for (Product product : lists) {
			System.out.println(product.getName()+"\t"+product.getDescription()+"\t"+product.getCategory().getId()+"\t"+product.getCategory().getName());
		}
	}
}