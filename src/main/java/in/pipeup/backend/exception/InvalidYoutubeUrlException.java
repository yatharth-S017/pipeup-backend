package in.pipeup.backend.exception;

public class InvalidYoutubeUrlException extends RuntimeException {

    public InvalidYoutubeUrlException(String message) {
        super(message);
    }
}