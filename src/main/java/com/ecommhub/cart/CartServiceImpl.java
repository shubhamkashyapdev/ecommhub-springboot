package com.ecommhub.cart;

import com.ecommhub.error.NotFoundException;
import com.ecommhub.product.Product;
import com.ecommhub.product.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Cart> fetchAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart addProductToCart(Long cartId, CartProductDTO cartProductDTO) throws NotFoundException {
        // check quantity
        if(cartProductDTO.quantity() <= 0){
            throw new RuntimeException("Invalid Product Quantity");
        }
        Optional<Product> product = productRepository.findById(cartProductDTO.productId());
        Product db_product = product.orElseThrow(() -> new NotFoundException("Product Not Found By Provided ID"));
        CartProduct cartProduct = CartProduct.builder()
                .quantity(cartProductDTO.quantity())
                .product(db_product)
                .build();
        Optional<Cart> cart = cartRepository.findById(cartId);
        Cart db_cart = cart.orElseThrow(() -> new NotFoundException("Cart Not Found By Provided ID"));
        db_cart.getCartProducts().add(cartProduct);
        return cartRepository.save(db_cart);
    }
}
