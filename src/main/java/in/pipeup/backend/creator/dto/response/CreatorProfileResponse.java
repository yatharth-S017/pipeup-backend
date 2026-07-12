package in.pipeup.backend.creator.dto.response;

import in.pipeup.backend.entity.Niche;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatorProfileResponse {

    private Long id;
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
    private String profileImageUrl;
    private Boolean profileCompleted;

}
