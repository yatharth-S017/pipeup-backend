package in.pipeup.backend.creator.integration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class YoutubeSearchId {

    @JsonProperty("channelId")
    private String channelId;
}