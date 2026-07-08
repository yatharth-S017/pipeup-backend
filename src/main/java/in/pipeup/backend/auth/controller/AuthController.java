package in.pipeup.backend.auth.controller;

import in.pipeup.backend.auth.Dto.LoginRequest;
import in.pipeup.backend.auth.Dto.LoginResponse;
import in.pipeup.backend.auth.Dto.RegisterRequest;
import in.pipeup.backend.auth.Dto.RegisterResponse;
import in.pipeup.backend.auth.service.AuthServiceImpl;
import in.pipeup.backend.auth.service.IAuthService;
import in.pipeup.backend.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(
            @RequestBody RegisterRequest request) {
        RegisterResponse response = authService.registerUser(request);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/demo")
    public ResponseEntity<String> demo() {
        return ResponseEntity.ok(
                "Welcome to PipeUp! JWT Authentication is working successfully."
        );
    }

}
