package com.ecommhub.product.service;

import com.ecommhub.error.NotFoundException;
import com.ecommhub.product.ProductTag;
import com.ecommhub.product.records.ProductTagDTO;

import java.util.List;

public interface ProductTagService {
    List<ProductTag> fetchProductTags();

    ProductTag saveProductTag(ProductTagDTO productTagDTO);

    ProductTag updateProductTag(Long tagId, ProductTagDTO productTagDTO) throws NotFoundException;

    ProductTag deleteProductTag(Long tagId) throws NotFoundException;
}
