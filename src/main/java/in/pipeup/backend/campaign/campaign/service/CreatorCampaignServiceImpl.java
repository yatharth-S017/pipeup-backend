package in.pipeup.backend.campaign.campaign.service;

import in.pipeup.backend.campaign.campaign.dto.CampaignDetailsResponse;
import in.pipeup.backend.campaign.campaign.dto.CreatorCampaignCardResponse;
import in.pipeup.backend.campaign.campaign.entity.Campaign;
import in.pipeup.backend.campaign.campaign.enums.CampaignStatus;
import in.pipeup.backend.campaign.campaign.mapper.CampaignMapper;
import in.pipeup.backend.campaign.campaign.repository.CampaignRepository;
import in.pipeup.backend.exception.CampaignNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CreatorCampaignServiceImpl implements ICreatorCampaignService {

    private final CampaignRepository campaignRepository;
    private final CampaignMapper campaignMapper;


    @Override
    public List<CreatorCampaignCardResponse> getAllPublishedCampaigns() {

        return campaignRepository.findByStatus(CampaignStatus.PUBLISHED)
                .stream()
                .map(campaignMapper::toCreatorCampaignCardResponse)
                .toList();
    }

    @Override
    public CampaignDetailsResponse getCampaignDetails(Long campaignId) {

        Campaign campaign = campaignRepository.findById(campaignId)
                         .orElseThrow(() -> new CampaignNotFoundException("Campaign not found."));

        return campaignMapper.toCampaignDetailsResponse(campaign);
    }

}
