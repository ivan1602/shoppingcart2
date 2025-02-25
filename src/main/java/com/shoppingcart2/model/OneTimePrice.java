package com.shoppingcart2.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class OneTimePrice extends Price {

    public OneTimePrice() {
        super(null, PriceType.ONE_TIME);
    }
}
