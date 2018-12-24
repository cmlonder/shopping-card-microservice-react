package com.study.cemalonder.shoppingcart.tbdmicroservice.order;

import static java.util.stream.Collectors.groupingBy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.study.cemalonder.shoppingcart.tbdmicroservice.catalog.product.Product;

public class ShoppingCart {

    private final Double campaignDiscount;

    private final Double couponDiscount;

    private final Double deliveryCost;

    private final Map<Product, Integer> products;

    private final Double totalAmount;

    public ShoppingCart() {
        this.products = Collections.unmodifiableMap(new HashMap<>());
        this.totalAmount = 0.0;
        this.deliveryCost = 0.0;
        this.campaignDiscount = 0.0;
        this.couponDiscount = 0.0;
    }

    public ShoppingCart addItem(Product product, Integer quantity) {
        Map<Product, Integer> newProducts = new HashMap<>(products);
        newProducts.put(product, quantity);
        return updateProducts(this, newProducts);
    }

    public Double getCampaignDiscount() {
        return campaignDiscount;
    }

    public Double getCouponDiscount() {
        return couponDiscount;
    }

    public Double getDeliveryCost() {
        return deliveryCost;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public Double getTotalPrice() {
        return getTotalAmount() - getCouponDiscount() - getCampaignDiscount() + getDeliveryCost();
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void print() {
        final String CATEGORY_SEPERATOR = "--------------------------------------";
        final String PRODUCT_SEPERATOR = "";
        products.keySet().stream().collect(groupingBy(Product::getCategory)).forEach((category, groupedProducts) -> {
            System.out.println(category.toString());
            System.out.println(CATEGORY_SEPERATOR);
            groupedProducts.forEach(product -> {
                System.out.println(product.toString());
                System.out.println("Quantity: " + products.get(product));
                System.out.println("Unit Price: " + product.getPrice());
                System.out.println("Total Price: " + calculateProductPair(product.getPrice(), products.get(product)));
                System.out.println(PRODUCT_SEPERATOR);
            });
            System.out.println(CATEGORY_SEPERATOR);
        });

        System.out.println("SUMMARY");
        System.out.println("Total Amount: " + getTotalAmount());
        System.out.println("Total Campaign Discount: " + getCampaignDiscount());
        System.out.println("Total Coupon Discount: " + getCouponDiscount());
        System.out.println("Total Delivery: " + getDeliveryCost());
        System.out.println("Total Price: " + getTotalPrice());
    }

    private ShoppingCart(Map<Product, Integer> products, Double totalAmount, Double deliveryCost,
        Double campaignDiscount, Double couponDiscount) {
        assert campaignDiscount != null : "campaignDiscout can not be null";
        assert campaignDiscount > -1 : "campaignDiscount can not be less than 0";
        assert couponDiscount != null : "couponDiscount can not be null";
        assert couponDiscount > -1 : "couponDiscout can not be less than 0";
        assert deliveryCost != null : "delivery can not be null";
        assert deliveryCost > -1 : "deliveryCost can not be less than 0";
        assert products != null : "products can not be null";
        assert totalAmount != null : "totalAmount can not be null";

        this.campaignDiscount = campaignDiscount;
        this.couponDiscount = couponDiscount;
        this.deliveryCost = deliveryCost;
        this.totalAmount = totalAmount;
        this.products = Collections.unmodifiableMap(products);
    }

    public static ShoppingCart applyCouponDiscount(ShoppingCart cart, Double totalAmount) {
        Objects.requireNonNull(cart, "cart");
        Objects.requireNonNull(totalAmount, "totalAmount");
        Double couponDiscount = cart.getTotalAmount() - totalAmount;
        return new ShoppingCart(cart.getProducts(), cart.getTotalAmount(), cart.getDeliveryCost(), cart.getCampaignDiscount(),
            couponDiscount);
    }

    public static ShoppingCart applyCampaignDiscount(ShoppingCart cart, Map<Product, Integer> products) {
        Objects.requireNonNull(cart, "cart");
        Objects.requireNonNull(products, "products");
        Double campaignDiscount = cart.getTotalAmount() - getProductAmounts(products);
        return new ShoppingCart(products, cart.getTotalAmount(), cart.getDeliveryCost(), campaignDiscount,
            cart.getCouponDiscount());
    }

    public static ShoppingCart updateDelivery(ShoppingCart cart, Double deliveryCost) {
        Objects.requireNonNull(cart, "cart");
        Objects.requireNonNull(deliveryCost, "deliveryCost");
        return new ShoppingCart(cart.getProducts(), cart.getTotalAmount(), deliveryCost, cart.getCampaignDiscount(),
            cart.getCouponDiscount());
    }

    public static ShoppingCart updateProducts(ShoppingCart cart, Map<Product, Integer> products) {
        Objects.requireNonNull(cart, "cart");
        Objects.requireNonNull(products, "products");
        Double updatedTotalAmount = getProductAmounts(products);
        return new ShoppingCart(products, updatedTotalAmount, cart.getDeliveryCost(), cart.getCampaignDiscount(),
            cart.getCouponDiscount());
    }

    private static Double calculateProductPair(Double price, Integer amount) {
        assert price != null : "price can not be null";
        assert amount != null : "amount can not be null";
        return price * amount;
    }

    private static Double getProductAmounts(Map<Product, Integer> products) {
        assert products != null : "products can not be null";
        return products.entrySet()
            .stream()
            .filter(product -> product.getKey() != null && product.getValue() != null)
            .map(product -> calculateProductPair(product.getKey().getPrice(), product.getValue()))
            .mapToDouble(Double::doubleValue)
            .sum();
    }
}
