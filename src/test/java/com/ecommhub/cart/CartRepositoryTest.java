package com.ecommhub.cart;

import com.ecommhub.product.Product;
import com.ecommhub.product.repositories.ProductRepository;
import com.ecommhub.user.User;
import com.ecommhub.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void saveCart(){
        User user = User.builder()
                .email("dev@gmail.com")
                .password("12345678")
                .firstName("Dev")
                .lastName("1800")
                .build();
        User db_user = userRepository.save(user);

        // product
        Product product1 = Product.builder()
                .name("Football")
                .build();
        Product db_product1 = productRepository.save(product1);

        CartProduct cartProduct1 = CartProduct.builder()
                .product(db_product1)
                .build();
        CartProduct cartProduct2 = CartProduct.builder()
                .product(db_product1)
                .build();

        Cart cart = Cart.builder()
                .user(user)
                .cartProducts(List.of(cartProduct1))
                .build();
        Cart db_cart = cartRepository.save(cart);
        System.out.println(db_cart);
    }

}