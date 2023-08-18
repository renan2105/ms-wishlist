package com.renan.wish.resources;

import com.renan.wish.domain.Customer;
import com.renan.wish.dto.CustomerDTO;
import com.renan.wish.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/customers")
public class CustomerResource {

	@Autowired
	private CustomerService service;
	
	@RequestMapping(method=RequestMethod.GET)
 	public ResponseEntity<List<CustomerDTO>> findAll() {
		List<Customer> customerList = service.findAll();
		List<CustomerDTO> customerDTOList = customerList.stream().map(x -> new CustomerDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(customerDTOList);
	}

}
