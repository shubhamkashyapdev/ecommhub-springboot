package com.ecommhub.cart;

import com.ecommhub.product.Product;
import com.ecommhub.product.repositories.ProductRepository;
import com.ecommhub.user.User;
import com.ecommhub.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartProductRepository cartProductRepository;

    @Test
    public void saveCart(){
        User user = User.builder()
                .email("dev@gmail.com")
                .password("12345678")
                .firstName("Dev")
                .lastName("1800")
                .build();
        User db_user = userRepository.save(user);

        // cart
        Cart cart = Cart.builder()
                .user(user)
                .build();
        Cart db_cart = cartRepository.save(cart);
        System.out.println("Cart:" + db_cart);

        // product
        Product product1 = Product.builder()
                .name("Football")
                .price(1000)
                .build();
        Product db_product1 = productRepository.save(product1);

        CartProduct cartProduct1 = CartProduct.builder()
                .product(db_product1)
                .quantity(3)
                .cart(db_cart)
                .build();
        CartProduct cartProduct2 = CartProduct.builder()
                .product(db_product1)
                .quantity(2)
                .cart(db_cart)
                .build();
        CartProduct db_cartProduct1 = cartProductRepository.save(cartProduct1);
        CartProduct db_cartProduct2 = cartProductRepository.save(cartProduct2);

        List<Cart> db_cart_with_products = cartRepository.findAll();
        System.out.println(db_cart_with_products);

    }

}