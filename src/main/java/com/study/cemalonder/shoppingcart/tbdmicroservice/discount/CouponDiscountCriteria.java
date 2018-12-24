package com.study.cemalonder.shoppingcart.tbdmicroservice.discount;

import java.util.Objects;

public class CouponDiscountCriteria {
    private Double totalAmountLimit;

    private CouponDiscountCriteria(Double totalAmountLimit) {
        Objects.requireNonNull(totalAmountLimit, "totalAmountLimit");
        this.totalAmountLimit = totalAmountLimit;
    }

    public static CouponDiscountCriteria of(Double totalAmountLimit) {
        return new CouponDiscountCriteria(totalAmountLimit);
    }

    Double getTotalAmountLimit() {
        return totalAmountLimit;
    }
}
