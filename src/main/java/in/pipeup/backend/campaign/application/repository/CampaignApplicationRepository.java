package in.pipeup.backend.campaign.application.repository;

import in.pipeup.backend.campaign.application.entity.CampaignApplication;
import in.pipeup.backend.campaign.application.enums.ApplicationStatus;
import in.pipeup.backend.campaign.campaign.entity.Campaign;
import in.pipeup.backend.creator.entity.CreatorProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignApplicationRepository extends JpaRepository<CampaignApplication, Long> {

    // Check if creator has already applied to a campaign
    boolean existsByCampaignAndCreator(Campaign campaign, CreatorProfile creator);

    // Get all applications submitted by a creator
    List<CampaignApplication> findByCreator(CreatorProfile creator);

    // Get all applications for a campaign
    List<CampaignApplication> findByCampaign(Campaign campaign);

    // Get applications of a campaign by status
    List<CampaignApplication> findByCampaignAndStatus(Campaign campaign, ApplicationStatus status);
}