package in.pipeup.backend.auth.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VerifyOtpResponse {

    private String message;
    private String resetToken;
}
