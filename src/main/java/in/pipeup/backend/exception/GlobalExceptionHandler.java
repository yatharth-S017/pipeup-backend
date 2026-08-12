package in.pipeup.backend.exception;

import in.pipeup.backend.campaign.application.exception.ApplicationAlreadyExistsException;
import in.pipeup.backend.campaign.application.exception.ApplicationNotFoundException;
import in.pipeup.backend.campaign.application.exception.CampaignNotAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {

        ApiErrorResponse response = new ApiErrorResponse(
                false,
                ex.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> handleBadCredentialsException(BadCredentialsException ex) {

        ApiErrorResponse response = new ApiErrorResponse(
                false,
                "Invalid email or password",
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception ex) {

        ApiErrorResponse response = new ApiErrorResponse(
                false,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {

        ApiErrorResponse response = new ApiErrorResponse(
                false,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidPasswordException(InvalidPasswordException ex) {

        ApiErrorResponse response = new ApiErrorResponse(
                false,
                ex.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(CreatorProfileAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleCreatorProfileAlreadyExists(
            CreatorProfileAlreadyExistsException ex) {

        ApiErrorResponse response = new ApiErrorResponse(
                false,
                ex.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }


    @ExceptionHandler(CreatorProfileNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleCreatorProfileNotFound(
            CreatorProfileNotFoundException ex) {

        ApiErrorResponse response = new ApiErrorResponse(
                false,
                ex.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(CreatorAnalyticsNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleCreatorAnalyticsNotFoundException(
            CreatorAnalyticsNotFoundException ex) {

        ApiErrorResponse response = ApiErrorResponse.builder()
                .success(false)
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


    @ExceptionHandler(InvalidCampaignException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidCampaignException(
            InvalidCampaignException ex) {

        ApiErrorResponse response = new ApiErrorResponse(
                false,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(YoutubeApiException.class)
    public ResponseEntity<ApiErrorResponse> handleYoutubeApiException(
            YoutubeApiException ex) {

        ApiErrorResponse response = new ApiErrorResponse(
                false,
                ex.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(InvalidYoutubeUrlException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidYoutubeUrlException(
            InvalidYoutubeUrlException ex) {

        ApiErrorResponse response = new ApiErrorResponse(
                false,
                ex.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(ChannelNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleChannelNotFoundException(
            ChannelNotFoundException ex) {

        ApiErrorResponse response = new ApiErrorResponse(
                false,
                ex.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(CampaignNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleCampaignNotFoundException(
            CampaignNotFoundException ex) {

        ApiErrorResponse response = new ApiErrorResponse(
                false,
                ex.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(BrandProfileNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleBrandProfileNotFoundException(
            BrandProfileNotFoundException ex) {

        ApiErrorResponse response = new ApiErrorResponse(
                false,
                ex.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(BrandProfileAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleBrandProfileAlreadyExistsException(
            BrandProfileAlreadyExistsException ex) {

        ApiErrorResponse response = new ApiErrorResponse(
                false,
                ex.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }




    @ExceptionHandler(ApplicationAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleApplicationAlreadyExistsException(ApplicationAlreadyExistsException ex) {

        ApiErrorResponse response = ApiErrorResponse.builder()
                .success(false)
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }



    @ExceptionHandler(ApplicationNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleApplicationNotFoundException(ApplicationNotFoundException ex) {

        ApiErrorResponse response = ApiErrorResponse.builder()
                .success(false)
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(CampaignNotAvailableException.class)
    public ResponseEntity<ApiErrorResponse> handleCampaignNotAvailableException(CampaignNotAvailableException ex) {

        ApiErrorResponse response = ApiErrorResponse.builder()
                .success(false)
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }





    

}
