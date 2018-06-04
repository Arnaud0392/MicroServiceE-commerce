package com.ecommerce.microcommerce.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.ecommerce.microcommerce.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao{

	public static ArrayList<Product> produit = new ArrayList<>(); 
	static {

        produit.add(new Product(1, new String("Ordinateur portable"), 350));

        produit.add(new Product(2, new String("Aspirateur Robot"), 500)); 

        produit.add(new Product(3, new String("Table de Ping Pong"), 750));

    }
	
	@Override
	public ArrayList<Product> findAll() {
		// TODO Auto-generated method stub
		return produit;
	}

	@Override
	public Product findById(int id) {
		// TODO Auto-generated method stub
		for (Product product : produit) {  
            if(product.getId() ==id){
                return product;
            }
        }
		return null;
	}

	@Override
	public Product save(Product produits) {
		// TODO Auto-generated method stub
		produit.add(produits);
		return produits;
	}

}
