package in.pipeup.backend.campaign.application.exception;

public class UnauthorizedCampaignAccessException extends RuntimeException{

    public UnauthorizedCampaignAccessException(String message) {
        super(message);
    }
}
