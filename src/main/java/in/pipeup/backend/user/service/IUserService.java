package in.pipeup.backend.user.service;

import in.pipeup.backend.user.dto.request.ChangePasswordRequest;
import in.pipeup.backend.user.dto.request.UpdateProfileRequest;
import in.pipeup.backend.user.dto.response.UserResponse;

public interface IUserService {

    UserResponse getCurrentUser();

    // Update my basic profile
    UserResponse updateProfile(UpdateProfileRequest request);

    // Change my password
    void changePassword(ChangePasswordRequest request);
}
