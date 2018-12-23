package com.study.cemalonder.shoppingcart.tbdmicroservice.delivery;

import com.study.cemalonder.shoppingcart.tbdmicroservice.discount.Coupon;
import com.study.cemalonder.shoppingcart.tbdmicroservice.order.Cart;

import lombok.Data;
import lombok.NonNull;

/**
 * Delivery represents shipping details. It must have a
 * {@link Cart}. Delivery has a {@value FIXED_COST}.
 */
@Data
public class Delivery {

    private static final Double FIXED_COST = 2.99;

    @NonNull
    private Coupon coupon;

    private Double totalCost;

}
