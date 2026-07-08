package in.pipeup.backend.auth.service;

import in.pipeup.backend.auth.Dto.LoginRequest;
import in.pipeup.backend.auth.Dto.LoginResponse;
import in.pipeup.backend.auth.Dto.RegisterRequest;
import in.pipeup.backend.auth.Dto.RegisterResponse;
import in.pipeup.backend.entity.User;

public interface IAuthService {

    // Register a new user into the system
    RegisterResponse registerUser(RegisterRequest request);

    LoginResponse login(LoginRequest loginRequest);
}
