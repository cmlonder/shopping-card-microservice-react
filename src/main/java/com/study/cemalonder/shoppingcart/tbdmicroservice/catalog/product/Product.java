package com.study.cemalonder.shoppingcart.tbdmicroservice.catalog.product;

import lombok.Data;
import lombok.NonNull;

@Data
public class Product {

    @NonNull
    private String name;

    @NonNull
    private Double price;

    @NonNull
    private String title;

}
