package in.pipeup.backend.brand.repository;

import in.pipeup.backend.brand.entity.BrandProfile;
import in.pipeup.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandProfileRepository extends JpaRepository<BrandProfile,Long> {
    
    Optional<BrandProfile> findByUser(User user);
    boolean existsByUser(User user);
}
