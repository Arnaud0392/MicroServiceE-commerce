package com.ecommerce.microcommerce.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.microcommerce.model.*;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
	
	Product findById(int id);
	
	/*
	 * Pour sélecttionner les produits dont le prix est supérieur à un certain seuil, 
	 * on aurait aussi pu utiliser la notation JPQL
	 */
	//@Query("SELECT id, nom, prix, prixAchat FROM Product p WHERE p.prix > :prixLimit")
	//List<Product> rechercheUnProduitUnPeuCher(@Param("prixLimit") int prix);
	
	List<Product> findByPrixGreaterThan(int prixLimit);
	
	List<Product> findByNomLike(String recherche);
}
