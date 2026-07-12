package in.pipeup.backend.creator.mapper;

import in.pipeup.backend.creator.dto.request.CreateCreatorProfileRequest;
import in.pipeup.backend.creator.dto.request.UpdateCreatorProfileRequest;
import in.pipeup.backend.creator.dto.response.CreatorProfileResponse;
import in.pipeup.backend.entity.CreatorProfile;
import org.springframework.stereotype.Component;

@Component
public class CreatorProfileMapper {


    //Convert Entity -> Response DTO
    public CreatorProfileResponse toResponse(CreatorProfile creatorProfile) {

        if (creatorProfile == null) {
            return null;
        }

        CreatorProfileResponse response = new CreatorProfileResponse();
        response.setId(creatorProfile.getId());
        response.setDisplayName(creatorProfile.getDisplayName());
        response.setBio(creatorProfile.getBio());
        response.setCity(creatorProfile.getCity());
        response.setState(creatorProfile.getState());
        response.setCountry(creatorProfile.getCountry());
        response.setContentLanguages(creatorProfile.getContentLanguages());
        response.setNiches(creatorProfile.getNiches());
        response.setCustomNiche(creatorProfile.getCustomNiche());
        response.setYoutubeChannelUrl(creatorProfile.getYoutubeChannelUrl());
        response.setInstagramProfileUrl(creatorProfile.getInstagramProfileUrl());
        response.setProfileImageUrl(creatorProfile.getProfileImageUrl());
        response.setProfileCompleted(creatorProfile.getProfileCompleted());

        return response;
    }


    //Convert Create Request DTO -> Entity
    public CreatorProfile toEntity(CreateCreatorProfileRequest request) {

        if (request == null) {
            return null;
        }

        CreatorProfile creatorProfile = new CreatorProfile();
        creatorProfile.setDisplayName(request.getDisplayName());
        creatorProfile.setBio(request.getBio());
        creatorProfile.setCity(request.getCity());
        creatorProfile.setState(request.getState());
        creatorProfile.setCountry(request.getCountry());
        creatorProfile.setContentLanguages(request.getContentLanguages());
        creatorProfile.setNiches(request.getNiches());
        creatorProfile.setCustomNiche(request.getCustomNiche());
        creatorProfile.setYoutubeChannelUrl(request.getYoutubeChannelUrl());
        creatorProfile.setInstagramProfileUrl(request.getInstagramProfileUrl());

        return creatorProfile;
    }




   // Update Existing Entity from Update DTO
   public void updateEntity(CreatorProfile creatorProfile,
                            UpdateCreatorProfileRequest request) {

       if (request.getDisplayName() != null && !request.getDisplayName().isBlank()) {
           creatorProfile.setDisplayName(request.getDisplayName().trim());
       }

       if (request.getBio() != null && !request.getBio().isBlank()) {
           creatorProfile.setBio(request.getBio().trim());
       }

       if (request.getCity() != null && !request.getCity().isBlank()) {
           creatorProfile.setCity(request.getCity().trim());
       }

       if (request.getState() != null && !request.getState().isBlank()) {
           creatorProfile.setState(request.getState().trim());
       }

       if (request.getCountry() != null && !request.getCountry().isBlank()) {
           creatorProfile.setCountry(request.getCountry().trim());
       }

       if (request.getContentLanguages() != null
               && !request.getContentLanguages().isEmpty()) {
           creatorProfile.setContentLanguages(request.getContentLanguages());
       }

       if (request.getNiches() != null
               && !request.getNiches().isEmpty()) {
           creatorProfile.setNiches(request.getNiches());
       }

       if (request.getCustomNiche() != null
               && !request.getCustomNiche().isBlank()) {
           creatorProfile.setCustomNiche(request.getCustomNiche().trim());
       }

       if (request.getYoutubeChannelUrl() != null
               && !request.getYoutubeChannelUrl().isBlank()) {
           creatorProfile.setYoutubeChannelUrl(request.getYoutubeChannelUrl().trim());
       }

       if (request.getInstagramProfileUrl() != null
               && !request.getInstagramProfileUrl().isBlank()) {
           creatorProfile.setInstagramProfileUrl(request.getInstagramProfileUrl().trim());
       }
   }

}


