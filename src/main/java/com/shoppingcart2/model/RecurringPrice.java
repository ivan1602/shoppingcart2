package com.shoppingcart2.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class RecurringPrice extends Price {

    @NotNull(message = "Number of recurrences is mandatory.")
    @Positive(message = "Number of recurrences must be positive.")
    private Integer numberOfRecurrences;

    public RecurringPrice(BigDecimal amount, Integer numberOfRecurrences) {
        super(amount, PriceType.RECURRING);
        if (numberOfRecurrences == null) {
            throw new IllegalArgumentException("Number of recurrences must be provided.");
        }
        if (numberOfRecurrences <= 0) {
            throw new IllegalArgumentException("Number of recurrences must be positive.");
        }
        this.numberOfRecurrences = numberOfRecurrences;

    }

    public Integer getNumberOfRecurrences() {
        return this.numberOfRecurrences;
    }

}
