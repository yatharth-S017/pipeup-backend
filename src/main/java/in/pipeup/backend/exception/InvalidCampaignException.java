package in.pipeup.backend.exception;

public class InvalidCampaignException extends RuntimeException{
    public InvalidCampaignException(String message) {
        super(message);
    }
}
