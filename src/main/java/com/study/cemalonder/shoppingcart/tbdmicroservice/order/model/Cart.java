package com.study.cemalonder.shoppingcart.tbdmicroservice.order.model;

import java.util.List;

import com.study.cemalonder.shoppingcart.tbdmicroservice.discount.model.Coupon;

import lombok.Data;

@Data
public class Cart {

    private List<CartProduct> cartProducts;

    private Coupon coupon;

    private Double discountedOrderPrice;

    private Double totalOrderPrice;

}
