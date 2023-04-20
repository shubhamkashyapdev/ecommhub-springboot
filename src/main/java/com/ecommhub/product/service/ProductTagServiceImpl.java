package com.ecommhub.product.service;

import com.ecommhub.error.NotFoundException;
import com.ecommhub.product.ProductTag;
import com.ecommhub.product.records.ProductTagDTO;
import com.ecommhub.product.repositories.ProductRepository;
import com.ecommhub.product.repositories.ProductTagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductTagServiceImpl implements ProductTagService {

    private final ProductTagRepository productTagRepository;

    public ProductTagServiceImpl(ProductTagRepository productTagRepository) {
        this.productTagRepository = productTagRepository;
    }

    @Override
    public List<ProductTag> fetchProductTags() {
        return productTagRepository.findAll();
    }

    @Override
    public ProductTag saveProductTag(ProductTagDTO productTagDTO) {
        ProductTag productTag = ProductTag.builder()
                .name(productTagDTO.name())
                .build();
        return productTagRepository.save(productTag);
    }

    @Override
    public ProductTag updateProductTag(Long tagId, ProductTagDTO productTagDTO) throws NotFoundException {
        Optional<ProductTag> productTag = productTagRepository.findById(tagId);
        ProductTag db_productTag = productTag.orElseThrow(() -> new NotFoundException("Product Tag Not Found With Provided ID"));
        db_productTag.setName(productTagDTO.name());
        return productTagRepository.save(db_productTag);
    }

    @Override
    public ProductTag deleteProductTag(Long tagId) throws NotFoundException {
        Optional<ProductTag> productTag = productTagRepository.findById(tagId);
        ProductTag db_productTag = productTag.orElseThrow(() -> new NotFoundException("Product Tag Not Found With Provided ID"));
        productTagRepository.deleteById(tagId);
        return db_productTag;
    }
}
