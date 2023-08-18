package com.renan.wish.dto;

import com.renan.wish.domain.Wish;

import java.io.Serializable;

public class WishDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String idCustomer;

	private ProductDTO product;

	public WishDTO() {
	}
	
	public WishDTO(Wish wish) {
		id = wish.getId();
		idCustomer = wish.getIdCustomer();
		product = new ProductDTO(wish.getProduct());
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

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

}
