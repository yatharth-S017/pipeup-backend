package in.pipeup.backend.creator.integration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class YoutubeStatistics {

    @JsonProperty("subscriberCount")
    private Long subscriberCount;

    @JsonProperty("viewCount")
    private Long viewCount;

    @JsonProperty("videoCount")
    private Long videoCount;

}
