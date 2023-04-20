package com.ecommhub.order;

import com.ecommhub.error.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> fetchOrders(){
        return orderService.fetchOrders();
    }

    @PostMapping
    public Order saveOrder(@RequestBody() OrderDTO orderDTO) throws NotFoundException {
        return orderService.saveOrder(orderDTO);
    }
}
