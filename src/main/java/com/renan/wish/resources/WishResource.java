package com.renan.wish.resources;

import com.renan.wish.domain.Wish;
import com.renan.wish.dto.ProductDTO;
import com.renan.wish.dto.WishDTO;
import com.renan.wish.services.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/wishes")
public class WishResource {

	@Autowired
	private WishService service;
	
	@RequestMapping(value="/addNewWish", method=RequestMethod.POST)
 	public ResponseEntity<Void> addNewWish(@RequestBody WishDTO wishDTO) {
		Wish wish = service.fromDTO(wishDTO);
		wish = service.addNewWish(wish);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(wish.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/removeByIdCustomerAndIdProduct/{idCustomer}/{idProduct}", method=RequestMethod.DELETE)
 	public ResponseEntity<Void> deleteByIdCustomerAndProductId(@PathVariable String idCustomer,
																@PathVariable String idProduct) {
		service.deleteByIdCustomerAndProductId(idCustomer, idProduct);
		return ResponseEntity.noContent().build();
	}
	

	@RequestMapping(value="/findAllByIdCustomer/{idCustomer}", method=RequestMethod.GET)
	public ResponseEntity<List<WishDTO>> findAllByIdCustomer(@PathVariable String idCustomer) {

		List<Wish> wishList = service.findAllByIdCustomer(idCustomer);
		List<WishDTO> wishDTOList = wishList.stream().map(x -> new WishDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(wishDTOList);
	}
	
	@RequestMapping(value="/findByIdCustomerAndIdProduct/{idCustomer}/{idProduct}", method=RequestMethod.GET)
	public ResponseEntity<WishDTO> findByIdCustomerAndIdProduct(@PathVariable String idCustomer,
																@PathVariable String idProduct) {
		Wish wish = service.findByIdCustomerAndIdProduct(idCustomer, idProduct);
		return ResponseEntity.ok().body(new WishDTO(wish));
	}
}
