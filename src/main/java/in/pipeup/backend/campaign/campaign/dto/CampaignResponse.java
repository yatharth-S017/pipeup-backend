package in.pipeup.backend.campaign.campaign.dto;

import in.pipeup.backend.campaign.campaign.enums.CampaignStatus;
import in.pipeup.backend.campaign.campaign.enums.Platform;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CampaignResponse {

    private Long id;

    private String title;

    private String description;

    private String requirements;

    private Set<Platform> platforms;

    private BigDecimal totalBudget;

    private BigDecimal payoutPerCreator;

    private Integer requiredCreators;

    private LocalDate applicationDeadline;

    private LocalDate submissionDeadline;

    private List<String> attachmentUrls;

    private CampaignStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}