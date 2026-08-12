package in.pipeup.backend.creator.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatorAnalyticsResponse {

    private String channelName;

    private String channelUrl;

    private Long subscriberCount;

    private Long totalViews;

    private Long totalVideos;

    private Double averageViews;

    private Double averageLikes;

    private Double averageComments;

    private Double engagementRate;

    private LocalDateTime lastSyncedAt;
}
