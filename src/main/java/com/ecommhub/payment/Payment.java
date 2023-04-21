package com.ecommhub.payment;

import com.ecommhub.order.Order;
import com.ecommhub.payment.fields.PaymentMethod;
import com.ecommhub.payment.fields.PaymentStatus;
import com.ecommhub.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Please provide transaction ID")
    private String transactionId;
    @NotBlank(message = "Please provide the receipt")
    private String receipt;
    @NotBlank(message = "Please provide the payment amount")
    private int paymentAmount;


    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @NotBlank(message = "Please provide the payment method!")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @OneToOne
    @JoinColumn(
            name="order_id",
            referencedColumnName = "id"
    )
    private Order order;

    @ManyToOne
    @JoinColumn(
            name="user_id",
            referencedColumnName = "id"
    )
    private User user;
}
