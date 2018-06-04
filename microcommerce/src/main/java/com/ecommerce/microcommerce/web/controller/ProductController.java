package com.ecommerce.microcommerce.web.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.model.Product;

@RestController
public class ProductController {
	
	@Autowired
	private ProductDao productDao;
	
	//@GetMapping
	@RequestMapping(value="/Produits", method=RequestMethod.GET)
	public ArrayList<Product> listeProduit(){
		return productDao.findAll();
	}
	
	//@GetMapping
	@RequestMapping(value="/Produits/{id}", method=RequestMethod.GET)
	public Product afficheUnProduit(@PathVariable int id){
		return productDao.findById(id);
	}
}
