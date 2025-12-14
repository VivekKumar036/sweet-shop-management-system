import com.sweetshop.backend.auth.dto.RegisterRequest;
import com.sweetshop.backend.auth.entity.User;
import com.sweetshop.backend.auth.jwt.JwtUtil;
import com.sweetshop.backend.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

   @PostMapping("/register")
public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
    try {
        return ResponseEntity.ok(
            authService.register(
                request.getFullName(),
                request.getEmail(),
                request.getPassword()
            )
        );
    } catch (IllegalArgumentException e) {
        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }
}

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> body) {

        User user = authService.login(body.get("email"), body.get("password"));
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

        return ResponseEntity.ok(Map.of("token", token));
    }
}
