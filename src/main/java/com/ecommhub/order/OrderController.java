package com.ecommhub.order;

import com.ecommhub.error.NotFoundException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Order", description = "Order API")
@RestController
@RequestMapping("/api/v1/order")
@SecurityRequirement(name = "bearerAuth")
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
