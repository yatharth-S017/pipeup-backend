package in.pipeup.backend.campaign.campaign.service;

import in.pipeup.backend.campaign.campaign.dto.CampaignResponse;
import in.pipeup.backend.campaign.campaign.dto.CreateCampaignRequest;
import in.pipeup.backend.campaign.campaign.dto.UpdateCampaignRequest;

import java.util.List;

public interface ICampaignService {

    CampaignResponse createCampaign(CreateCampaignRequest request);

    List<CampaignResponse> getMyCampaigns();

    CampaignResponse getCampaignById(Long campaignId);

    CampaignResponse updateCampaign(Long campaignId, UpdateCampaignRequest request);

    void deleteCampaign(Long campaignId);
}
