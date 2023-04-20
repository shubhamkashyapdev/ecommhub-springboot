package com.ecommhub.order;

import com.ecommhub.error.NotFoundException;

import java.util.List;

public interface OrderService {
    List<Order> fetchOrders();

    Order saveOrder(OrderDTO orderDTO) throws NotFoundException;
}
