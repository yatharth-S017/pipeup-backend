package in.pipeup.backend.campaign.application.exception;

public class ApplicationAlreadyExistsException  extends RuntimeException{

    public ApplicationAlreadyExistsException(String message) {
        super(message);
    }
}
