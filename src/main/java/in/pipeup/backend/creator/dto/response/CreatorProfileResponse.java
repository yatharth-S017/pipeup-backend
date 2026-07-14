package in.pipeup.backend.creator.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatorProfileResponse {
    private Long id;

    private String displayName;

    private String email;

    private String phoneNumber;

    private String youtubeChannelUrl;

    private String instagramProfileUrl;

    private Set<String> niches;

    private String state;

    private String city;

    private String currentChallenge;

    private String expectedSupport;

    private BigDecimal startingPrice;

    private String profileImageUrl;

    private Boolean onboardingCompleted;

    private LocalDateTime onboardingCompletedAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
