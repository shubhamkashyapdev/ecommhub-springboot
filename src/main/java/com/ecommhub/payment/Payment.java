package com.ecommhub.payment;

import com.ecommhub.order.Order;
import com.ecommhub.payment.fields.PaymentMethod;
import com.ecommhub.payment.fields.PaymentStatus;
import com.ecommhub.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    @SequenceGenerator(
            name = "payment_id_sequence",
            sequenceName = "payment_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "payment_id_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String orderCreationId;
    private String razorpayPaymentId;
    private String razorpaySignature;
    private String receipt;
    private int paymentAmount;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(
            name="user_id",
            referencedColumnName = "id"
    )
    private User user;
}
