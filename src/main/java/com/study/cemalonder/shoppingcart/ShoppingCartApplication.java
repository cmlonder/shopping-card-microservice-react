package com.study.cemalonder.shoppingcart;

import com.study.cemalonder.shoppingcart.tbdmicroservice.catalog.category.Category;
import com.study.cemalonder.shoppingcart.tbdmicroservice.catalog.product.Product;
import com.study.cemalonder.shoppingcart.tbdmicroservice.delivery.DeliveryCostCalculator;
import com.study.cemalonder.shoppingcart.tbdmicroservice.discount.Campaign;
import com.study.cemalonder.shoppingcart.tbdmicroservice.discount.CampaignDiscountCriteria;
import com.study.cemalonder.shoppingcart.tbdmicroservice.discount.Coupon;
import com.study.cemalonder.shoppingcart.tbdmicroservice.discount.CouponDiscountCriteria;
import com.study.cemalonder.shoppingcart.tbdmicroservice.discount.DiscountService;
import com.study.cemalonder.shoppingcart.tbdmicroservice.discount.DiscountType;
import com.study.cemalonder.shoppingcart.tbdmicroservice.order.ShoppingCart;

public class ShoppingCartApplication {

    public static void main(String[] args) {
        Category food = Category.of("food");
        Category woman = Category.of("woman");
        Category shirt = Category.of("shirt", woman);

        Product apple = Product.of("Apple", 100.0, food);
        Product almond = Product.of("Almond", 150.0, food);
        Product armani = Product.of("Armani", 10.0, shirt);
        Product defacto = Product.of("Defacto", 12.0, shirt);

        ShoppingCart cart = new ShoppingCart();

        cart = cart.addItem(apple, 4);
        cart = cart.addItem(almond, 1);
        cart = cart.addItem(armani, 2);
        cart = cart.addItem(defacto, 2);

        Campaign campaign1 = Campaign.of(CampaignDiscountCriteria.of(food, 3), 20.0, DiscountType.RATE);
        Campaign campaign2 = Campaign.of(CampaignDiscountCriteria.of(food, 5), 50.0, DiscountType.RATE);
        Campaign campaign3 = Campaign.of(CampaignDiscountCriteria.of(food, 5), 5.0, DiscountType.AMOUNT);
        Campaign campaign4 = Campaign.of(CampaignDiscountCriteria.of(shirt, 1), 1.0, DiscountType.AMOUNT);
        Campaign campaign5 = Campaign.of(CampaignDiscountCriteria.of(woman, 1), 1.0, DiscountType.AMOUNT);

        Coupon coupon = Coupon.of(CouponDiscountCriteria.of(10.0), 100.0, DiscountType.AMOUNT);

        DiscountService discountService = new DiscountService();
        cart = discountService.applyDiscounts(cart, coupon, campaign1, campaign2, campaign3, campaign4, campaign5);

        DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator(10.0, 15.0, 2.99);
        cart = deliveryCostCalculator.calculateFor(cart);

        cart.print();
    }

}

