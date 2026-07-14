package in.pipeup.backend.creator.service;

import in.pipeup.backend.common.security.CurrentUserService;
import in.pipeup.backend.creator.dto.request.CompleteCreatorOnboardingRequest;
import in.pipeup.backend.creator.dto.request.UpdateCreatorProfileRequest;
import in.pipeup.backend.creator.dto.response.CreatorProfileResponse;
import in.pipeup.backend.creator.mapper.CreatorProfileMapper;
import in.pipeup.backend.creator.repository.CreatorProfileRepository;
import in.pipeup.backend.creator.service.ICreatorService;
import in.pipeup.backend.entity.CreatorProfile;
import in.pipeup.backend.entity.Role;
import in.pipeup.backend.entity.User;
import in.pipeup.backend.exception.CreatorProfileAlreadyExistsException;
import in.pipeup.backend.exception.CreatorProfileNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class CreatorServiceImpl implements ICreatorService {

    private final CreatorProfileRepository creatorProfileRepository;
    private final CreatorProfileMapper creatorProfileMapper;
    private final CurrentUserService currentUserService;


    @Override
    public CreatorProfileResponse completeOnboarding(CompleteCreatorOnboardingRequest request) {

        User currentUser = currentUserService.getCurrentUser();

        if (creatorProfileRepository.existsByUser(currentUser)) {
            throw new CreatorProfileAlreadyExistsException("Creator onboarding has already been completed.");
        }

        CreatorProfile creatorProfile = creatorProfileMapper.toEntity(request);

        creatorProfile.setUser(currentUser);
        creatorProfile.setOnboardingCompleted(true);
        creatorProfile.setOnboardingCompletedAt(LocalDateTime.now());
        CreatorProfile savedProfile = creatorProfileRepository.save(creatorProfile);

        return creatorProfileMapper.toResponse(savedProfile);
    }

    @Override
    @Transactional
    public CreatorProfileResponse getProfile() {

        User currentUser = currentUserService.getCurrentUser();

        CreatorProfile creatorProfile = creatorProfileRepository.findByUser(currentUser)
                        .orElseThrow(() -> new CreatorProfileNotFoundException("Creator profile not found."));

        return creatorProfileMapper.toResponse(creatorProfile);
    }



    @Override
    public CreatorProfileResponse updateProfile(
            UpdateCreatorProfileRequest request) {

        User currentUser = currentUserService.getCurrentUser();

        CreatorProfile creatorProfile =
                creatorProfileRepository.findByUser(currentUser).orElseThrow(() -> new CreatorProfileNotFoundException("Creator profile not found."));

        creatorProfileMapper.updateEntity(creatorProfile, request);

        CreatorProfile updatedProfile = creatorProfileRepository.save(creatorProfile);

        return creatorProfileMapper.toResponse(updatedProfile);
    }
}