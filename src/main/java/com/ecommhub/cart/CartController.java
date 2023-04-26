package com.ecommhub.cart;

import com.ecommhub.error.NotFoundException;
import com.ecommhub.user.User;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cart")
@SecurityRequirement(name = "bearerAuth")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/list")
    public List<Cart> fetchAllCarts(){
        return cartService.fetchAllCarts();
    }

    @GetMapping
    public ResponseEntity<Optional<Cart>> getCurrentUserCart(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(cartService.fetchCart(user.getCartId()));
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
