package in.pipeup.backend.campaign.application.service;


import in.pipeup.backend.brand.entity.BrandProfile;
import in.pipeup.backend.brand.repository.BrandProfileRepository;
import in.pipeup.backend.campaign.application.dto.*;
import in.pipeup.backend.campaign.application.entity.CampaignApplication;
import in.pipeup.backend.campaign.application.enums.ApplicationStatus;
import in.pipeup.backend.campaign.application.exception.*;
import in.pipeup.backend.campaign.application.mapper.ApplicationMapper;
import in.pipeup.backend.campaign.application.repository.CampaignApplicationRepository;
import in.pipeup.backend.campaign.campaign.entity.Campaign;
import in.pipeup.backend.campaign.campaign.enums.CampaignStatus;
import in.pipeup.backend.campaign.campaign.repository.CampaignRepository;
import in.pipeup.backend.common.security.CurrentUserService;
import in.pipeup.backend.creator.entity.CreatorAnalytics;
import in.pipeup.backend.creator.entity.CreatorProfile;
import in.pipeup.backend.creator.repository.CreatorAnalyticsRepository;
import in.pipeup.backend.creator.repository.CreatorProfileRepository;
import in.pipeup.backend.entity.User;
import in.pipeup.backend.exception.BrandProfileNotFoundException;
import in.pipeup.backend.exception.CampaignNotFoundException;
import in.pipeup.backend.exception.CreatorAnalyticsNotFoundException;
import in.pipeup.backend.exception.CreatorProfileNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements IApplicationService{

    private final CampaignRepository campaignRepository;
    private final CampaignApplicationRepository applicationRepository;
    private final CreatorProfileRepository creatorProfileRepository;
    private final CurrentUserService currentUserService;
    private final ApplicationMapper applicationMapper;

    private final BrandProfileRepository brandProfileRepository;
    private final CreatorAnalyticsRepository creatorAnalyticsRepository;


    @Override
    public ApplicationResponse applyToCampaign(Long campaignId, ApplyCampaignRequest request) {

        User user = currentUserService.getCurrentUser();

        CreatorProfile creator = creatorProfileRepository.findByUser(user)
                .orElseThrow(() -> new CreatorProfileNotFoundException("Creator profile not found."));

        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new CampaignNotFoundException("Campaign not found."));

        if (campaign.getStatus() != CampaignStatus.PUBLISHED) {
            throw new CampaignNotAvailableException("This campaign is not accepting applications.");
        }

        if (applicationRepository.existsByCampaignAndCreator(campaign, creator)) {
            throw new ApplicationAlreadyExistsException("You have already applied to this campaign.");
        }

        CampaignApplication application = CampaignApplication.builder()
                .campaign(campaign)
                .creator(creator)
                .message(request.getMessage())
                .build();

        CampaignApplication savedApplication = applicationRepository.save(application);

        return applicationMapper.toResponse(savedApplication);
    }





    @Override
    public List<MyApplicationResponse> getMyApplications() {

        User user = currentUserService.getCurrentUser();

        CreatorProfile creator = creatorProfileRepository.findByUser(user)
                .orElseThrow(() -> new CreatorProfileNotFoundException("Creator profile not found."));

        return applicationRepository.findByCreator(creator)
                .stream()
                .map(applicationMapper::toMyApplicationResponse)
                .toList();
    }





    @Override
    public List<ApplicantResponse> getApplicants(Long campaignId) {

        // Get currently logged-in user
        User currentUser = currentUserService.getCurrentUser();

        // Get Brand Profile
        BrandProfile brandProfile = brandProfileRepository.findByUser(currentUser)
                                    .orElseThrow(() -> new BrandProfileNotFoundException("Brand profile not found."));

        // Find Campaign
        Campaign campaign = campaignRepository.findById(campaignId)
                            .orElseThrow(() -> new CampaignNotFoundException("Campaign not found."));

        // Security Check
        // Ensure this campaign belongs to the logged-in brand
        if (!campaign.getBrand().getId().equals(brandProfile.getId())) {
            throw new UnauthorizedCampaignAccessException("You are not allowed to view applicants for this campaign.");
        }

        // Get all applications of this campaign
        List<CampaignApplication> applications = applicationRepository.findByCampaign(campaign);

        // Convert Entity -> DTO
        return applications.stream()
                .map(application -> {

                    CreatorAnalytics analytics = creatorAnalyticsRepository.findByCreatorProfile(application.getCreator())
                                              .orElseThrow(() -> new CreatorAnalyticsNotFoundException("Creator analytics not found."));

                    return applicationMapper.toApplicantResponse(
                            application, analytics
                    );
                }).toList();
    }





    @Override
    public ApplicationDetailsResponse getApplicationDetails(Long applicationId) {

        // Get currently logged-in user
        User currentUser = currentUserService.getCurrentUser();

        // Get Brand Profile
        BrandProfile brandProfile = brandProfileRepository
                .findByUser(currentUser)
                .orElseThrow(() ->
                        new BrandProfileNotFoundException(
                                "Brand profile not found."
                        ));

        // Find Application
        CampaignApplication application = applicationRepository
                .findById(applicationId)
                .orElseThrow(() ->
                        new ApplicationNotFoundException(
                                "Application not found."
                        ));

        // Get Campaign
        Campaign campaign = application.getCampaign();

        // Verify campaign ownership
        if (!campaign.getBrand().getId().equals(brandProfile.getId())) {
            throw new UnauthorizedCampaignAccessException(
                    "You are not allowed to access this application."
            );
        }

        // Convert Entity -> DTO
        return applicationMapper.toApplicationDetailsResponse(application);
    }






    @Override
    public void acceptApplication(Long applicationId) {

        updateApplicationStatus(applicationId, ApplicationStatus.ACCEPTED);
    }

    @Override
    public void rejectApplication(Long applicationId) {

        updateApplicationStatus(applicationId, ApplicationStatus.REJECTED);
    }




    private void updateApplicationStatus(Long applicationId, ApplicationStatus newStatus) {

        // Logged-in Brand
        User currentUser = currentUserService.getCurrentUser();

        BrandProfile brandProfile = brandProfileRepository.findByUser(currentUser)
                                  .orElseThrow(() -> new BrandProfileNotFoundException("Brand profile not found."));

        // Find Application
        CampaignApplication application = applicationRepository.findById(applicationId)
                                       .orElseThrow(() -> new ApplicationNotFoundException("Application not found."));

        // Verify Brand owns this campaign
        validateCampaignOwnership(application.getCampaign(), brandProfile);

        // Only pending applications can be processed
        if (application.getStatus() != ApplicationStatus.PENDING) {
            throw new InvalidApplicationStateException("Application has already been processed.");
        }

        // Update status
        application.setStatus(newStatus);

        applicationRepository.save(application);
    }


    private void validateCampaignOwnership(Campaign campaign, BrandProfile brandProfile) {

        if (!campaign.getBrand().getId().equals(brandProfile.getId())) {
            throw new UnauthorizedCampaignAccessException("You are not allowed to access this campaign.");
        }
    }




}
