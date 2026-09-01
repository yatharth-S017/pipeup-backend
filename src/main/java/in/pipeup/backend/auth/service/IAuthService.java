package in.pipeup.backend.auth.service;

import in.pipeup.backend.auth.Dto.*;
import in.pipeup.backend.entity.User;

public interface IAuthService {

    // Register a new user into the system
    RegisterResponse registerUser(RegisterRequest request);

    LoginResponse login(LoginRequest loginRequest);


    void forgotPassword(ForgotPasswordRequest request);

    VerifyOtpResponse verifyOtp(VerifyOtpRequest request);

    void resetPassword(ResetPasswordRequest request);



}
