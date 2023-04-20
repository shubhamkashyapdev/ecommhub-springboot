package com.ecommhub.product.service;

import com.ecommhub.error.NotFoundException;
import com.ecommhub.product.ProductCategory;
import com.ecommhub.product.records.ProductCategoryDTO;
import com.ecommhub.product.repositories.ProductCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }


    @Override
    public List<ProductCategory> fetchProductCategories() {
        return productCategoryRepository.findAll();
    }

    @Override
    public ProductCategory saveProductCategory(ProductCategoryDTO productCategoryDTO) {
        ProductCategory productCategory = ProductCategory.builder()
                .name(productCategoryDTO.name())
                .build();
        return productCategoryRepository.save(productCategory);
    }

    @Override
    public ProductCategory updateProductCategory(Long categoryId, ProductCategoryDTO productCategoryDTO) throws NotFoundException {
        Optional<ProductCategory> productCategory = productCategoryRepository.findById(categoryId);
        ProductCategory db_productCategory = productCategory.orElseThrow(() -> new NotFoundException("Product Category Not Found With Provided ID"));
        db_productCategory.setName(productCategoryDTO.name());
        db_productCategory.setImage(productCategoryDTO.image());
        return productCategoryRepository.save(db_productCategory);
    }

    @Override
    public ProductCategory deleteProductCategory(Long categoryId) throws NotFoundException {
        Optional<ProductCategory> productCategory = productCategoryRepository.findById(categoryId);
        ProductCategory db_productCategory = productCategory.orElseThrow(() -> new NotFoundException("Product Category Not Found With Provided ID"));
        productCategoryRepository.deleteById(categoryId);
        return db_productCategory;
    }
}
