package in.pipeup.backend.campaign.campaign.dto;

import in.pipeup.backend.campaign.campaign.enums.Platform;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCampaignRequest {

    private String title;

    private String description;

    private String requirements;

    private Set<Platform> platforms;

    @Positive(message = "Budget must be greater than 0")
    private BigDecimal totalBudget;

    @Positive(message = "Payout must be greater than 0")
    private BigDecimal payoutPerCreator;

    @Positive(message = "Required creators must be greater than 0")
    private Integer requiredCreators;

    @Future(message = "Application deadline must be in the future")
    private LocalDate applicationDeadline;

    @Future(message = "Submission deadline must be in the future")
    private LocalDate submissionDeadline;

    private List<String> attachmentUrls;
}