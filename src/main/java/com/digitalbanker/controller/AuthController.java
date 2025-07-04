package com.digitalbanker.controller;
import com.digitalbanker.dto.AuthRequest;
import com.digitalbanker.dto.RegisterRequest;
import com.digitalbanker.model.User;
import com.digitalbanker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import io.swagger.v3.oas.annotations.security.SecurityRequirement; // ✅ Needed
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService,
                          AuthenticationManager authenticationManager,
                          PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request) {
        String token = userService.login(request);
        return ResponseEntity.ok(token);
    }

    @SecurityRequirement(name = "bearerAuth")  // shows lock in Swagger
    @PostMapping("/auth/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // encode password
        user.setRole("USER"); // default role

        return ResponseEntity.ok(userService.register(user));
    }
}
