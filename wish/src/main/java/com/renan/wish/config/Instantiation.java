package com.renan.wish.config;

import com.renan.wish.domain.Customer;
import com.renan.wish.domain.Product;
import com.renan.wish.repository.CustomerRepository;
import com.renan.wish.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ProductRepository productRepository;


	@Override
	public void run(String... arg0) {
		
		customerRepository.deleteAll();
		productRepository.deleteAll();

		Customer renan = new Customer(null, "Renan");
		Customer ruan = new Customer(null, "Ruan");
		Customer test = new Customer(null, "Test");

		customerRepository.saveAll(Arrays.asList(renan, ruan, test));

		for (int i = 1; i < 25; i++) {
			productRepository.save(new Product(null, "Product" + i));
		}

	}

}
