package com.study.cemalonder.shoppingcart.tbdmicroservice.catalog.category;

import com.study.cemalonder.shoppingcart.tbdmicroservice.catalog.product.Product;

import lombok.Data;
import lombok.NonNull;

/**
 * Category is name of group that similar products belongs to. One {@link Product} must belong to a category.
 * Category may or may not have parent category.
 */
@Data
public class Category {

    @NonNull
    private String title;

    private Category parentCategory;
}
