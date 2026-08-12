package in.pipeup.backend.creator.integration;

import in.pipeup.backend.creator.integration.dto.YoutubeChannelResponse;
import in.pipeup.backend.creator.integration.dto.YoutubeSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class YoutubeApiClient {

    private final WebClient webClient;

    @Value("${youtube.api.key}")
    private String apiKey;

    @Value("${youtube.api.base-url}")
    private String baseUrl;

    public YoutubeChannelResponse getChannelDetails(String channelId) {

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/channels")
                        .queryParam("part", "snippet,statistics")
                        .queryParam("id", channelId)
                        .queryParam("key", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(YoutubeChannelResponse.class)
                .block();
    }


    public YoutubeSearchResponse searchChannel(String handle) {

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search")
                        .queryParam("part", "snippet")
                        .queryParam("q", handle)
                        .queryParam("type", "channel")
                        .queryParam("maxResults", 1)
                        .queryParam("key", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(YoutubeSearchResponse.class)
                .block();

    }
    

}
