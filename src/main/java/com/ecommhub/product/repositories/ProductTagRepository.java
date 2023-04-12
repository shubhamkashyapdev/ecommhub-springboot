package com.ecommhub.product.repositories;

import com.ecommhub.product.ProductTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTagRepository extends JpaRepository<ProductTag, Long> {
}
