package in.pipeup.backend.creator.integration.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class YoutubeChannel {

    private String id;

    private YoutubeSnippet snippet;

    private YoutubeStatistics statistics;

}
