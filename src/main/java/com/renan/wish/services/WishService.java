package com.renan.wish.services;

import com.renan.wish.domain.Product;
import com.renan.wish.domain.Wish;
import com.renan.wish.dto.WishDTO;
import com.renan.wish.repository.WishRepository;
import com.renan.wish.services.exception.ExceededLimitWishesException;
import com.renan.wish.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishService {

	@Autowired
	private WishRepository wishRepository;


	public Wish findByIdCustomerAndIdProduct(String idCustomer, String idProduct) {
		Optional<Wish> obj = wishRepository.findByIdCustomerAndProductId(idCustomer, idProduct);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public List<Wish> findAllByIdCustomer(String idCustomer) {
		List<Wish> wishList = wishRepository.findAllByIdCustomer(idCustomer);
		return wishList;
	}

	public Wish addNewWish(Wish wish) {

		if(wishRepository.countByIdCustomer(wish.getIdCustomer()) >= 20)
			throw new ExceededLimitWishesException("Limite de desejos excedido");

		return insert(wish);
	}

	public void deleteByIdCustomerAndProductId(String idCustomer, String idProduct) {
		Wish wish = findByIdCustomerAndIdProduct(idCustomer, idProduct);
		delete(wish.getId());
	}

	public Wish insert(Wish wish) {
		return wishRepository.insert(wish);
	}

	public void delete(String id) {
		wishRepository.deleteById(id);
	}

	public Wish fromDTO(WishDTO wishDTO) {
		return new Wish(wishDTO.getId(),
				wishDTO.getIdCustomer(),
				new Product(wishDTO.getProduct().getId(), wishDTO.getProduct().getName()));
	}
}
