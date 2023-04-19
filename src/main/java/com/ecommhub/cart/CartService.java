package com.ecommhub.cart;

import com.ecommhub.error.NotFoundException;

import java.util.List;

public interface CartService {
    List<Cart> fetchAllCarts();

    Cart addProductToCart(Long cartId, CartProductDTO cartProductDTO) throws NotFoundException;
}
