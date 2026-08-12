package in.pipeup.backend.campaign.application.dto;

import in.pipeup.backend.campaign.application.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDetailsResponse {

    private Long applicationId;

    private Long creatorId;

    private String fullName;

    private String displayName;

    private String youtubeChannelUrl;

    private String instagramProfileUrl;

    private String message;

    private ApplicationStatus status;

    private LocalDateTime appliedAt;
}
