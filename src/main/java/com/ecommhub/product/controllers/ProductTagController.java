package com.ecommhub.product.controllers;

import com.ecommhub.error.NotFoundException;
import com.ecommhub.product.ProductTag;
import com.ecommhub.product.records.ProductTagDTO;
import com.ecommhub.product.service.ProductTagService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product-tag")
@SecurityRequirement(name = "bearerAuth")
public class ProductTagController {

    private final ProductTagService productTagService;

    public ProductTagController(ProductTagService productTagService) {
        this.productTagService = productTagService;
    }

    @GetMapping
    public List<ProductTag> fetchProductTags(){
        return productTagService.fetchProductTags();
    }

    @PostMapping
    public ProductTag saveProductTag(@RequestBody() ProductTagDTO productTagDTO){
        return productTagService.saveProductTag(productTagDTO);
    }

    @PutMapping("{id}")
    public ProductTag updateProductTag(@PathVariable("id") Long tagId, @RequestBody() ProductTagDTO productTagDTO) throws NotFoundException {
        return productTagService.updateProductTag(tagId, productTagDTO);
    }

    @DeleteMapping("{id}")
    public ProductTag deleteProductTag(@PathVariable("id") Long tagId) throws NotFoundException {
        return productTagService.deleteProductTag(tagId);
    }
}
