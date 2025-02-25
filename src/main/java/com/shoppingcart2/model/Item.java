package com.shoppingcart2.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Document(collection = "items")
public class Item {

    @Id
    private String id;

    @Valid

    private String identifier;

    @NotNull(message = "Action is mandatory")
    private ActionType action;

    @NotNull(message = "Prices are mandatory")
    @Size(min = 1, message = "At least one price is required")
    private List<Price> prices;

    @Pattern(regexp = "^[a-zA-Z0-9]{5,20}$")
    @NotNull(message = "customerId is mandatory")
    @NotBlank(message = "CustomerId is mandatory")
    private String customerId;

    private LocalDateTime timestamp;

    public Item(String identifier, ActionType action, List<Price> prices, String customerId, LocalDateTime timestamp) {

        this.identifier = identifier;
        this.action = action;
        this.prices = prices;
        if (customerId.isBlank()) {
            throw new IllegalArgumentException("Customer identifier must be added");
        }
        this.customerId = customerId;
        this.timestamp = timestamp;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public ActionType getAction() {
        return this.action;
    }

    public void setAction(ActionType action) {
        this.action = action;
    }

    public List<Price> getPrices() {
        return this.prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
