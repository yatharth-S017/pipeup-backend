package in.pipeup.backend.creator.integration;

import in.pipeup.backend.creator.integration.dto.YoutubeChannel;
import in.pipeup.backend.creator.integration.dto.YoutubeChannelResponse;
import in.pipeup.backend.creator.integration.dto.YoutubeSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class YoutubeService {

    private final YoutubeApiClient youtubeApiClient;

    /**
     * Returns Channel ID from YouTube Handle
     */
    public String getChannelId(String handle) {

        YoutubeSearchResponse response = youtubeApiClient.searchChannel(handle);

        if (response == null || response.getItems() == null || response.getItems().isEmpty()) {

            return null;
        }

        return response.getItems()
                .get(0)
                .getId()
                .getChannelId();

    }

    /**
     * Returns complete channel information.
     */
    public YoutubeChannel getChannel(String channelId) {

        YoutubeChannelResponse response =
                youtubeApiClient.getChannelDetails(channelId);

        if (response == null
                || response.getItems() == null
                || response.getItems().isEmpty()) {

            return null;
        }

        return response.getItems().get(0);

    }

}