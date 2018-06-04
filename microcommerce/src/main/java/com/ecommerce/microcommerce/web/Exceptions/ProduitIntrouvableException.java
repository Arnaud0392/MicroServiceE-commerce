package com.ecommerce.microcommerce.web.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProduitIntrouvableException extends RuntimeException {

	public ProduitIntrouvableException(String s) {
		// TODO Auto-generated constructor stub
		super(s);
	} 
}
