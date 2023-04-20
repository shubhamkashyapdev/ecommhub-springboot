package com.ecommhub.cart;

import com.ecommhub.error.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<Cart> fetchAllCarts(){
        return cartService.fetchAllCarts();
    }

    @PutMapping("/add/{cartId}")
    public Cart addProductToCart(@PathVariable("cartId") Long cartId, @RequestBody() CartProductDTO cartProductDTO) throws NotFoundException {
        return cartService.addProductToCart(cartId, cartProductDTO);
    }

    @PutMapping("/remove/{cartId}")
    public Cart removeProductFromCart(@PathVariable("cartId") Long cartId, @RequestBody() RemoveCartProductDTO removeCartProductDTO) throws NotFoundException {
        return cartService.removeProductFromCart(cartId, removeCartProductDTO);
    }


}
