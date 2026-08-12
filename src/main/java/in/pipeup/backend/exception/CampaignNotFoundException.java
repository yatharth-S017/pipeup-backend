package in.pipeup.backend.exception;

public class CampaignNotFoundException extends RuntimeException {

    public CampaignNotFoundException(String message) {
        super(message);
    }
}
