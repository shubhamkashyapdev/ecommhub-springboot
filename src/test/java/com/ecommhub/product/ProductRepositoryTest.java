package com.ecommhub.product;

import com.ecommhub.blog.tag.PostTag;
import com.ecommhub.product.fields.ProductAttribute;
import com.ecommhub.product.fields.ProductColor;
import com.ecommhub.product.fields.ProductSize;
import com.ecommhub.product.fields.SaleType;
import com.ecommhub.product.repositories.ProductBrandRepository;
import com.ecommhub.product.repositories.ProductCategoryRepository;
import com.ecommhub.product.repositories.ProductRepository;
import com.ecommhub.product.repositories.ProductTagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private ProductBrandRepository productBrandRepository;
    @Autowired
    private ProductTagRepository productTagRepository;
    @Autowired
    private ShopRepository shopRepository;

    @Test
    public void saveProduct(){
        // product brand
        ProductBrand productBrand1 = ProductBrand.builder()
                .name("Nike")
                .build();
        ProductBrand db_productBrand1 = productBrandRepository.save(productBrand1);

        // product tags
        ProductTag productTag1 = ProductTag.builder()
                .name("Sneakers")
                .build();
        ProductTag db_productTag1 = productTagRepository.save(productTag1);
        List<ProductTag> productTags = new ArrayList<ProductTag>();
        productTags.add(productTag1);

        // product category
        ProductCategory productCategory1 = ProductCategory.builder()
                .name("Shoes")
                .build();
        ProductCategory db_productCategory = productCategoryRepository.save(productCategory1);

        // product shop
        Shop shop = Shop.builder()
                .name("Elante")
                .isActive(true)
                .build();
        Shop db_shop = shopRepository.save(shop);

        // product attributes
        ProductColor productColor1 = ProductColor.builder()
                .name("Jet Black")
                .hexcode("#333333")
                .build();
        ProductColor productColor2 = ProductColor.builder()
                .name("White")
                .hexcode("#ffffff")
                .build();
        ProductAttribute productAttribute = ProductAttribute.builder()
                 .productColors(List.of(productColor1, productColor2)) // @todo - to be fixed
                .productSizes(List.of(ProductSize.valueOf("S"), ProductSize.valueOf("M")))
                .build();

        // product
        Product product = Product.builder()
                .name("Heavy Metal Shoes")
                .description("Sketchers shoes with heavy metal")
                .price(9999)
                .saleType(SaleType.FLAT)
                .flatSale(299)
                .keywords(List.of("Sketchers", "Shoes", "Black Shoes"))
                .productAttribute(productAttribute)
                .productBrand(db_productBrand1)
                .productCategory(db_productCategory)
                .productTags(productTags)
                .shop(db_shop)
                .build();
        Product db_product = productRepository.save(product);
        System.out.println(db_product);
    }

    @Test
    public void fetchAllProducts(){
        List<Product> products = productRepository.findAll();
        System.out.println(products);
    }

}