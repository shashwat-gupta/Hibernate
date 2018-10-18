package com.service.dao;

import java.util.List;

import com.service.model.Product;

public interface MyProduct {

	void addProduct(Product p) throws Exception;
	
	List<Product> products() throws Exception;
	
	List<Product> byNameOrPrice(String name,int price) throws Exception;
	
	void updateProduct(Product p) throws Exception;
	
	void deleteProduct(int productId) throws Exception;
}
