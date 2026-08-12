package in.pipeup.backend.creator.integration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class YoutubeSnippet {

    private String title;

    private String description;

    @JsonProperty("customUrl")
    private String customUrl;
    private String publishedAt;

}