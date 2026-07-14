package in.pipeup.backend.creator.service;

import in.pipeup.backend.creator.dto.request.CompleteCreatorOnboardingRequest;
import in.pipeup.backend.creator.dto.request.UpdateCreatorProfileRequest;
import in.pipeup.backend.creator.dto.response.CreatorProfileResponse;

public interface ICreatorService {

    CreatorProfileResponse completeOnboarding(CompleteCreatorOnboardingRequest request);

    CreatorProfileResponse getProfile();

    CreatorProfileResponse updateProfile(UpdateCreatorProfileRequest request);
}