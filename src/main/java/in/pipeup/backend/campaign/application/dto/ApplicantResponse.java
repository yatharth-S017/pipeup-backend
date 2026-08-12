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
public class ApplicantResponse {

    private Long applicationId;

    private Long creatorId;

    private String creatorName;

    private String channelName;

    private Long subscriberCount;

    private Long totalViews;

    private ApplicationStatus status;

    private LocalDateTime appliedAt;
}
