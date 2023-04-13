package com.ecommhub.payment;

import com.ecommhub.payment.fields.PaymentMethod;
import com.ecommhub.payment.fields.PaymentStatus;
import com.ecommhub.user.User;
import com.ecommhub.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PaymentRepositoryTest {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void savePayment(){
        User user = User.builder()
                .email("dev@gmail.com")
                .password("12345678")
                .firstName("Dev")
                .lastName("1800")
                .build();
        userRepository.save(user);
        Payment payment = Payment.builder()
                .user(user)
                .paymentAmount(9999)
                .paymentMethod(PaymentMethod.UPI)
                .paymentStatus(PaymentStatus.PAID)
                .build();
        Payment db_payment = paymentRepository.save(payment);
        System.out.println(db_payment);
    }

    @Test
    public void fetchAllPayments(){
        List<Payment> payments = paymentRepository.findAll();
        System.out.println(payments);
    }
}