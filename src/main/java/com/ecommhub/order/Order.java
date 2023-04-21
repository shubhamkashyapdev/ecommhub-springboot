package com.ecommhub.order;

import com.ecommhub.payment.Payment;
import com.ecommhub.payment.fields.PaymentMethod;
import com.ecommhub.product.Product;
import com.ecommhub.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class Order {

    @Id
    @SequenceGenerator(
            name = "order_id_sequence",
            sequenceName = "order_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "order_id_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private int amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(
            name="user_id",
            referencedColumnName = "id"
    )
    private User user;

    @OneToOne
    @JoinColumn(
            name="payment_id",
            referencedColumnName = "id"
    )
    private Payment payment;

    @OneToMany(
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name="order_product_id",
            referencedColumnName = "id"
    )
    private List<OrderProduct> orderProducts;
}
