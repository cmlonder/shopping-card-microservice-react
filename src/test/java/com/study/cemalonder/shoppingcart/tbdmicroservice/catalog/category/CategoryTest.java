package com.study.cemalonder.shoppingcart.tbdmicroservice.catalog.category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.study.cemalonder.shoppingcart.tbdmicroservice.catalog.product.Product;

public class CategoryTest {

    @Test(expected = NullPointerException.class)
    public void of_WhenNullTitle_ShouldThrowException() {
        Category.of(null);
    }

    @Test(expected = NullPointerException.class)
    public void of_WhenNullParentCategory_ShouldThrowException() {
        Category.of("first", null);
    }

    @Test
    public void getParentCategory_ShouldReturnParentCategory() {
        Category baseCategory = Category.of("base");
        Category firstCategory = Category.of("second", baseCategory);
        assertEquals(baseCategory, firstCategory.getParentCategory());
    }

    @Test
    public void getTitle_ShouldReturnTitle() {
        Category firstCategory = Category.of("first");
        assertEquals("first", firstCategory.getTitle());
    }

    @Test
    public void equals_WhenNullCategory_ShouldReturnFalse() {
        Category firsTCategory = Category.of("first");
        assertNotEquals(null, firsTCategory);
    }

    @Test
    public void equals_WhenDifferentTitle_ShouldReturnFalse() {
        Category firsTCategory = Category.of("first");
        Category secondCategory = Category.of("second");
        assertNotEquals(firsTCategory, secondCategory);
    }

    @Test
    public void equals_WhenDifferentTypeObject_ShouldReturnFalse() {
        Category firsTCategory = Category.of("first");
        Product product = Product.of("first", 10.0, firsTCategory);
        assertNotEquals(firsTCategory, product);
    }

    @Test
    public void equals_WhenSameTitleAndDifferentParentCategory_ShouldReturnFalse() {
        Category baseCategory = Category.of("base");
        Category baseCategory2 = Category.of("base2");
        Category firsTCategory = Category.of("first", baseCategory);
        Category secondCategory = Category.of("first", baseCategory2);
        assertNotEquals(firsTCategory, secondCategory);
    }

    @Test
    public void equals_WhenDifferentTitleAndSameParentCategory_ShouldReturnFalse() {
        Category baseCategory = Category.of("base");
        Category firsTCategory = Category.of("first", baseCategory);
        Category secondCategory = Category.of("second", baseCategory);
        assertNotEquals(firsTCategory, secondCategory);
    }

    @Test
    public void equals_WhenSameTitleAndBothNoParentCategory_ShouldReturnTrue() {
        Category firsTCategory = Category.of("first");
        Category secondCategory = Category.of("first");
        assertEquals(firsTCategory, secondCategory);
    }

    @Test
    public void equals_WhenSameTitleAndSameParentCategory_ShouldReturnTrue() {
        Category baseCategory = Category.of("base");
        Category firsTCategory = Category.of("first", baseCategory);
        Category secondCategory = Category.of("first", baseCategory);
        assertEquals(firsTCategory, secondCategory);
    }
}