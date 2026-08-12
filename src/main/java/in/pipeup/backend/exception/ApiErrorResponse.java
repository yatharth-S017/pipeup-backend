package in.pipeup.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class ApiErrorResponse {

    private boolean success;
    private String message;
    private LocalDateTime timestamp;
}
