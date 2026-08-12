package in.pipeup.backend.creator.integration.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class YoutubeSearchResponse {

    private List<YoutubeSearchItem> items;
}