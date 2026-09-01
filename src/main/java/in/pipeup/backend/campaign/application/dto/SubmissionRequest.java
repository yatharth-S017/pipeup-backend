package in.pipeup.backend.campaign.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubmissionRequest {
    @NotBlank(message = "Submission URL is required")
    @Pattern(
            regexp = "^(https?://).+",
            message = "Please provide a valid URL"
    )
    private String submissionUrl;
}
