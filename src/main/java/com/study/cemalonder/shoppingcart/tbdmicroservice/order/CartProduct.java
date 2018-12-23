package com.study.cemalonder.shoppingcart.tbdmicroservice.order;

import com.study.cemalonder.shoppingcart.tbdmicroservice.catalog.product.Product;

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
