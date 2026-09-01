package in.pipeup.backend.auth.service;

import in.pipeup.backend.auth.Dto.VerifyOtpResponse;
import in.pipeup.backend.entity.User;

public interface OtpService {

    String generateOtp();

    String createOtp(User user);

    VerifyOtpResponse verifyOtp(User user, String otp);
}
