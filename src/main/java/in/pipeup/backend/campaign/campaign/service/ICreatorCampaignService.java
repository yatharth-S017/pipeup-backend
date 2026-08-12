package in.pipeup.backend.campaign.campaign.service;

import in.pipeup.backend.campaign.campaign.dto.CampaignDetailsResponse;
import in.pipeup.backend.campaign.campaign.dto.CreatorCampaignCardResponse;

import java.util.List;

public interface ICreatorCampaignService {

    //Get all published campaigns visible to creators.
    List<CreatorCampaignCardResponse> getAllPublishedCampaigns();


    //Get detailed information about a specific campaign.
    CampaignDetailsResponse getCampaignDetails(Long campaignId);
}
