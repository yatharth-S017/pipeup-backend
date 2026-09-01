package in.pipeup.backend.auth.service;

import in.pipeup.backend.auth.Dto.*;
import in.pipeup.backend.auth.entity.PasswordResetOtp;
import in.pipeup.backend.auth.repository.PasswordResetRepository;
import in.pipeup.backend.auth.repository.UserRepository;
import in.pipeup.backend.entity.Role;
import in.pipeup.backend.entity.User;
import in.pipeup.backend.exception.EmailAlreadyExistsException;
import in.pipeup.backend.security.CustomUserDetails;
import in.pipeup.backend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final OtpService otpService;
    private final EmailService emailService;
    private final PasswordResetRepository passwordResetRepository;




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
        user.setAccountType(request.getAccountType());

        User savedUser = userRepository.save(user);


        return new RegisterResponse(
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getEmail(),
                savedUser.getRole().name(),
                savedUser.getAccountType().name()
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
                user.getRole().name(),
                user.getAccountType().name()
        );
    }

    @Override
    public void forgotPassword(ForgotPasswordRequest request) {

        userRepository.findByEmail(request.getEmail()).ifPresent(user -> {

                    String otp = otpService.createOtp(user);

                    emailService.sendOtpEmail(user.getEmail(), otp);
                });
    }

    @Override
    public VerifyOtpResponse verifyOtp(VerifyOtpRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid OTP"));

        return otpService.verifyOtp(user, request.getOtp());
    }



    @Override
    public void resetPassword(ResetPasswordRequest request) {

        PasswordResetOtp resetOtp = passwordResetRepository
                .findByResetToken(request.getResetToken())
                .orElseThrow(() -> new RuntimeException("Invalid or expired reset token"));

        if (!resetOtp.isVerified()) {
            throw new RuntimeException("OTP verification required");
        }

        if (resetOtp.getResetTokenExpiresAt() == null || LocalDateTime.now().isAfter(resetOtp.getResetTokenExpiresAt())) {
            throw new RuntimeException("Reset token has expired");
        }

        User user = resetOtp.getUser();

        user.setPassword(
                passwordEncoder.encode(request.getNewPassword())
        );

        userRepository.save(user);

        passwordResetRepository.delete(resetOtp);
    }

}
