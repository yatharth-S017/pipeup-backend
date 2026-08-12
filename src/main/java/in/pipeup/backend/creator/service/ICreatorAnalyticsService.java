package in.pipeup.backend.creator.service;

import in.pipeup.backend.creator.dto.response.CreatorAnalyticsResponse;
import in.pipeup.backend.creator.entity.CreatorProfile;

public interface ICreatorAnalyticsService {

    //Automatically called after creator onboarding
    void initializeAnalytics(CreatorProfile creatorProfile);

    //Returns analytics of logged in creator.
    CreatorAnalyticsResponse getAnalytics();

    //Refresh analytics from YouTube.
    CreatorAnalyticsResponse refreshAnalytics();


    CreatorAnalyticsResponse getCreatorAnalytics(Long creatorId);
}
