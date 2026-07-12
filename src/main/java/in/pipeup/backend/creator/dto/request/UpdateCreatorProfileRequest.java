package in.pipeup.backend.creator.dto.request;

import in.pipeup.backend.entity.Niche;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UpdateCreatorProfileRequest {
    
    private String displayName;
    private String bio;
    private String city;
    private String state;
    private String country;
    private Set<String> contentLanguages;
    private Set<Niche> niches;
    private String customNiche;
    private String youtubeChannelUrl;
    private String instagramProfileUrl;
}
