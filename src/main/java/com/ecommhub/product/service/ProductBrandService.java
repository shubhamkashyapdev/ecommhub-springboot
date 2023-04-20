package com.ecommhub.product.service;

import com.ecommhub.error.NotFoundException;
import com.ecommhub.product.ProductBrand;
import com.ecommhub.product.records.ProductBrandDTO;

import java.util.List;

public interface ProductBrandService {
    List<ProductBrand> fetchProductBrands();

    ProductBrand saveProductBrand(ProductBrandDTO productBrandDTO);

    ProductBrand updateProductBrand(Long brandId, ProductBrandDTO productBrandDTO) throws NotFoundException;

    ProductBrand deleteProductBrand(Long brandId) throws NotFoundException;
}
