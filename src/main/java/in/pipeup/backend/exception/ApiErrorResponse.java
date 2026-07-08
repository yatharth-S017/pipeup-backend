package in.pipeup.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ApiErrorResponse {

    private boolean success;
    private String message;
    private LocalDateTime timestamp;
}
