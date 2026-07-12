package in.pipeup.backend.user.service;

import in.pipeup.backend.auth.repository.UserRepository;
import in.pipeup.backend.common.security.CurrentUserService;
import in.pipeup.backend.entity.User;
import in.pipeup.backend.exception.InvalidPasswordException;
import in.pipeup.backend.user.dto.request.ChangePasswordRequest;
import in.pipeup.backend.user.dto.request.UpdateProfileRequest;
import in.pipeup.backend.user.dto.response.UserResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService{

    private final CurrentUserService currentUserService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserResponse getCurrentUser() {
        User user = currentUserService.getCurrentUser();
        return new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole()
        );
    }



    @Override
    public UserResponse updateProfile(UpdateProfileRequest request) {

        User user = currentUserService.getCurrentUser();
        user.setFullName(request.getFullName());
        User updatedUser = userRepository.save(user);

        return new UserResponse(
                updatedUser.getId(),
                updatedUser.getFullName(),
                updatedUser.getEmail(),
                updatedUser.getRole()
        );
    }



    @Transactional
    @Override
    public void changePassword(ChangePasswordRequest request) {

        User user = currentUserService.getCurrentUser();

        // Verify current password
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Current password is incorrect");
        }
        // Check new password is different
        if (passwordEncoder.matches(request.getNewPassword(), user.getPassword())) {
            throw new InvalidPasswordException("New password must be different from the current password");
        }

        // Encode and save new password
        user.setPassword(passwordEncoder.encode(request.getNewPassword())
        );

        userRepository.save(user);
    }


}
