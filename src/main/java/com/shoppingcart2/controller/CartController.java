package com.shoppingcart2.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart2.model.Item;
import com.shoppingcart2.model.StatisticsResponse;
import com.shoppingcart2.service.ItemService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ItemService itemService;

    //Get all items for customer
    @GetMapping("/{customerId}")
    public List<Item> getCart(@PathVariable String customerId) {
        return itemService.getItemsByCustomer(customerId);
    }


 @PostMapping("/add")
    public ResponseEntity<String> addItem(
        @RequestBody @Valid Item item) { // @Valid omogućava validaciju        
        Item savedItem = itemService.addItem(item);
        return ResponseEntity.status(HttpStatus.CREATED).body("Item added successfully");
    } 

   /* //Remove one item from the cart by ID
    @DeleteMapping("/{customerId}/remove/{id}")
    public void removeItem(@PathVariable String id) {
        itemService.removeItem(id);
    } */
    
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeItem(
            @RequestParam String customerId,
            @RequestParam String identifier) {
        boolean isRemoved = itemService.removeItem(customerId, identifier);
        if (isRemoved) {
            return ResponseEntity.ok("Item uspješno obrisan.");
        } else {
            return ResponseEntity.badRequest().body("Item nije pronađen ili nije pripadao korisniku.");
        }
    }

    //Evict all items from the cart for the customer
    @DeleteMapping("/evict")
    public void evictCart(){
        itemService.evictCart();
    }

    @GetMapping("/statistics")
    public StatisticsResponse getStatistics(
            @RequestParam String identifier) {
        return itemService.getStatistics(identifier);
    }

    @GetMapping("/fullstatistics")
    public StatisticsResponse getFullStatistics(
            @RequestParam String identifier, 
             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return itemService.getFullStatistics(identifier, startDate, endDate);
    }


}