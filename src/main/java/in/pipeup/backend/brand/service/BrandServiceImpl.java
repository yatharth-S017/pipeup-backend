package in.pipeup.backend.brand.service;

import in.pipeup.backend.brand.dto.request.CompleteBrandProfileRequest;
import in.pipeup.backend.brand.dto.request.UpdateBrandProfileRequest;
import in.pipeup.backend.brand.dto.response.BrandProfileResponse;
import in.pipeup.backend.brand.dto.response.BrandPublicProfileResponse;
import in.pipeup.backend.brand.entity.BrandProfile;
import in.pipeup.backend.brand.mapper.BrandMapper;
import in.pipeup.backend.brand.repository.BrandProfileRepository;
import in.pipeup.backend.common.security.CurrentUserService;
import in.pipeup.backend.entity.User;
import in.pipeup.backend.exception.BrandProfileAlreadyExistsException;
import in.pipeup.backend.exception.BrandProfileNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandServiceImpl implements IBrandService {

    private final BrandProfileRepository brandProfileRepository;
    private final BrandMapper brandMapper;
    private final CurrentUserService currentUserService;


    @Override
    public BrandProfileResponse completeProfile(CompleteBrandProfileRequest request) {

        User currentUser = currentUserService.getCurrentUser();

        if (brandProfileRepository.existsByUser(currentUser)) {
            throw new BrandProfileAlreadyExistsException("Brand profile has already been completed.");
        }

        BrandProfile brandProfile = brandMapper.toEntity(request, currentUser);
        BrandProfile savedProfile = brandProfileRepository.save(brandProfile);

        return brandMapper.toResponse(savedProfile);
    }


    @Override
    public BrandProfileResponse getProfile() {

        User currentUser = currentUserService.getCurrentUser();

        BrandProfile brandProfile = brandProfileRepository.findByUser(currentUser)
                .orElseThrow(() -> new BrandProfileNotFoundException("Brand profile not found."));

        return brandMapper.toResponse(brandProfile);
    }


    @Override
    public BrandProfileResponse updateProfile(UpdateBrandProfileRequest request) {

        User currentUser = currentUserService.getCurrentUser();

        BrandProfile brandProfile = brandProfileRepository.findByUser(currentUser)
                .orElseThrow(() -> new BrandProfileNotFoundException("Brand profile not found."));

        brandMapper.updateEntity(brandProfile, request);

        BrandProfile updatedProfile = brandProfileRepository.save(brandProfile);

        return brandMapper.toResponse(updatedProfile);
    }


    @Override
    public BrandPublicProfileResponse getPublicProfile(Long brandId) {

        BrandProfile brandProfile = brandProfileRepository.findById(brandId)
                .orElseThrow(() -> new BrandProfileNotFoundException("Brand profile not found."));

        return brandMapper.toPublicResponse(brandProfile);
    }
}
