package in.pipeup.backend.campaign.campaign.mapper;

import in.pipeup.backend.brand.entity.BrandProfile;
import in.pipeup.backend.campaign.campaign.dto.*;
import in.pipeup.backend.campaign.campaign.entity.Campaign;
import in.pipeup.backend.campaign.campaign.enums.CampaignStatus;
import org.springframework.stereotype.Component;

@Component
public class CampaignMapper {

    //Create Campaign Entity from Create Request
    public Campaign toEntity(CreateCampaignRequest request, BrandProfile brand) {

        return Campaign.builder()
                .brand(brand)
                .title(request.getTitle())
                .description(request.getDescription())
                .requirements(request.getRequirements())
                .platforms(request.getPlatforms())
                .totalBudget(request.getTotalBudget())
                .payoutPerCreator(request.getPayoutPerCreator())
                .requiredCreators(request.getRequiredCreators())
                .applicationDeadline(request.getApplicationDeadline())
                .submissionDeadline(request.getSubmissionDeadline())
                .attachmentUrls(request.getAttachmentUrls())
                .status(CampaignStatus.PUBLISHED)
                .build();
    }


    //convert entity into campaignResponse
    public CampaignResponse toResponse(Campaign campaign) {

        return CampaignResponse.builder()
                .id(campaign.getId())
                .title(campaign.getTitle())
                .description(campaign.getDescription())
                .requirements(campaign.getRequirements())
                .platforms(campaign.getPlatforms())
                .totalBudget(campaign.getTotalBudget())
                .payoutPerCreator(campaign.getPayoutPerCreator())
                .requiredCreators(campaign.getRequiredCreators())
                .applicationDeadline(campaign.getApplicationDeadline())
                .submissionDeadline(campaign.getSubmissionDeadline())
                .attachmentUrls(campaign.getAttachmentUrls())
                .status(campaign.getStatus())
                .createdAt(campaign.getCreatedAt())
                .updatedAt(campaign.getUpdatedAt())
                .build();
    }


    public void updateEntity(Campaign campaign, UpdateCampaignRequest request) {

        if (request.getTitle() != null) {
            campaign.setTitle(request.getTitle());
        }

        if (request.getDescription() != null) {
            campaign.setDescription(request.getDescription());
        }

        if (request.getRequirements() != null) {
            campaign.setRequirements(request.getRequirements());
        }

        if (request.getPlatforms() != null && !request.getPlatforms().isEmpty()) {
            campaign.setPlatforms(request.getPlatforms());
        }

        if (request.getTotalBudget() != null) {
            campaign.setTotalBudget(request.getTotalBudget());
        }

        if (request.getPayoutPerCreator() != null) {
            campaign.setPayoutPerCreator(request.getPayoutPerCreator());
        }

        if (request.getRequiredCreators() != null) {
            campaign.setRequiredCreators(request.getRequiredCreators());
        }

        if (request.getApplicationDeadline() != null) {
            campaign.setApplicationDeadline(request.getApplicationDeadline());
        }

        if (request.getSubmissionDeadline() != null) {
            campaign.setSubmissionDeadline(request.getSubmissionDeadline());
        }

        if (request.getAttachmentUrls() != null) {
            campaign.setAttachmentUrls(request.getAttachmentUrls());
        }
    }



    // ===========================
    // Creator Side - Campaign Card
    // ===========================
    public CreatorCampaignCardResponse toCreatorCampaignCardResponse(Campaign campaign) {
        return CreatorCampaignCardResponse.builder()
                .id(campaign.getId())
                .title(campaign.getTitle())
                .brandName(campaign.getBrand().getBrandName())
                .platforms(campaign.getPlatforms())
                .payoutPerCreator(campaign.getPayoutPerCreator())
                .requiredCreators(campaign.getRequiredCreators())
                .build();
    }


    // ===========================
    // Creator Side - Campaign Details
    // ===========================
    public CampaignDetailsResponse toCampaignDetailsResponse(Campaign campaign) {
        return CampaignDetailsResponse.builder()
                .id(campaign.getId())
                .title(campaign.getTitle())
                .brandName(campaign.getBrand().getBrandName())
                .description(campaign.getDescription())
                .requirements(campaign.getRequirements())
                .platforms(campaign.getPlatforms())
                .totalBudget(campaign.getTotalBudget())
                .payoutPerCreator(campaign.getPayoutPerCreator())
                .requiredCreators(campaign.getRequiredCreators())
                .applicationDeadline(campaign.getApplicationDeadline())
                .submissionDeadline(campaign.getSubmissionDeadline())
                .attachmentUrls(campaign.getAttachmentUrls())
                .build();
    }

}
