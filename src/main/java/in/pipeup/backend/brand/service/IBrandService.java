package in.pipeup.backend.brand.service;

import in.pipeup.backend.brand.dto.request.CompleteBrandProfileRequest;
import in.pipeup.backend.brand.dto.request.UpdateBrandProfileRequest;
import in.pipeup.backend.brand.dto.response.BrandProfileResponse;
import in.pipeup.backend.brand.dto.response.BrandPublicProfileResponse;

public interface IBrandService {

    BrandProfileResponse completeProfile(CompleteBrandProfileRequest request);

    BrandProfileResponse getProfile();

    BrandProfileResponse updateProfile(UpdateBrandProfileRequest request);

    BrandPublicProfileResponse getPublicProfile(Long brandId);
}
