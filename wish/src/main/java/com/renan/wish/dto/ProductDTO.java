package com.renan.wish.dto;

import com.renan.wish.domain.Product;

import java.io.Serializable;

public class ProductDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	
	public ProductDTO() {
	}
	
	public ProductDTO(Product product) {
		id = product.getId();
		name = product.getName();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
