package com.study.cemalonder.shoppingcart.tbdmicroservice.catalog.model;

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
