package com.study.cemalonder.shoppingcart.tbdmicroservice.discount;

import java.util.Objects;

import com.study.cemalonder.shoppingcart.tbdmicroservice.catalog.category.Category;

public final class Campaign extends Discount {

    private final CampaignDiscountCriteria campaignDiscountCriteria;

    public static Campaign of(CampaignDiscountCriteria campaignDiscountCriteria, Double discountAmount,
        DiscountType discountType) {
        return new Campaign(campaignDiscountCriteria, discountAmount, discountType);
    }

    Double getDiscountedPrice(Category category, Integer orderAmount, Double price) {
        if (isApplicableCategory(category) && isBiggerThanDiscountLimit(orderAmount)) {
            return calculateDiscount(price);
        }
        return price;
    }

    private Campaign(CampaignDiscountCriteria campaignDiscountCriteria, Double discountAmount,
        DiscountType discountType) {
        super(discountAmount, discountType);
        Objects.requireNonNull(campaignDiscountCriteria, "campaignDiscountCriteria");
        this.campaignDiscountCriteria = campaignDiscountCriteria;
    }

    private boolean isBiggerThanDiscountLimit(Integer orderAmount) {
        assert orderAmount != null : "orderAmount can not be null";
        assert orderAmount < -1 : "orderAmount can not be less than -1";
        assert campaignDiscountCriteria != null : "campaignDiscountCriteria can not be null";
        assert campaignDiscountCriteria.getOrderAmount()
            != null : "campaignDiscountCriteria.getOrderAmount can not be null";
        return orderAmount > campaignDiscountCriteria.getOrderAmount();
    }

    private boolean isApplicableCategory(Category requestedCategory) {
        assert requestedCategory != null : "requestedCategory can not be null";
        assert campaignDiscountCriteria != null : "campaignDiscountCriteria can not be null";
        assert campaignDiscountCriteria.getCategory() != null : "campaignDiscountCriteria.getCategory can not be null";

        if (campaignDiscountCriteria.getCategory().equals(requestedCategory)) {
            return true;
        } else if (requestedCategory.getParentCategory() != null) {
            return isApplicableCategory(requestedCategory.getParentCategory());
        }
        return false;
    }
}
