package com.ecommhub.product.service;

import com.ecommhub.error.NotFoundException;
import com.ecommhub.product.ProductCategory;
import com.ecommhub.product.records.ProductCategoryDTO;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> fetchProductCategories();

    ProductCategory saveProductCategory(ProductCategoryDTO productCategoryDTO);

    ProductCategory updateProductCategory(Long categoryId, ProductCategoryDTO productCategoryDTO) throws NotFoundException;

    ProductCategory deleteProductCategory(Long categoryId) throws NotFoundException;
}
