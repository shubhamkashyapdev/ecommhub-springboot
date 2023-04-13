package com.ecommhub.order;

import com.ecommhub.payment.Payment;
import com.ecommhub.payment.PaymentRepository;
import com.ecommhub.payment.fields.PaymentMethod;
import com.ecommhub.payment.fields.PaymentStatus;
import com.ecommhub.product.Product;
import com.ecommhub.product.repositories.ProductRepository;
import com.ecommhub.user.User;
import com.ecommhub.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private UserRepository userRepository;


    @Test
    public void saveOrder(){
        // user
        User user = User.builder()
                .email("dev@gmail.com")
                .password("12345678")
                .firstName("Dev")
                .lastName("1800")
                .build();
        User db_user = userRepository.save(user);

        // products
        Product product = Product.builder()
                .name("Shoes")
                .build();
        Product db_product = productRepository.save(product);
        Product product2 = Product.builder()
                .name("Shoes")
                .build();
        Product db_product2 = productRepository.save(product2);

        OrderProduct orderProduct1 = OrderProduct.builder()
                .product(db_product)
                .price(db_product.getPrice())
                .quantity(3)
                .build();
        OrderProduct orderProduct2 = OrderProduct.builder()
                .product(db_product2)
                .price(db_product2.getPrice())
                .quantity(5)
                .build();


        // payment
        Payment payment = Payment.builder()
                .paymentMethod(PaymentMethod.CASH)
                .paymentStatus(PaymentStatus.PENDING)
                .build();
        Payment db_payment = paymentRepository.save(payment);

        // order
        Order order = Order.builder()
                .payment(db_payment)
                .user(db_user)
                .orderProduct(List.of(orderProduct1, orderProduct2))
                .build();
        Order db_order = orderRepository.save(order);
        System.out.println(db_order);
    }
}