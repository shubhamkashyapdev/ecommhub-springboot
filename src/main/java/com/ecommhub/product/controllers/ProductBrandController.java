package com.ecommhub.product.controllers;

import com.ecommhub.error.NotFoundException;
import com.ecommhub.product.ProductBrand;
import com.ecommhub.product.records.ProductBrandDTO;
import com.ecommhub.product.service.ProductBrandService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Product Brand", description = "Product Brand API")
@RestController
@RequestMapping("/api/v1/product-brand")
@SecurityRequirement(name = "bearerAuth")
public class ProductBrandController {

    private final ProductBrandService productBrandService;

    public ProductBrandController(ProductBrandService productBrandService) {
        this.productBrandService = productBrandService;
    }

    @GetMapping
    public List<ProductBrand> fetchProductBrands(){
        return productBrandService.fetchProductBrands();
    }

    @PostMapping
    public ProductBrand saveProductBrand(@RequestBody() ProductBrandDTO productBrandDTO){
        return productBrandService.saveProductBrand(productBrandDTO);
    }

    @PutMapping("{id}")
    public ProductBrand updateProductBrand(@PathVariable("id") Long brandId, @RequestBody() ProductBrandDTO productBrandDTO) throws NotFoundException {
        return productBrandService.updateProductBrand(brandId, productBrandDTO);
    }

    @DeleteMapping("{id}")
    public ProductBrand deleteProductBrand(@PathVariable("id") Long brandId) throws NotFoundException {
        return productBrandService.deleteProductBrand(brandId);
    }

}
