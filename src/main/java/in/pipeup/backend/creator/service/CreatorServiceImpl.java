package in.pipeup.backend.creator.service;

import in.pipeup.backend.common.security.CurrentUserService;
import in.pipeup.backend.creator.dto.request.CreateCreatorProfileRequest;
import in.pipeup.backend.creator.dto.request.UpdateCreatorProfileRequest;
import in.pipeup.backend.creator.dto.response.CreatorProfileResponse;
import in.pipeup.backend.creator.mapper.CreatorProfileMapper;
import in.pipeup.backend.creator.repository.CreatorProfileRepository;
import in.pipeup.backend.entity.CreatorProfile;
import in.pipeup.backend.entity.User;
import in.pipeup.backend.exception.CreatorProfileAlreadyExistsException;
import in.pipeup.backend.exception.CreatorProfileNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatorServiceImpl implements ICreatorService{

    private final CreatorProfileRepository creatorProfileRepository;
    private final CurrentUserService currentUserService;
    private final CreatorProfileMapper creatorProfileMapper;

    @Override
    @Transactional
    public CreatorProfileResponse createProfile(CreateCreatorProfileRequest request) {

        // Get currently logged-in user
        User user = currentUserService.getCurrentUser();

        // Check if profile already exists
        if (creatorProfileRepository.existsByUser(user)) {
            throw new CreatorProfileAlreadyExistsException("Creator profile already exists.");
        }

        // Convert request to entity
        CreatorProfile creatorProfile = creatorProfileMapper.toEntity(request);

        // Associate profile with logged-in user
        creatorProfile.setUser(user);

        // Initial status
        creatorProfile.setProfileCompleted(isProfileCompleted(creatorProfile));

        CreatorProfile savedProfile = creatorProfileRepository.save(creatorProfile);
        return creatorProfileMapper.toResponse(savedProfile);
    }

    @Override
    public CreatorProfileResponse getProfile() {
        // Get currently logged-in user
        User user = currentUserService.getCurrentUser();

        // Fetch creator profile
        CreatorProfile creatorProfile = creatorProfileRepository
                .findByUser(user)
                .orElseThrow(() -> new CreatorProfileNotFoundException("Creator profile not found."));

        // Convert Entity -> Response
        return creatorProfileMapper.toResponse(creatorProfile);
    }





    @Override
    public CreatorProfileResponse updateProfile(UpdateCreatorProfileRequest request) {
        // Get currently logged-in user
        User user = currentUserService.getCurrentUser();

        // Fetch existing creator profile
        CreatorProfile creatorProfile = creatorProfileRepository
                .findByUser(user)
                .orElseThrow(() -> new CreatorProfileNotFoundException(
                        "Creator profile not found."
                ));

        // Update entity
        creatorProfileMapper.updateEntity(creatorProfile, request);

        // Recalculate profile completion
        creatorProfile.setProfileCompleted(
                isProfileCompleted(creatorProfile)
        );

        // Save updated profile
        CreatorProfile updatedProfile = creatorProfileRepository.save(creatorProfile);

        // Return updated response
        return creatorProfileMapper.toResponse(updatedProfile);
    }




    private boolean isProfileCompleted(CreatorProfile profile) {

        return profile.getDisplayName() != null
                && !profile.getDisplayName().isBlank()

                && profile.getBio() != null
                && !profile.getBio().isBlank()

                && profile.getYoutubeChannelUrl() != null
                && !profile.getYoutubeChannelUrl().isBlank()

                && profile.getInstagramProfileUrl() != null
                && !profile.getInstagramProfileUrl().isBlank()

                && profile.getCity() != null
                && !profile.getCity().isBlank()

                && profile.getCountry() != null
                && !profile.getCountry().isBlank()

                && profile.getContentLanguages() != null
                && !profile.getContentLanguages().isEmpty()

                && profile.getNiches() != null
                && !profile.getNiches().isEmpty();
    }
}
