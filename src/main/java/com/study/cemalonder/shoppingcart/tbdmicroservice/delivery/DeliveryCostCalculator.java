package com.study.cemalonder.shoppingcart.tbdmicroservice.delivery;

import java.util.Map;
import java.util.Objects;

import com.study.cemalonder.shoppingcart.tbdmicroservice.catalog.product.Product;
import com.study.cemalonder.shoppingcart.tbdmicroservice.order.ShoppingCart;

public class DeliveryCostCalculator {

    private final Double costPerDelivery;

    private final Double costPerProduct;

    private final Double fixedCost;

    public DeliveryCostCalculator(Double costPerDelivery, Double costPerProduct, Double fixedCost) {
        Objects.requireNonNull(costPerDelivery, "costPerDelivery");
        Objects.requireNonNull(costPerProduct, "costPerProduct");
        Objects.requireNonNull(fixedCost, "fixedCost");
        if (costPerDelivery < 0) {
            throw new IllegalArgumentException("costPerDelivery can not be less than 0");
        }
        if (costPerProduct < 0) {
            throw new IllegalArgumentException("costPerProduct can not be less than 0");
        }
        if (fixedCost < 0) {
            throw new IllegalArgumentException("fixedCost can not be less than 0");
        }
        this.costPerDelivery = costPerDelivery;
        this.costPerProduct = costPerProduct;
        this.fixedCost = fixedCost;
    }

    public ShoppingCart calculateFor(ShoppingCart cart) {
        Integer numberOfDeliveries = getDistinctCategories(cart);
        Integer numberOfProducts = getDistinctProducts(cart);
        Double deliveryCost = (costPerDelivery * numberOfDeliveries) + (costPerProduct * numberOfProducts) + fixedCost;
        return ShoppingCart.updateDelivery(cart, deliveryCost);
    }

    private Integer getDistinctCategories(ShoppingCart cart) {
        assert cart != null : "cart can not be null";
        Map<Product, Integer> products = cart.getProducts();
        if (products != null) {
            return Math.toIntExact(
                products.keySet().stream().map(Product::getCategory).filter(Objects::nonNull).distinct().count());
        }
        return 0;
    }

    private Integer getDistinctProducts(ShoppingCart cart) {
        assert cart != null : "cart can not be null";
        Map<Product, Integer> products = cart.getProducts();
        if (products != null) {
            return Math.toIntExact(cart.getProducts().keySet().stream().filter(Objects::nonNull).distinct().count());
        }
        return 0;
    }

}
