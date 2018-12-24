package com.study.cemalonder.shoppingcart.tbdmicroservice.discount;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.study.cemalonder.shoppingcart.tbdmicroservice.catalog.product.Product;
import com.study.cemalonder.shoppingcart.tbdmicroservice.order.ShoppingCart;

public class DiscountService {

    public ShoppingCart applyDiscounts(ShoppingCart shoppingCart, Coupon coupon, Campaign... campaigns) {
        ShoppingCart campaignApplied = applyCampaigns(shoppingCart, campaigns);
        ShoppingCart couponApplied = applyCoupon(campaignApplied, coupon);
        return couponApplied;
    }

    private ShoppingCart applyCampaigns(ShoppingCart shoppingCart, Campaign... campaigns) {
        if (campaigns != null && campaigns.length > 0) {
            Map<Product, Integer> discountedProducts = getDiscountedProducts(shoppingCart, campaigns);
            return ShoppingCart.applyCampaignDiscount(shoppingCart, discountedProducts);
        }
        return shoppingCart;
    }

    private ShoppingCart applyCoupon(ShoppingCart shoppingCart, Coupon coupon) {
        if (coupon != null) {
            Double currentAmount = shoppingCart.getTotalAmount();
            Double discountedAmount = coupon.getDiscountedPrice(currentAmount);
            return ShoppingCart.applyCouponDiscount(shoppingCart, discountedAmount);
        }
        return shoppingCart;
    }

    private Map<Product, Integer> getDiscountedProducts(ShoppingCart shoppingCart, Campaign... campaigns) {
        assert shoppingCart != null : "shoppingCart can not be null";
        assert shoppingCart.getProducts() != null : "shoppinCart.getProducts can not be null";

        Map<Product, Integer> discountedProducts = new HashMap<>();
        shoppingCart.getProducts().forEach((product, orderAmount) -> {
            Double discountedPrice = getMinDiscountedPrice(product, orderAmount, campaigns);
            Product discountedProduct = applyDiscountToProduct(product, discountedPrice);
            discountedProducts.put(discountedProduct, orderAmount);
        });
        return discountedProducts;
    }

    private Product applyDiscountToProduct(Product product, Double discountedPrice) {
        assert product != null : "product can not be null";
        return Product.of(product, discountedPrice);
    }

    private Double getMinDiscountedPrice(Product product, Integer orderAmount, Campaign... campaigns) {
        List<Double> discountedPrices = getDiscountedPrices(product, orderAmount, campaigns);
        assert discountedPrices != null : "discountedPrices can not be null";
        assert product.getPrice() != null : "product.getPrice can not be null";
        return discountedPrices.stream().min(Comparator.naturalOrder()).orElse(product.getPrice());

    }

    private List<Double> getDiscountedPrices(Product product, Integer orderAmount, Campaign... campaigns) {
        List<Double> discountedPrices = new ArrayList<>();
        for (Campaign campaign : campaigns) {
            Double discountedPrice = getDiscountedPrice(campaign, product, orderAmount);
            discountedPrices.add(discountedPrice);
        }
        return discountedPrices;
    }

    private Double getDiscountedPrice(Campaign campaign, Product product, Integer orderAmount) {
        assert campaign != null : "campaign can not be null";
        return campaign.getDiscountedPrice(product.getCategory(), orderAmount, product.getPrice());
    }

}
