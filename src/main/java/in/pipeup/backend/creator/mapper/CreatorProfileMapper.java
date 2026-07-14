package in.pipeup.backend.creator.mapper;

import in.pipeup.backend.creator.dto.request.CompleteCreatorOnboardingRequest;
import in.pipeup.backend.creator.dto.request.UpdateCreatorProfileRequest;
import in.pipeup.backend.creator.dto.response.CreatorProfileResponse;
import in.pipeup.backend.entity.CreatorProfile;
import org.springframework.stereotype.Component;

@Component
public class CreatorProfileMapper {

    /*
     * Entity -> Response
     */
    public CreatorProfileResponse toResponse(CreatorProfile creatorProfile) {

        if (creatorProfile == null) {
            return null;
        }

        CreatorProfileResponse response = new CreatorProfileResponse();

        response.setId(creatorProfile.getId());

        response.setDisplayName(creatorProfile.getDisplayName());

        response.setEmail(creatorProfile.getUser().getEmail());

        response.setPhoneNumber(creatorProfile.getPhoneNumber());

        response.setYoutubeChannelUrl(creatorProfile.getYoutubeChannelUrl());

        response.setInstagramProfileUrl(creatorProfile.getInstagramProfileUrl());

        response.setNiches(creatorProfile.getNiches());

        response.setState(creatorProfile.getState());

        response.setCity(creatorProfile.getCity());

        response.setCurrentChallenge(creatorProfile.getCurrentChallenge());

        response.setExpectedSupport(creatorProfile.getExpectedSupport());

        response.setStartingPrice(creatorProfile.getStartingPrice());

        response.setProfileImageUrl(creatorProfile.getProfileImageUrl());

        response.setOnboardingCompleted(creatorProfile.getOnboardingCompleted());

        response.setOnboardingCompletedAt(
                creatorProfile.getOnboardingCompletedAt()
        );

        response.setCreatedAt(
                creatorProfile.getCreatedAt()
        );

        response.setUpdatedAt(
                creatorProfile.getUpdatedAt()
        );

        return response;
    }

    /*
     * Onboarding Request -> Entity
     */
    public CreatorProfile toEntity(CompleteCreatorOnboardingRequest request) {

        if (request == null) {
            return null;
        }

        CreatorProfile creatorProfile = new CreatorProfile();

        creatorProfile.setDisplayName(request.getDisplayName().trim());

        creatorProfile.setPhoneNumber(request.getPhoneNumber().trim());

        creatorProfile.setYoutubeChannelUrl(request.getYoutubeChannelUrl());

        creatorProfile.setInstagramProfileUrl(request.getInstagramProfileUrl());

        creatorProfile.setNiches(request.getNiches());

        creatorProfile.setState(request.getState().trim());

        creatorProfile.setCity(request.getCity().trim());

        creatorProfile.setCurrentChallenge(request.getCurrentChallenge());

        creatorProfile.setExpectedSupport(request.getExpectedSupport());

        creatorProfile.setStartingPrice(request.getStartingPrice());

        return creatorProfile;
    }

    /*
     * Update Existing Entity
     */
    public void updateEntity(
            CreatorProfile creatorProfile,
            UpdateCreatorProfileRequest request
    ) {

        if (request.getDisplayName() != null &&
                !request.getDisplayName().isBlank()) {

            creatorProfile.setDisplayName(
                    request.getDisplayName().trim()
            );
        }

        if (request.getPhoneNumber() != null &&
                !request.getPhoneNumber().isBlank()) {

            creatorProfile.setPhoneNumber(
                    request.getPhoneNumber().trim()
            );
        }

        if (request.getYoutubeChannelUrl() != null &&
                !request.getYoutubeChannelUrl().isBlank()) {

            creatorProfile.setYoutubeChannelUrl(
                    request.getYoutubeChannelUrl().trim()
            );
        }

        if (request.getInstagramProfileUrl() != null &&
                !request.getInstagramProfileUrl().isBlank()) {

            creatorProfile.setInstagramProfileUrl(
                    request.getInstagramProfileUrl().trim()
            );
        }

        if (request.getNiches() != null &&
                !request.getNiches().isEmpty()) {

            creatorProfile.setNiches(request.getNiches());
        }

        if (request.getState() != null &&
                !request.getState().isBlank()) {

            creatorProfile.setState(
                    request.getState().trim()
            );
        }

        if (request.getCity() != null &&
                !request.getCity().isBlank()) {

            creatorProfile.setCity(
                    request.getCity().trim()
            );
        }

        if (request.getCurrentChallenge() != null &&
                !request.getCurrentChallenge().isBlank()) {

            creatorProfile.setCurrentChallenge(
                    request.getCurrentChallenge().trim()
            );
        }

        if (request.getExpectedSupport() != null &&
                !request.getExpectedSupport().isBlank()) {

            creatorProfile.setExpectedSupport(
                    request.getExpectedSupport().trim()
            );
        }

        if (request.getStartingPrice() != null) {

            creatorProfile.setStartingPrice(
                    request.getStartingPrice()
            );
        }

        if (request.getProfileImageUrl() != null &&
                !request.getProfileImageUrl().isBlank()) {

            creatorProfile.setProfileImageUrl(
                    request.getProfileImageUrl().trim()
            );
        }
    }
}