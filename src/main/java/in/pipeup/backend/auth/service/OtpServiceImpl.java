package in.pipeup.backend.auth.service;

import in.pipeup.backend.auth.Dto.VerifyOtpResponse;
import in.pipeup.backend.auth.entity.PasswordResetOtp;
import in.pipeup.backend.auth.repository.PasswordResetRepository;
import in.pipeup.backend.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OtpServiceImpl implements OtpService{


    private final PasswordResetRepository passwordResetRepository;
    private final PasswordEncoder passwordEncoder;

    private final SecureRandom secureRandom = new SecureRandom();

    private static final int OTP_EXPIRY_MINUTES = 10;
    private static final int MAX_ATTEMPTS = 10;


    public OtpServiceImpl(PasswordResetRepository passwordResetRepository, PasswordEncoder passwordEncoder) {
        this.passwordResetRepository = passwordResetRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public String generateOtp() {
        int otp = 100000 + secureRandom.nextInt(900000);
        return String.valueOf(otp);
    }



    @Override
    @Transactional
    public String createOtp(User user) {

        // Remove any previous OTP for this user
        passwordResetRepository.deleteByUser(user);

        // Generate a new 6-digit OTP
        String otp = generateOtp();

        PasswordResetOtp passwordResetOtp = new PasswordResetOtp();

        passwordResetOtp.setUser(user);

        // Store only the hashed OTP
        passwordResetOtp.setOtpHash(passwordEncoder.encode(otp));

        // OTP expires after 10 minutes
        passwordResetOtp.setExpiresAt(LocalDateTime.now().plusMinutes(OTP_EXPIRY_MINUTES));

        passwordResetOtp.setCreatedAt(LocalDateTime.now());

        passwordResetOtp.setVerified(false);

        passwordResetOtp.setAttemptCount(0);

        passwordResetRepository.save(passwordResetOtp);

        // Return raw OTP so EmailService can send it
        return otp;
    }



    @Override
    public VerifyOtpResponse verifyOtp(User user, String otp) {

        PasswordResetOtp resetOtp = passwordResetRepository
                               .findTopByUserOrderByCreatedAtDesc(user)
                               .orElseThrow(() -> new RuntimeException("OTP not found"));

        // Check whether OTP has expired
        if (LocalDateTime.now().isAfter(resetOtp.getExpiresAt())) {
            throw new RuntimeException("OTP has expired");
        }

        // Check maximum attempts
        if (resetOtp.getAttemptCount() >= MAX_ATTEMPTS) {
            throw new RuntimeException("Maximum OTP attempts exceeded");
        }

        // Increase attempt count
        resetOtp.setAttemptCount(resetOtp.getAttemptCount() + 1);

        // Check OTP
        if (!passwordEncoder.matches(otp, resetOtp.getOtpHash())) {
            passwordResetRepository.save(resetOtp);
            throw new RuntimeException("Invalid OTP");
        }

        // OTP is valid
        resetOtp.setVerified(true);

        // Generate secure reset token
        String resetToken = UUID.randomUUID().toString();

        resetOtp.setResetToken(resetToken);

        // Reset token expires after 10 minutes
        resetOtp.setResetTokenExpiresAt(LocalDateTime.now().plusMinutes(OTP_EXPIRY_MINUTES));

        passwordResetRepository.save(resetOtp);

        return new VerifyOtpResponse("OTP verified successfully", resetToken);
    }
}
