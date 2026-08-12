package in.pipeup.backend.creator.repository;

import in.pipeup.backend.creator.entity.CreatorAnalytics;
import in.pipeup.backend.creator.entity.CreatorProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreatorAnalyticsRepository extends JpaRepository<CreatorAnalytics,Long> {

    Optional<CreatorAnalytics> findByCreatorProfile(CreatorProfile creatorProfile);

    boolean existsByCreatorProfile(CreatorProfile creatorProfile);

    Optional<CreatorAnalytics> findByChannelId(String channelId);
    
}
