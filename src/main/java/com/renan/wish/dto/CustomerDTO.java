package com.renan.wish.dto;

import com.renan.wish.domain.Customer;

import java.io.Serializable;

public class CustomerDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	
	public CustomerDTO() {
	}
	
	public CustomerDTO(Customer customer) {
		id = customer.getId();
		name = customer.getName();
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
