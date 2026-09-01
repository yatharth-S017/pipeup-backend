package in.pipeup.backend.campaign.application.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SubmissionResponse {
    private Long applicationId;
    private String submissionUrl;
    private LocalDateTime submittedAt;
}
