package com.study.cemalonder.shoppingcart.tbdmicroservice.delivery.model;

import com.study.cemalonder.shoppingcart.tbdmicroservice.discount.model.Coupon;

import lombok.Data;
import lombok.NonNull;

/**
 * Delivery represents shipping details. It must have a
 * {@link com.study.cemalonder.shoppingcart.tbdmicroservice.order.model.Cart}. Delivery has a {@value FIXED_COST}.
 */
@Data
public class Delivery {

    private static final Double FIXED_COST = 2.99;

    @NonNull
    private Coupon coupon;

    private Double totalCost;

}
