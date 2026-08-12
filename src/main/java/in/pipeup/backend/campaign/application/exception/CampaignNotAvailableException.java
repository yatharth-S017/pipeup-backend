package in.pipeup.backend.campaign.application.exception;

public class CampaignNotAvailableException extends RuntimeException{

    public CampaignNotAvailableException(String message) {
        super(message);
    }
}
