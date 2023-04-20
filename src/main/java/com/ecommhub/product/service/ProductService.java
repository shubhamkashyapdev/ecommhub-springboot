package com.ecommhub.product.service;

import com.ecommhub.error.NotFoundException;
import com.ecommhub.product.Product;
import com.ecommhub.product.records.ProductDTO;

import java.util.List;

public interface ProductService {
    List<Product> fetchProducts();

    Product saveProduct(ProductDTO productDTO) throws NotFoundException;

    Product updateProduct(Long productId, ProductDTO productDTO) throws NotFoundException;

    Product deleteProduct(Long productId) throws NotFoundException;
}
