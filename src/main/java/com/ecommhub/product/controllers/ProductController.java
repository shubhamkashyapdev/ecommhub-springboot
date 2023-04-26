package com.ecommhub.product.controllers;

import com.ecommhub.error.NotFoundException;
import com.ecommhub.product.Product;
import com.ecommhub.product.records.ProductDTO;
import com.ecommhub.product.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@SecurityRequirement(name = "bearerAuth")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> fetchProducts(){
        return productService.fetchProducts();
    }

    @PostMapping
    public Product saveProduct(@RequestBody() ProductDTO productDTO) throws NotFoundException {
        return productService.saveProduct(productDTO);
    }

    @PutMapping("{id}")
    public Product updateProduct(@PathVariable("id") Long productId, @RequestBody() ProductDTO productDTO) throws NotFoundException {
        return productService.updateProduct(productId, productDTO);
    }

    @DeleteMapping("{id}")
    public Product deleteProduct(@PathVariable("id") Long productId) throws NotFoundException {
        return productService.deleteProduct(productId);
    }
}
