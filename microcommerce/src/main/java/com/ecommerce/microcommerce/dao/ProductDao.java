package com.ecommerce.microcommerce.dao;

import java.util.ArrayList;

import com.ecommerce.microcommerce.model.*;

public interface ProductDao {
	
	public ArrayList<Product> findAll();
	
	public Product findById(int id);
	
	public Product save(Product produit);
}
