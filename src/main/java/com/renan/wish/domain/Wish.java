package com.renan.wish.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection="wish")
public class Wish implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String idCustomer;
	@DBRef
	private Product product;


	public Wish() {
	}

	public Wish(String id, String idCustomer, Product product) {
		this.id = id;
		this.idCustomer = idCustomer;
		this.product = product;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}