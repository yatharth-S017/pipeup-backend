package in.pipeup.backend.campaign.campaign.repository;

import in.pipeup.backend.brand.entity.BrandProfile;
import in.pipeup.backend.campaign.campaign.entity.Campaign;
import in.pipeup.backend.campaign.campaign.enums.CampaignStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    // Get all campaigns created by a brand
    List<Campaign> findByBrand(BrandProfile brand);

    // Get campaigns of a brand by status
    List<Campaign> findByBrandAndStatus(BrandProfile brand, CampaignStatus status);

    // Get all published campaigns (used in Creator Marketplace later)
    List<Campaign> findByStatus(CampaignStatus status);




}
