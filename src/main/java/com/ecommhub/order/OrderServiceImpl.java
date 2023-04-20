package com.ecommhub.order;

import com.ecommhub.cart.Cart;
import com.ecommhub.cart.CartProduct;
import com.ecommhub.cart.CartRepository;
import com.ecommhub.error.NotFoundException;
import com.ecommhub.user.User;
import com.ecommhub.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;

    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderProductRepository orderProductRepository, UserRepository userRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public List<Order> fetchOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order saveOrder(OrderDTO orderDTO) throws NotFoundException {
        Optional<User> user = userRepository.findById(orderDTO.user());
        User db_user = user.orElseThrow(() -> new NotFoundException("User Not Found By Provided ID"));

        Optional<Cart> cart = cartRepository.findById(db_user.getCartId());
        Cart db_cart = cart.orElseThrow(() -> new NotFoundException("Cart Not Found By Provided ID"));

        int products_price = 0;
        // order products
        List<OrderProduct> orderProducts = new ArrayList<>();
        for(CartProduct cartProduct : db_cart.getCartProducts()){
            OrderProduct orderProduct = OrderProduct
                    .builder()
                    .product(cartProduct.getProduct())
                    .price(cartProduct.getProduct().getPrice())
                    .quantity(cartProduct.getQuantity())
                    .build();
            orderProducts.add(orderProduct);
            products_price += cartProduct.getProduct().getPrice();
        }

        Order order = Order.builder()
                .user(db_user)
                .orderProducts(orderProducts)
                .amount(products_price)
                .build();
        return orderRepository.save(order);
    }
}
