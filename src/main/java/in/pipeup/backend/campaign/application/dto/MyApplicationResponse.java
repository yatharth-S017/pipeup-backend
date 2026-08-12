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
public class MyApplicationResponse {

    private Long applicationId;

    private Long campaignId;

    private String campaignTitle;

    private String brandName;

    private ApplicationStatus status;

    private LocalDateTime appliedAt;
}

