package com.ecommhub.cart;

import com.ecommhub.error.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface CartService {
    List<Cart> fetchAllCarts();

    Cart addProductToCart(Long cartId, CartProductDTO cartProductDTO) throws NotFoundException;

    Cart removeProductFromCart(Long cartId, RemoveCartProductDTO removeCartProductDTO) throws NotFoundException;

    Optional<Cart> fetchCart(Long cartId);
}
