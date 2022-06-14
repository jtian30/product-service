package com.tian.service.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tian.service.product.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
