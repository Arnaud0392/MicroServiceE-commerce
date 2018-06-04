package com.ecommerce.microcommerce.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.model.Product;
import com.ecommerce.microcommerce.web.Exceptions.ProduitIntrouvableException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;


@Api(description="API pour les opérations CRUD sur les produits")
@RestController
public class ProductController {
	
	@Autowired
	private ProductDao productDao;
	
	/*
	 * 
	 * C'est ici que nous créons notre filtre dynamique
	 * 
	 * Récupérer la liste des produits
	 */
	@ApiOperation(value="Affiche la liste des produits")
	@GetMapping(value = "/Produits")
	public MappingJacksonValue listeProduits() {
	   Iterable<Product> produits = productDao.findAll();
	
	   SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("prixAchat");
	   FilterProvider listDeNosFiltres = new SimpleFilterProvider().addFilter("monFiltreDynamique", monFiltre);
	   MappingJacksonValue produitsFiltres = new MappingJacksonValue(produits);
	   produitsFiltres.setFilters(listDeNosFiltres);
	   
	   return produitsFiltres;
	}
	
	/*
	 * 
	// Afficher la liste des produits
	@GetMapping(value="/Produits")
	public ArrayList<Product> listeProduit(){
		return productDao.findAll();
	}
	 */
	
	
	// Afficher un produit par son {id}
	@ApiOperation(value="Affiche un produit par son id, à condition qu'il soit en stock")
	@GetMapping(value="/Produits/{id}")
	public Product afficheUnProduit(@PathVariable int id){
		Product produit = productDao.findById(id);
		
		if (produit == null) 
			throw new ProduitIntrouvableException("Le produit avec l'id "+id+" n'existe pas");
		
		return produit;
	}
	
	/*
	// Méthode qui teste les requêtes
	@GetMapping(value="test/produits/{prixLimit}")
	public List<Product> testeRequetes(@PathVariable int prixLimit){
		return productDao.findByPrixGreaterThan(prixLimit);
	}
	*/
	
	// Methode de recherche en fonction d'une chaine de caractère
	@GetMapping(value="test/produits/{recherche}")
	public List<Product> testeRecherche(@PathVariable String recherche){
		return productDao.findByNomLike("%"+recherche+"%");
	}
	
	// Ajouter un produit
	@ApiOperation(value="Ajoute un produit")
	@PostMapping(value="/Produits")
	public ResponseEntity<Void> ajouterUnProduit(@Valid @RequestBody Product produit){
		Product product = productDao.save(produit);
		
		if (produit == null){
			return ResponseEntity.noContent().build();
		}
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(product.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	// Suprimer un produit
	@ApiOperation(value="Supprime un produit par son id")
	@DeleteMapping(value="Produits/{id}")
	public void supprimerProduit(@PathVariable int id){
		productDao.deleteById(id);
	}
	
	// Modifier un produit
	@ApiOperation(value="Modifie un produit par son id (Mis à jour)")
	@PutMapping(value="/Produits")
	public void updateProduit(@RequestBody Product produit){
		productDao.save(produit);
	}
}
