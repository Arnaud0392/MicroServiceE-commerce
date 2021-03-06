package com.ecommerce.microcommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.fasterxml.jackson.annotation.JsonIgnore;


// On pouvait aussi le mettre au debut de la classe et lister toutes les propriétés à ignorer
//@JsonIgnoreProperties(value = {"prixAchat", "..."})

/*
 * On peut aussi filtrer dynamiquement en exposant ou non une propiété en fonction de qui la demande
 * Pour cela, on utilise : @JsonFilter("monFiltreDynamique")
 * Cette annotation veut juste dire que le beans Product accepte un filtre appelé "monFiltreDynamique"
 * Il ne nous reste plus qu'à aller créer ce filtre dans le controlleur du bean
 * */

@Entity
public class Product {
	
	@Id
	//@GeneratedValue
	private int id;
	
	@Length(min=3, max=20, message="Le nom doit être compris entre 3 et 20 caractères")
	private String nom;
	
	@Min(value=1)
	private int prix;
	
	// Information que nous ne voulons pas exposer
	
	@JsonIgnore
	private int prixAchat;

	public Product() {
		super();
	}

	public Product(int id, String nom, int prix, int prixAchat) {
		super();
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.prixAchat = prixAchat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public int getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(int prixAchat) {
		this.prixAchat = prixAchat;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", nom=" + nom + ", prix=" + prix
				+ ", prixAchat=" + prixAchat + "]";
	}
	
	
}
