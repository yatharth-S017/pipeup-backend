package in.pipeup.backend.campaign.campaign.service;

import in.pipeup.backend.brand.entity.BrandProfile;
import in.pipeup.backend.brand.repository.BrandProfileRepository;
import in.pipeup.backend.campaign.campaign.dto.CampaignResponse;
import in.pipeup.backend.campaign.campaign.dto.CreateCampaignRequest;
import in.pipeup.backend.campaign.campaign.dto.UpdateCampaignRequest;
import in.pipeup.backend.campaign.campaign.entity.Campaign;
import in.pipeup.backend.campaign.campaign.mapper.CampaignMapper;
import in.pipeup.backend.campaign.campaign.repository.CampaignRepository;
import in.pipeup.backend.common.security.CurrentUserService;
import in.pipeup.backend.entity.User;
import in.pipeup.backend.exception.BrandProfileNotFoundException;
import in.pipeup.backend.exception.CampaignNotFoundException;
import in.pipeup.backend.exception.InvalidCampaignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignServiceImpl implements ICampaignService{

    private final CampaignRepository campaignRepository;
    private final BrandProfileRepository brandProfileRepository;
    private final CurrentUserService currentUserService;
    private final CampaignMapper campaignMapper;




    private BrandProfile getCurrentBrand() {
        User currentUser = currentUserService.getCurrentUser();
        return brandProfileRepository.findByUser(currentUser)
                .orElseThrow(() -> new BrandProfileNotFoundException("Brand profile not found."));
    }

    private Campaign getOwnedCampaign(Long campaignId) {

        BrandProfile brand = getCurrentBrand();

        Campaign campaign = campaignRepository.findById(campaignId)
                            .orElseThrow(() -> new CampaignNotFoundException("Campaign not found."));

        if (!campaign.getBrand().getId().equals(brand.getId())) {
            throw new CampaignNotFoundException("Campaign not found.");
        }

        return campaign;
    }




    @Override
    public CampaignResponse createCampaign(CreateCampaignRequest request) {

        BrandProfile brand = getCurrentBrand();

        if (request.getSubmissionDeadline().isBefore(request.getApplicationDeadline())) {
            throw new InvalidCampaignException("Submission deadline must be after application deadline.");
        }

        Campaign campaign = campaignMapper.toEntity(request, brand);

        Campaign savedCampaign = campaignRepository.save(campaign);

        return campaignMapper.toResponse(savedCampaign);
    }



    @Override
    public List<CampaignResponse> getMyCampaigns() {

        BrandProfile brand = getCurrentBrand();

        return campaignRepository.findByBrand(brand)
                .stream()
                .map(campaignMapper::toResponse)
                .toList();
    }


    @Override
    public CampaignResponse getCampaignById(Long campaignId) {

        Campaign campaign = getOwnedCampaign(campaignId);
        return campaignMapper.toResponse(campaign);
    }




    @Override
    public CampaignResponse updateCampaign(Long campaignId,
                                           UpdateCampaignRequest request) {

        Campaign campaign = getOwnedCampaign(campaignId);

        if (request.getApplicationDeadline() != null
                && request.getSubmissionDeadline() != null
                && request.getSubmissionDeadline().isBefore(request.getApplicationDeadline())) {

            throw new InvalidCampaignException(
                    "Submission deadline must be after application deadline.");
        }

        campaignMapper.updateEntity(campaign, request);

        Campaign updatedCampaign = campaignRepository.save(campaign);
        return campaignMapper.toResponse(updatedCampaign);
    }




    @Override
    public void deleteCampaign(Long campaignId) {
        Campaign campaign = getOwnedCampaign(campaignId);
        campaignRepository.delete(campaign);
    }


}
