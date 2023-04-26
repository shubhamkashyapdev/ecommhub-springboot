package com.ecommhub.user;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> fetchAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/me")
    public Optional<UserOut> getCurrentLoggedInUser(@AuthenticationPrincipal User user) {
        System.out.println(user);
        UserOut userOut = new UserOut(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getCartId(), user.getRole());
        return Optional.of(userOut);
    }

}
