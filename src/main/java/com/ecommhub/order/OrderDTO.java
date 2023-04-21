package com.ecommhub.order;

import com.ecommhub.payment.fields.PaymentMethod;

import java.util.List;

public record OrderDTO(Long user, PaymentMethod paymentMethod) {
}
