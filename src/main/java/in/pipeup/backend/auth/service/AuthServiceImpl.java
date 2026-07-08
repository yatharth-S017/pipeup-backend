package in.pipeup.backend.auth.service;

import in.pipeup.backend.auth.Dto.LoginRequest;
import in.pipeup.backend.auth.Dto.LoginResponse;
import in.pipeup.backend.auth.Dto.RegisterRequest;
import in.pipeup.backend.auth.Dto.RegisterResponse;
import in.pipeup.backend.auth.repository.UserRepository;
import in.pipeup.backend.entity.Role;
import in.pipeup.backend.entity.User;
import in.pipeup.backend.exception.EmailAlreadyExistsException;
import in.pipeup.backend.security.CustomUserDetails;
import in.pipeup.backend.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;



    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public RegisterResponse  registerUser(RegisterRequest request) {

        // Check whether email already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        // Create User Entity
        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        User savedUser = userRepository.save(user);

        return new RegisterResponse(
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getEmail(),
                savedUser.getRole().name()
        );
    }


    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        User user = customUserDetails.getUser();

        String token = jwtService.generateToken(user);

        return new LoginResponse(
                token,
                user.getFullName(),
                user.getEmail(),
                user.getRole().name()
        );
    }

}
