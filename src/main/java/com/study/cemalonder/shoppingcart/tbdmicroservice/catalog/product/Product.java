package com.study.cemalonder.shoppingcart.tbdmicroservice.catalog.product;

import java.util.Objects;

import com.study.cemalonder.shoppingcart.tbdmicroservice.catalog.category.Category;

public final class Product {

    private final Double price;

    private final String title;

    private final Category category;

    public static Product of(String title, Double price, Category category) {
        return new Product(title, price, category);
    }

    public static Product of(Product product, Double price) {
        Objects.requireNonNull(product, "product");
        return new Product(product.getTitle(), price, product.getCategory());
    }

    public Category getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Product) {
            Product other = (Product) obj;
            return price.equals(other.price) && title.equals(other.title) && category.equals(other.category);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return price.hashCode() ^ title.hashCode() ^ category.hashCode();
    }

    @Override
    public String toString() {
        return "Product Title: " + title + ", Price: " + price + ", Category: [" + category + "]";
    }

    private Product(String title, Double price, Category category) {
        Objects.requireNonNull(title, "title");
        Objects.requireNonNull(price, "price");
        Objects.requireNonNull(category, "category");
        this.title = title;
        this.price = price;
        this.category = category;
    }
}
