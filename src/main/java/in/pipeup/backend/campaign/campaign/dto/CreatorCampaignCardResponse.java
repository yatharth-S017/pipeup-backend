package in.pipeup.backend.campaign.campaign.dto;

import in.pipeup.backend.campaign.campaign.enums.Platform;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatorCampaignCardResponse {

    private Long id;

    private String title;

    private String brandName;

    private Set<Platform> platforms;

    private BigDecimal payoutPerCreator;

    private Integer requiredCreators;
}
