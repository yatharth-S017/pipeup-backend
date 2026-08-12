package in.pipeup.backend.creator.service;

import in.pipeup.backend.common.security.CurrentUserService;
import in.pipeup.backend.creator.dto.response.CreatorAnalyticsResponse;
import in.pipeup.backend.creator.entity.CreatorAnalytics;
import in.pipeup.backend.creator.entity.CreatorProfile;
import in.pipeup.backend.creator.integration.YoutubeService;
import in.pipeup.backend.creator.integration.YoutubeUtil;
import in.pipeup.backend.creator.integration.dto.YoutubeChannel;
import in.pipeup.backend.creator.mapper.CreatorAnalyticsMapper;
import in.pipeup.backend.creator.repository.CreatorAnalyticsRepository;
import in.pipeup.backend.creator.repository.CreatorProfileRepository;
import in.pipeup.backend.entity.User;
import in.pipeup.backend.exception.CreatorAnalyticsNotFoundException;
import in.pipeup.backend.exception.CreatorProfileNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreatorAnalyticsServiceImpl implements ICreatorAnalyticsService{


    private final CreatorAnalyticsRepository creatorAnalyticsRepository;
    private final CreatorAnalyticsMapper creatorAnalyticsMapper;
    private final YoutubeService youtubeService;


    private final CurrentUserService currentUserService;
    private final CreatorProfileRepository creatorProfileRepository;



    @Transactional
    @Override
    public void initializeAnalytics(CreatorProfile creatorProfile) {
        
        if (creatorProfile.getYoutubeChannelUrl() == null
                || creatorProfile.getYoutubeChannelUrl().isBlank()) {

            System.out.println("URL is empty");
            return;
        }

        String handle = YoutubeUtil.extractHandle(
                creatorProfile.getYoutubeChannelUrl());

        System.out.println("Handle : " + handle);

        if (handle == null) {

            System.out.println("Handle extraction failed");
            return;
        }

        String channelId = youtubeService.getChannelId(handle);

        System.out.println("Channel ID : " + channelId);

        if (channelId == null) {
            System.out.println("Channel ID not found");
            return;
        }

        YoutubeChannel channel = youtubeService.getChannel(channelId);

        System.out.println("Channel : " + channel);

        if (channel == null) {

            System.out.println("Channel fetch failed");
            return;
        }

        CreatorAnalytics analytics =
                creatorAnalyticsMapper.toEntity(channel, creatorProfile);

        System.out.println("Saving analytics...");

        creatorAnalyticsRepository.save(analytics);

        System.out.println("Analytics saved successfully.");

    }


    @Override
    public CreatorAnalyticsResponse getAnalytics() {

        User currentUser = currentUserService.getCurrentUser();

        CreatorProfile creatorProfile = creatorProfileRepository
                .findByUser(currentUser).orElseThrow(() -> new CreatorProfileNotFoundException("Creator profile not found."));

        CreatorAnalytics analytics = creatorAnalyticsRepository
                .findByCreatorProfile(creatorProfile).orElseThrow(() ->
                        new RuntimeException("Creator analytics not found."));

        return creatorAnalyticsMapper.toResponse(analytics);
    }



    @Transactional
    @Override
    public CreatorAnalyticsResponse refreshAnalytics() {

        User currentUser = currentUserService.getCurrentUser();

        CreatorProfile creatorProfile = creatorProfileRepository
                .findByUser(currentUser)
                .orElseThrow(() -> new CreatorProfileNotFoundException("Creator profile not found."));

        CreatorAnalytics analytics = creatorAnalyticsRepository
                .findByCreatorProfile(creatorProfile).orElseThrow(() -> new RuntimeException("Creator analytics not found."));

        String handle = YoutubeUtil.extractHandle(creatorProfile.getYoutubeChannelUrl());

        String channelId = youtubeService.getChannelId(handle);

        if (channelId == null) {
            throw new IllegalStateException(
                    "Unable to resolve YouTube channel."
            );
        }


        YoutubeChannel channel = youtubeService.getChannel(channelId);

        if (channel == null) {
            throw new IllegalStateException(
                    "Unable to fetch latest YouTube analytics."
            );
        }

        analytics.setChannelId(channel.getId());
        analytics.setChannelName(channel.getSnippet().getTitle());
        analytics.setSubscriberCount(channel.getStatistics().getSubscriberCount());
        analytics.setTotalViews(channel.getStatistics().getViewCount());
        analytics.setTotalVideos(channel.getStatistics().getVideoCount());
        analytics.setLastSyncedAt(LocalDateTime.now());

        creatorAnalyticsRepository.save(analytics);

        return creatorAnalyticsMapper.toResponse(analytics);

    }




    @Override
    public CreatorAnalyticsResponse getCreatorAnalytics(Long creatorId) {

        // Find creator profile
        CreatorProfile creatorProfile = creatorProfileRepository.findById(creatorId)
                         .orElseThrow(() -> new CreatorProfileNotFoundException("Creator profile not found."));

        // Find analytics of that creator
        CreatorAnalytics creatorAnalytics = creatorAnalyticsRepository.findByCreatorProfile(creatorProfile)
                                       .orElseThrow(() -> new CreatorAnalyticsNotFoundException("Creator analytics not found."));

        // Convert Entity -> DTO
        return creatorAnalyticsMapper.toResponse(creatorAnalytics);
    }


}
