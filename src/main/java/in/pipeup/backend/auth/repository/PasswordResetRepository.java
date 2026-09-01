package in.pipeup.backend.auth.repository;

import in.pipeup.backend.auth.entity.PasswordResetOtp;
import in.pipeup.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetRepository extends JpaRepository<PasswordResetOtp, Long> {

    Optional<PasswordResetOtp> findTopByUserOrderByCreatedAtDesc(User user);

    Optional<PasswordResetOtp> findByResetToken(String resetToken);

    void deleteByUser(User user);
}
