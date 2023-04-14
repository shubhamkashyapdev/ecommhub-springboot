package com.ecommhub.user;

import com.ecommhub.cart.Cart;
import com.ecommhub.cart.CartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;

    @Test
    public void saveUser(){
        User user = User.builder()
                .email("dev@gmail.com")
                .password("12345678")
                .firstName("Dev")
                .lastName("1800")
                .build();
        User db_user = userRepository.save(user);

        // cart
        Cart cart = Cart.builder()
                .user(db_user)
                .build();
        Cart db_cart = cartRepository.save(cart);

        System.out.println("User " + db_user);
    }

    @Test
    public void printAllUsers(){
        List<User> users = userRepository.findAll();
        System.out.println(users);
    }

}