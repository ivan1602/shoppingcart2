package com.shoppingcart2.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shoppingcart2.model.Item;
import com.shoppingcart2.model.StatisticsResponse;
import com.shoppingcart2.repository.ItemRepository;

import ch.qos.logback.classic.Logger;

@Service
public class ItemService {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(ItemService.class);

    
    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getItemsByCustomer(String customerId) {
        return itemRepository.findByCustomerId(customerId);
    }
 
    public Item addItem(Item item){
        item.setTimestamp(LocalDateTime.now());
        String randomString = generateRandomString(10); // Generate a string of length 10
        if (item.getIdentifier().isBlank()){
            item.setIdentifier(randomString);
        }
        return itemRepository.save(item);
    }

   /* public void removeItem(String id) {
        itemRepository.deleteById(id);
    } */

    public boolean removeItem(String customerId, String identifier) {
        // Pronađi item po identifier-u
        try {       
            // Retrieve items by identifier
            List<Item> itemsInRange = itemRepository.findByCustomerId(customerId);
              
            // Count instances of each action type
            for (Item item : itemsInRange) {
                if (item.getIdentifier().toString().equals(identifier))
                {
                    itemRepository.deleteById(item.getId());
                    return true; 
                }               
            }
            
        } catch (Exception e) {
            logger.error("Error occurred while calculating statistics: {}", e.getMessage(), e);
            return false;
        }   
        return false;    
    }

    public void evictCart(){
        itemRepository.deleteAll();
    }

    public StatisticsResponse getStatistics (String identifier) {
        Map<String, Integer> statistics = new HashMap<>();
        int numberOfSales = 0;
        try {       
            // Retrieve items by identifier
            List<Item> itemsInRange = itemRepository.findByIdentifier(identifier);

            if (itemsInRange == null || itemsInRange.isEmpty()) {
                return new StatisticsResponse(numberOfSales, statistics, "No items were sold");
            }
    
            // Count instances of each action type
            for (Item item : itemsInRange) {
                String key = item.getAction().toString();
                statistics.put(key, statistics.getOrDefault(key, 0) + 1);
            }

        } catch (Exception e) {
            logger.error("Error occurred while calculating statistics: {}", e.getMessage().toString(), e);
         return new StatisticsResponse(Integer.valueOf(numberOfSales), statistics, "An internal error occurred. Please try again later.");
        }

        return new StatisticsResponse(Integer.valueOf(numberOfSales), statistics, "Statistics retrieved successfully");

   } 

   public StatisticsResponse getFullStatistics (String identifier, LocalDateTime startDate, LocalDateTime endDate) {
    Map<String, Integer> statistics = new HashMap<>();
    int numberOfSales = 0;
    try {       
        // Retrieve items by identifier
        List<Item> itemsInRange = itemRepository.findByIdentifier(identifier);      
        
        for (Item item : itemsInRange) {
            LocalDateTime addedDate = item.getTimestamp();

            if ((addedDate.isEqual(startDate) || addedDate.isAfter(startDate)) &&
            (addedDate.isEqual(endDate) || addedDate.isBefore(endDate))) {
                String key = item.getAction().toString();                
                statistics.put(key, statistics.getOrDefault(key, 0) + 1);
                numberOfSales++;                
            }                   
        }

    } catch (Exception e) {
        logger.error("Error occurred while calculating statistics: {}", e.getMessage(), e);
     return new StatisticsResponse(numberOfSales, statistics, "An internal error occurred. Please try again later.");
    }
        return new StatisticsResponse(numberOfSales, statistics, "Statistics retrieved successfully.");
   }   

   public static String generateRandomString(int length) {
    String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    Random random = new Random();
    StringBuilder stringBuilder = new StringBuilder(length);

    for (int i = 0; i < length; i++) {
        int index = random.nextInt(characters.length());
        stringBuilder.append(characters.charAt(index));
    }

    return stringBuilder.toString();
}
}
