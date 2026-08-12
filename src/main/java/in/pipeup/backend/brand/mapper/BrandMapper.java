package in.pipeup.backend.brand.mapper;

import in.pipeup.backend.brand.dto.request.CompleteBrandProfileRequest;
import in.pipeup.backend.brand.dto.request.UpdateBrandProfileRequest;
import in.pipeup.backend.brand.dto.response.BrandProfileResponse;
import in.pipeup.backend.brand.dto.response.BrandPublicProfileResponse;
import in.pipeup.backend.brand.entity.BrandProfile;
import in.pipeup.backend.entity.User;
import org.springframework.stereotype.Component;


@Component
public class BrandMapper {


   // Convert CompleteBrandProfileRequest -> BrandProfile Entity
    public BrandProfile toEntity(CompleteBrandProfileRequest request, User user) {

        return BrandProfile.builder()
                .user(user)
                .companyName(request.getCompanyName())
                .brandName(request.getBrandName())
                .website(request.getWebsite())
                .industry(request.getIndustry())
                .companySize(request.getCompanySize())
                .state(request.getState())
                .city(request.getCity())
                .description(request.getDescription())
                .logoUrl(request.getLogoUrl())
                .profileCompleted(true)
                .build();
    }




    //Convert Entity -> BrandProfileResponse
    public BrandProfileResponse toResponse(BrandProfile brandProfile) {

        return BrandProfileResponse.builder()
                .id(brandProfile.getId())
                .companyName(brandProfile.getCompanyName())
                .brandName(brandProfile.getBrandName())
                .website(brandProfile.getWebsite())
                .industry(brandProfile.getIndustry())
                .companySize(brandProfile.getCompanySize())
                .state(brandProfile.getState())
                .city(brandProfile.getCity())
                .description(brandProfile.getDescription())
                .logoUrl(brandProfile.getLogoUrl())
                .verified(brandProfile.getVerified())
                .profileCompleted(brandProfile.getProfileCompleted())
                .build();
    }


    //Convert Entity -> Public Response

    public BrandPublicProfileResponse toPublicResponse(BrandProfile brandProfile) {

        return BrandPublicProfileResponse.builder()
                .id(brandProfile.getId())
                .companyName(brandProfile.getCompanyName())
                .brandName(brandProfile.getBrandName())
                .website(brandProfile.getWebsite())
                .industry(brandProfile.getIndustry())
                .companySize(brandProfile.getCompanySize())
                .state(brandProfile.getState())
                .city(brandProfile.getCity())
                .description(brandProfile.getDescription())
                .logoUrl(brandProfile.getLogoUrl())
                .verified(brandProfile.getVerified())
                .build();
    }


    //Update existing BrandProfile from UpdateBrandProfileRequest
    public void updateEntity(BrandProfile brandProfile, UpdateBrandProfileRequest request) {

        if (request.getCompanyName() != null) {
            brandProfile.setCompanyName(request.getCompanyName());
        }

        if (request.getBrandName() != null) {
            brandProfile.setBrandName(request.getBrandName());
        }

        if (request.getWebsite() != null) {
            brandProfile.setWebsite(request.getWebsite());
        }

        if (request.getIndustry() != null) {
            brandProfile.setIndustry(request.getIndustry());
        }

        if (request.getCompanySize() != null) {
            brandProfile.setCompanySize(request.getCompanySize());
        }

        if (request.getState() != null) {
            brandProfile.setState(request.getState());
        }

        if (request.getCity() != null) {
            brandProfile.setCity(request.getCity());
        }

        if (request.getDescription() != null) {
            brandProfile.setDescription(request.getDescription());
        }

        if (request.getLogoUrl() != null) {
            brandProfile.setLogoUrl(request.getLogoUrl());
        }
    }


    



}
