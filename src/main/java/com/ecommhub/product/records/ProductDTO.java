package com.ecommhub.product.records;

import java.util.List;

public record ProductDTO(String name, String sku, String image, String description, int price, Long brandId, Long categoryId, List<Long> tags) {
}
