package com.study.cemalonder.shoppingcart.tbdmicroservice.order.model;

import com.study.cemalonder.shoppingcart.tbdmicroservice.catalog.model.Product;

import lombok.Data;
import lombok.NonNull;

@Data
public class CartProduct {

    @NonNull
    private Integer discountedProductPrice;

    @NonNull
    private Product product;

    @NonNull
    private Integer quantity;

    @NonNull
    private Integer totalProductPrice;
}
