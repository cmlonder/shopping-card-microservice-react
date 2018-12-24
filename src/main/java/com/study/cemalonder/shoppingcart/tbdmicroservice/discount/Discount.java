package com.study.cemalonder.shoppingcart.tbdmicroservice.discount;

import java.util.Objects;

class Discount {

    private Double discountAmount;

    private DiscountType discountType;

    Discount(Double discountAmount, DiscountType discountType) {
        Objects.requireNonNull(discountAmount, "discountAmount");
        if (discountAmount < -1) {
            throw new IllegalArgumentException("discountAmount should be bigger than -1");
        }
        Objects.requireNonNull(discountType, "discountType");
        this.discountAmount = discountAmount;
        this.discountType = discountType;
    }

    Double calculateDiscount(Double price) {
        assert discountType != null : "discountType can not be null";
        return discountType.calculateDiscountedPrice(price, discountAmount);
    }
}
