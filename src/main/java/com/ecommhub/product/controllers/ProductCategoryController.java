package com.ecommhub.product.controllers;

import com.ecommhub.error.NotFoundException;
import com.ecommhub.product.ProductCategory;
import com.ecommhub.product.records.ProductCategoryDTO;
import com.ecommhub.product.service.ProductCategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product-category")
@SecurityRequirement(name = "bearerAuth")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public List<ProductCategory> fetchProductCategories(){
        return productCategoryService.fetchProductCategories();
    }

    @PostMapping
    public ProductCategory saveProductCategory(@RequestBody() ProductCategoryDTO productCategoryDTO){
        return productCategoryService.saveProductCategory(productCategoryDTO);
    }

    @PutMapping("{id}")
    public ProductCategory updateProductCategory(@PathVariable("id") Long categoryId, @RequestBody() ProductCategoryDTO productCategoryDTO) throws NotFoundException {
        return productCategoryService.updateProductCategory(categoryId, productCategoryDTO);
    }

    @DeleteMapping("{id}")
    public ProductCategory deleteProductCategory(@PathVariable("id") Long categoryId) throws NotFoundException {
        return productCategoryService.deleteProductCategory(categoryId);
    }

}
