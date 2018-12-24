package com.study.cemalonder.shoppingcart.tbdmicroservice.catalog.category;

import java.util.Objects;

public final class Category {

    private final String title;

    private Category parentCategory;

    public static Category of(String title) {
        return new Category(title);
    }

    public static Category of(String title, Category parentCategory) {
        return new Category(title, parentCategory);
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Category) {
            Category other = (Category) obj;
            if (parentCategory != null && other.parentCategory != null) {
                return title.equals(other.title) && parentCategory.equals(other.parentCategory);
            }else if (parentCategory == null && other.parentCategory == null) {
                return title.equals(other.title);
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (Objects.nonNull(parentCategory)) {
            return title.hashCode() ^ parentCategory.hashCode();
        }
        return title.hashCode();
    }

    @Override
    public String toString() {
        if (Objects.nonNull(parentCategory)) {
            return "Category Title: " + title + ", Parent Category: [" + parentCategory + "]";
        }
        return "Category Title: " + title;
    }

    private Category(String title) {
        Objects.requireNonNull(title, "title");
        this.title = title;
    }

    private Category(String title, Category parentCategory) {
        this(title);
        Objects.requireNonNull(parentCategory, "parentCategory");
        this.parentCategory = parentCategory;
    }
}
