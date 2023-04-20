package com.ecommhub.product.service;

import com.ecommhub.error.NotFoundException;
import com.ecommhub.product.ProductBrand;
import com.ecommhub.product.records.ProductBrandDTO;
import com.ecommhub.product.repositories.ProductBrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductBrandServiceImpl implements ProductBrandService {

    private final ProductBrandRepository productBrandRepository;

    public ProductBrandServiceImpl(ProductBrandRepository productBrandRepository) {
        this.productBrandRepository = productBrandRepository;
    }

    @Override
    public List<ProductBrand> fetchProductBrands() {
        return productBrandRepository.findAll();
    }

    @Override
    public ProductBrand saveProductBrand(ProductBrandDTO productBrandDTO) {
        ProductBrand productBrand = ProductBrand.builder()
                .name(productBrandDTO.name())
                .image(productBrandDTO.image())
                .build();

        return productBrandRepository.save(productBrand);
    }

    @Override
    public ProductBrand updateProductBrand(Long brandId, ProductBrandDTO productBrandDTO) throws NotFoundException {
        Optional<ProductBrand> productBrand = productBrandRepository.findById(brandId);
        ProductBrand db_productBrand = productBrand.orElseThrow(() -> new NotFoundException("Product Brand Not Found By Provided Id"));
        db_productBrand.setName(productBrandDTO.name());
        db_productBrand.setImage(productBrandDTO.image());
        return productBrandRepository.save(db_productBrand);
    }

    @Override
    public ProductBrand deleteProductBrand(Long brandId) throws NotFoundException {
        Optional<ProductBrand> productBrand = productBrandRepository.findById(brandId);
        ProductBrand db_productBrand = productBrand.orElseThrow(() -> new NotFoundException("Product Brand Not Found By Provided Id"));
        productBrandRepository.deleteById(brandId);
        return db_productBrand;
    }
}
