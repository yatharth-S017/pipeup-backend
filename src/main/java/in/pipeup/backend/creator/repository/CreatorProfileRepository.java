package in.pipeup.backend.creator.repository;

import in.pipeup.backend.creator.entity.CreatorProfile;
import in.pipeup.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CreatorProfileRepository extends JpaRepository<CreatorProfile, Long> {

    Optional<CreatorProfile> findByUser(User user);
    boolean existsByUser(User user);

    List<CreatorProfile> findByOnboardingCompletedTrue();
}
