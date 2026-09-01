package in.pipeup.backend.creator.dto.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatorDiscoveryResponse {

    private Long creatorId;

    // Creator Profile
    private String displayName;
    private String profileImageUrl;

    private Set<String> niches;

    private String city;
    private String state;

    // Social
    private String youtubeChannelUrl;
    private String instagramProfileUrl;

    // YouTube Analytics
    private String channelName;
    private String channelLogo;

    private Long subscriberCount;
    private Long totalViews;
    private Long totalVideos;

    private Double averageViews;
    private Double averageLikes;
    private Double averageComments;
    private Double engagementRate;
}
