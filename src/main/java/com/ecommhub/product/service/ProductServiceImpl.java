package com.ecommhub.product.service;

import com.ecommhub.blog.tag.PostTag;
import com.ecommhub.error.NotFoundException;
import com.ecommhub.product.Product;
import com.ecommhub.product.ProductBrand;
import com.ecommhub.product.ProductCategory;
import com.ecommhub.product.ProductTag;
import com.ecommhub.product.records.ProductDTO;
import com.ecommhub.product.repositories.ProductBrandRepository;
import com.ecommhub.product.repositories.ProductCategoryRepository;
import com.ecommhub.product.repositories.ProductRepository;
import com.ecommhub.product.repositories.ProductTagRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductBrandRepository productBrandRepository;
    private final ProductTagRepository productTagRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository, ProductBrandRepository productBrandRepository, ProductTagRepository productTagRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.productBrandRepository = productBrandRepository;
        this.productTagRepository = productTagRepository;
    }

    @Override
    public List<Product> fetchProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product saveProduct(ProductDTO productDTO) throws NotFoundException {
        Optional<ProductCategory> productCategory = productCategoryRepository.findById(productDTO.categoryId());
        ProductCategory db_productCategory = productCategory.orElseThrow(() -> new NotFoundException("Product Category Not Found By Provided ID"));

        Optional<ProductBrand> productBrand = productBrandRepository.findById(productDTO.brandId());
        ProductBrand db_productBrand = productBrand.orElseThrow(() -> new NotFoundException("Product Brand Not Found By Provided ID"));

        // tags
        List<ProductTag> tags = new ArrayList<>();
        List<ProductTag> tagList = productTagRepository.findAllById(productDTO.tags());
        if (tagList.size() < productDTO.tags().size()) {
            throw new NotFoundException("One or more tags not found");
        }
        tags.addAll(tagList);



        Product product = Product.builder()
                .name(productDTO.name())
                .sku(productDTO.sku())
                .description(productDTO.description())
                .image(productDTO.image())
                .price(productDTO.price())
                .productCategory(db_productCategory)
                .productTags(tags)
                .productBrand(db_productBrand)
                .build();
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, ProductDTO productDTO) throws NotFoundException {
        Optional<Product> product = productRepository.findById(productId);
        Product db_product = product.orElseThrow(() -> new NotFoundException("Product Not Found By Provided ID"));
        db_product.setName(productDTO.name());
        db_product.setSku(productDTO.sku());
        db_product.setImage(productDTO.image());
        db_product.setPrice(productDTO.price());
        db_product.setDescription(productDTO.description());
        return productRepository.save(db_product);
    }

    @Override
    public Product deleteProduct(Long productId) throws NotFoundException {
        Optional<Product> product = productRepository.findById(productId);
        Product db_product = product.orElseThrow(() -> new NotFoundException("Product Not Found By Provided ID"));
        productRepository.deleteById(productId);
        return db_product;
    }
}
