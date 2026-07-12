package in.pipeup.backend.creator.service;

import in.pipeup.backend.creator.dto.request.CreateCreatorProfileRequest;
import in.pipeup.backend.creator.dto.request.UpdateCreatorProfileRequest;
import in.pipeup.backend.creator.dto.response.CreatorProfileResponse;

public interface ICreatorService {


    //Creates a creator profile for the currently logged-in user.
    CreatorProfileResponse createProfile(CreateCreatorProfileRequest request);

    //Returns the creator profile of the currently logged-in user
    CreatorProfileResponse getProfile();

    //Updates the creator profile of the currently logged-in user
    CreatorProfileResponse updateProfile(UpdateCreatorProfileRequest request);
}
