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
public class CreateCampaignRequest {

    @NotBlank(message = "Campaign title is required")
    private String title;

    @NotBlank(message = "Campaign description is required")
    private String description;

    @NotBlank(message = "Campaign requirements are required")
    private String requirements;

    @NotEmpty(message = "Select at least one platform")
    private Set<Platform> platforms;

    @NotNull(message = "Total budget is required")
    @Positive(message = "Budget must be greater than 0")
    private BigDecimal totalBudget;

    @NotNull(message = "Payout per creator is required")
    @Positive(message = "Payout must be greater than 0")
    private BigDecimal payoutPerCreator;

    @NotNull(message = "Required creators is required")
    @Positive(message = "Required creators must be greater than 0")
    private Integer requiredCreators;

    @NotNull(message = "Application deadline is required")
    @Future(message = "Application deadline must be in the future")
    private LocalDate applicationDeadline;

    @NotNull(message = "Submission deadline is required")
    @Future(message = "Submission deadline must be in the future")
    private LocalDate submissionDeadline;

    private List<String> attachmentUrls;
}
