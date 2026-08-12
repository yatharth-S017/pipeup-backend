package in.pipeup.backend.creator.mapper;

import in.pipeup.backend.creator.dto.response.CreatorAnalyticsResponse;
import in.pipeup.backend.creator.entity.CreatorAnalytics;
import in.pipeup.backend.creator.entity.CreatorProfile;
import in.pipeup.backend.creator.integration.dto.YoutubeChannel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CreatorAnalyticsMapper {

    public CreatorAnalytics toEntity(
            YoutubeChannel channel,
            CreatorProfile creatorProfile
    ) {

        return CreatorAnalytics.builder()

                .creatorProfile(creatorProfile)

                .channelId(channel.getId())

                .channelName(channel.getSnippet().getTitle())

                .channelUrl(creatorProfile.getYoutubeChannelUrl())

                .subscriberCount(channel.getStatistics().getSubscriberCount())

                .totalViews(channel.getStatistics().getViewCount())

                .totalVideos(channel.getStatistics().getVideoCount())

                // Will calculate later
                .averageViews(0.0)
                .averageLikes(0.0)
                .averageComments(0.0)
                .engagementRate(0.0)

                .lastSyncedAt(LocalDateTime.now())

                .build();
    }


    public CreatorAnalyticsResponse toResponse(
            CreatorAnalytics analytics
    ) {

        return CreatorAnalyticsResponse.builder()

                .channelName(analytics.getChannelName())

                .channelUrl(analytics.getChannelUrl())

                .subscriberCount(analytics.getSubscriberCount())

                .totalViews(analytics.getTotalViews())

                .totalVideos(analytics.getTotalVideos())

                .averageViews(analytics.getAverageViews())

                .averageLikes(analytics.getAverageLikes())

                .averageComments(analytics.getAverageComments())

                .engagementRate(analytics.getEngagementRate())

                .lastSyncedAt(analytics.getLastSyncedAt())

                .build();

    }

}
