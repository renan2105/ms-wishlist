package com.renan.wish.resources;

import com.renan.wish.domain.Product;
import com.renan.wish.dto.ProductDTO;
import com.renan.wish.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/products")
public class ProductResource {

	@Autowired
	private ProductService service;
	
	@RequestMapping(method=RequestMethod.GET)
 	public ResponseEntity<List<ProductDTO>> findAll() {
		List<Product> productList = service.findAll();
		List<ProductDTO> productDTOList = productList.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(productDTOList);
	}

}
