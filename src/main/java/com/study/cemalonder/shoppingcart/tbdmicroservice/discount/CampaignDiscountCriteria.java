package com.study.cemalonder.shoppingcart.tbdmicroservice.discount;

import java.util.Objects;

import com.study.cemalonder.shoppingcart.tbdmicroservice.catalog.category.Category;

public class CampaignDiscountCriteria {

    private Category category;

    private Integer orderAmount;

    private CampaignDiscountCriteria(Category category, Integer orderAmount) {
        Objects.requireNonNull(category, "category");
        Objects.requireNonNull(orderAmount, "orderAmount");
        if (orderAmount < -1) {
            throw new IllegalArgumentException("OrderAmount should be bigger than -1");
        }
        this.category = category;
        this.orderAmount = orderAmount;
    }

    public static CampaignDiscountCriteria of(Category category, Integer orderAmount) {
        return new CampaignDiscountCriteria(category, orderAmount);
    }

    Category getCategory() {
        return category;
    }

    Integer getOrderAmount() {
        return orderAmount;
    }
}
