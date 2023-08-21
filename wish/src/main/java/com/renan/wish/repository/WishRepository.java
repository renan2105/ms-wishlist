package com.renan.wish.repository;

import com.renan.wish.domain.Wish;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishRepository extends MongoRepository<Wish, String> {

    Optional<Wish> findByIdCustomerAndProductId(String idCustomer, String idProduct);

    List<Wish> findAllByIdCustomer(String idCustomer);

    long countByIdCustomer(String idCustomer);

}
