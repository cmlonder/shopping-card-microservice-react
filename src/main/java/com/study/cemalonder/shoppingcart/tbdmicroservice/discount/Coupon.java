package com.study.cemalonder.shoppingcart.tbdmicroservice.discount;

import java.util.Objects;

public final class Coupon extends Discount {

    private final CouponDiscountCriteria couponDiscountCriteria;

    public static Coupon of(CouponDiscountCriteria couponDiscountCriteria, Double discountAmount,
        DiscountType discountType) {
        return new Coupon(couponDiscountCriteria, discountAmount, discountType);
    }

    Double getDiscountedPrice(Double totalAmount) {
        Objects.requireNonNull(totalAmount, "totalAmount");
        if (isBiggerThanDiscountLimit(totalAmount)) {
            return calculateDiscount(totalAmount);
        }
        return totalAmount;
    }

    private Coupon(CouponDiscountCriteria couponDiscountCriteria, Double discountAmount, DiscountType discountType) {
        super(discountAmount, discountType);
        Objects.requireNonNull(couponDiscountCriteria, "couponDiscountCriteria");
        Objects.requireNonNull(discountAmount, "discountAmount");
        this.couponDiscountCriteria = couponDiscountCriteria;
    }

    private boolean isBiggerThanDiscountLimit(Double totalAmount) {
        assert totalAmount != null : "totalAmount can not be null";
        assert couponDiscountCriteria != null : "couponDiscountCriteria can not be null";
        assert couponDiscountCriteria.getTotalAmountLimit()
            != null : "couponDiscountCriteria.getTotalAmountLimit can not be null";
        return totalAmount > couponDiscountCriteria.getTotalAmountLimit();
    }

}
