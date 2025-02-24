package com.shoppingcart2.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = OneTimePrice.class, name = "ONE_TIME"),    
    @JsonSubTypes.Type(value = RecurringPrice.class, name = "RECURRING")
})
@Data
abstract class Price {

    @Positive(message = "Value must be positive.")
    private BigDecimal amount;
    
    private PriceType type;

    public Price(BigDecimal amount, PriceType type) {      
       
        this.amount = amount;
        this.type = type;
    }

    public Price() {
               
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        this.amount = amount;
    } 

    public PriceType getType() {
        return this.type;
    }

    public void setType(PriceType type) {
        this.type = type;
    }
}   
