package com.ecommhub.cart;

import com.ecommhub.error.NotFoundException;
import com.ecommhub.product.Product;
import com.ecommhub.product.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartProductRepository cartProductRepository;

    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository, CartProductRepository cartProductRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartProductRepository = cartProductRepository;
    }

    @Override
    public List<Cart> fetchAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart addProductToCart(Long cartId, CartProductDTO cartProductDTO) throws NotFoundException {
        // check quantity
        if(cartProductDTO.quantity() <= 0){
            throw new RuntimeException("Invalid Product Quantity");
        }
        Optional<Cart> cart = cartRepository.findById(cartId);
        Cart db_cart = cart.orElseThrow(() -> new NotFoundException("Cart Not Found By Provided ID"));

        Optional<Product> product = productRepository.findById(cartProductDTO.productId());
        Product db_product = product.orElseThrow(() -> new NotFoundException("Product Not Found By Provided ID"));

        CartProduct cartProduct = CartProduct.builder()
                .quantity(cartProductDTO.quantity())
                .product(db_product)
                .cart(db_cart)
                .build();
        CartProduct db_cartProduct = cartProductRepository.save(cartProduct);

        db_cart.getCartProducts().add(db_cartProduct);
        return cartRepository.save(db_cart);
    }

    @Override
    public Cart removeProductFromCart(Long cartId, RemoveCartProductDTO removeCartProductDTO) throws NotFoundException {
        Optional<Cart> cart = cartRepository.findById(cartId);
        Cart db_cart = cart.orElseThrow(() -> new NotFoundException("Cart Not Found By Provided ID"));

        // find index of the db_product
        int index = db_cart.getCartProducts().indexOf(
                db_cart.getCartProducts().stream()
                        .filter(p -> p.getId().equals(removeCartProductDTO.productId()))
                        .findFirst()
                        .orElseThrow(() -> new NotFoundException("Product Not Found In Cart"))
        );
        System.out.println("INDEX: " + index); // index = 0
        // remove the item from db_cart
        System.out.println(db_cart.getCartProducts().size()); // size = 1
        // db_cart.getCartProducts().remove(index);
        List<CartProduct> cartProducts = db_cart.getCartProducts();
        System.out.println(cartProducts);
        cartProducts.remove(index);
        db_cart.setCartProducts(cartProducts);
        System.out.println(db_cart.getCartProducts().size()); // size = 0
        // save the update db_cart
        cartRepository.save(db_cart);
        return db_cart;
    }

    @Override
    public Optional<Cart> fetchCart(Long cartId) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        return cart;
    }
}
