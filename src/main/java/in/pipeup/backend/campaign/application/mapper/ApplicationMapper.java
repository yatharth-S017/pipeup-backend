package in.pipeup.backend.campaign.application.mapper;

import in.pipeup.backend.campaign.application.dto.ApplicantResponse;
import in.pipeup.backend.campaign.application.dto.ApplicationDetailsResponse;
import in.pipeup.backend.campaign.application.dto.ApplicationResponse;
import in.pipeup.backend.campaign.application.dto.MyApplicationResponse;
import in.pipeup.backend.campaign.application.entity.CampaignApplication;
import in.pipeup.backend.creator.entity.CreatorAnalytics;
import in.pipeup.backend.creator.entity.CreatorProfile;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {

    public ApplicationResponse toResponse(CampaignApplication application) {

        return ApplicationResponse.builder()
                .applicationId(application.getId())
                .campaignId(application.getCampaign().getId())
                .campaignTitle(application.getCampaign().getTitle())
                .status(application.getStatus())
                .appliedAt(application.getAppliedAt())
                .build();
    }



    public MyApplicationResponse toMyApplicationResponse(CampaignApplication application) {

        return MyApplicationResponse.builder()
                .applicationId(application.getId())
                .campaignId(application.getCampaign().getId())
                .campaignTitle(application.getCampaign().getTitle())
                .brandName(application.getCampaign().getBrand().getBrandName())
                .status(application.getStatus())
                .appliedAt(application.getAppliedAt())
                .build();
    }



    public ApplicantResponse toApplicantResponse(CampaignApplication application, CreatorAnalytics analytics) {

        CreatorProfile creator = application.getCreator();

        return ApplicantResponse.builder()
                .applicationId(application.getId())
                .creatorId(creator.getId())
                .creatorName(creator.getDisplayName())
                .channelName(analytics.getChannelName())
                .subscriberCount(analytics.getSubscriberCount())
                .totalViews(analytics.getTotalViews())
                .status(application.getStatus())
                .appliedAt(application.getAppliedAt())
                .build();
    }


    public ApplicationDetailsResponse toApplicationDetailsResponse(CampaignApplication application) {

        CreatorProfile creator = application.getCreator();

        return ApplicationDetailsResponse.builder()
                .applicationId(application.getId())
                .creatorId(creator.getId())
                .fullName(creator.getUser().getFullName())
                .displayName(creator.getDisplayName())
                .youtubeChannelUrl(creator.getYoutubeChannelUrl())
                .instagramProfileUrl(creator.getInstagramProfileUrl())
                .message(application.getMessage())
                .status(application.getStatus())
                .appliedAt(application.getAppliedAt())
                .build();
    }

}