package com.shoppingcart2.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shoppingcart2.model.Item;

public interface ItemRepository extends MongoRepository<Item, String> {

    List<Item> findByCustomerId(String customerId);

    List<Item> findByIdentifier(String identifier);

}
