package in.pipeup.backend.campaign.application.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyCampaignRequest {

    @Size(max = 1000, message = "Application message cannot exceed 1000 characters.")
    private String message;
}
