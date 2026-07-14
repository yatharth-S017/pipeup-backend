package in.pipeup.backend.creator.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
public class CompleteCreatorOnboardingRequest {

    @NotBlank(message = "Display name is required")
    private String displayName;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Enter a valid 10-digit Indian mobile number")
    private String phoneNumber;

    private String youtubeChannelUrl;

    private String instagramProfileUrl;

    @NotEmpty(message = "Select at least one niche")
    @Size(max = 5, message = "Maximum 5 niches allowed")
    private Set<String> niches = new HashSet<>();

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "City is required")
    private String city;

    @Size(max = 500)
    private String currentChallenge;

    @Size(max = 500)
    private String expectedSupport;

    @NotNull(message = "Starting price is required")
    @DecimalMin(value = "0")
    private BigDecimal startingPrice;


}
