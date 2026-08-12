package in.pipeup.backend.campaign.campaign.dto;

import in.pipeup.backend.campaign.campaign.enums.Platform;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignDetailsResponse {

    Long id;

    String title;

    String brandName;

    String description;

    String requirements;

    Set<Platform> platforms;

    BigDecimal totalBudget;

    BigDecimal payoutPerCreator;

    Integer requiredCreators;

    LocalDate applicationDeadline;

    LocalDate submissionDeadline;

    List<String> attachmentUrls;
}
