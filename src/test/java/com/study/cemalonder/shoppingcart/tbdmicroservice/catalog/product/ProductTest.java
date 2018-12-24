package com.study.cemalonder.shoppingcart.tbdmicroservice.catalog.product;

import static org.junit.Assert.*;

import org.junit.Test;

import com.study.cemalonder.shoppingcart.tbdmicroservice.catalog.category.Category;

public class ProductTest {

    @Test(expected = NullPointerException.class)
    public void of_GivenNullPrice_ShouldThrowException() {
        Product.of("title", null, Category.of("title"));
    }

    @Test(expected = NullPointerException.class)
    public void of_GivenNullTitle_ShouldThrowException() {
        Product.of(null, 10.0, Category.of("title"));
    }

    @Test(expected = NullPointerException.class)
    public void of_GivenNullCategory_ShouldThrowException() {
        Product.of("title", 10.0, null);
    }
    @Test(expected = NullPointerException.class)
    public void of_GivenNullProduct_ShouldThrowException() {
        Product.of(null, 10.0);
    }

    @Test
    public void of_GivenTitleAndPriceAndCategory_ShouldReturnProduct() {
        Product.of("title", 10.0, Category.of("title"));
    }
}