package in.pipeup.backend.campaign.application.service;

import in.pipeup.backend.campaign.application.dto.*;

import java.util.List;

public interface IApplicationService {


    //Creator applies to a campaign.
    ApplicationResponse applyToCampaign(Long campaignId, ApplyCampaignRequest request);

    List<MyApplicationResponse> getMyApplications();


    List<ApplicantResponse> getApplicants(Long campaignId);

    ApplicationDetailsResponse getApplicationDetails(Long applicationId);



    void acceptApplication(Long applicationId);

    void rejectApplication(Long applicationId);
}
