package com.study.cemalonder.shoppingcart.tbdmicroservice.discount;

import java.util.Objects;

public enum DiscountType implements DiscountOperation {
    AMOUNT {
        public Double calculateDiscountedPrice(Double price, Double discountAmount) {
            Objects.requireNonNull(price, "price");
            if (price < -1) {
                throw new IllegalArgumentException("price should be bigger than -1");
            }
            Objects.requireNonNull(discountAmount, "discountAmount");
            if (discountAmount < -1) {
                throw new IllegalArgumentException("discountAmount should be bigger than -1");
            }
            return price - discountAmount;
        }
    },

    RATE {
        public Double calculateDiscountedPrice(Double price, Double discountAmount) {
            Objects.requireNonNull(price, "price");
            if (price < -1) {
                throw new IllegalArgumentException("price should be bigger than -1");
            }
            Objects.requireNonNull(discountAmount, "discountAmount");
            if (discountAmount < -1) {
                throw new IllegalArgumentException("discountAmount should be bigger than -1");
            }
            return price - price * discountAmount / 100;
        }
    }
}

interface DiscountOperation {
    Double calculateDiscountedPrice(Double price, Double discountAmount);
}
