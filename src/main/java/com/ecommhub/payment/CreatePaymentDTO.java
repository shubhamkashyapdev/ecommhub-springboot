package com.ecommhub.payment;

import com.ecommhub.payment.fields.PaymentMethod;
import com.ecommhub.payment.fields.PaymentStatus;

public record CreatePaymentDTO(String transactionId, String receipt, int paymentAmount, PaymentStatus paymentStatus, PaymentMethod paymentMethod, Long order, Long user) {
}
