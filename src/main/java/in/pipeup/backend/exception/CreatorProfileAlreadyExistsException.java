package in.pipeup.backend.exception;

public class CreatorProfileAlreadyExistsException extends RuntimeException{
    public CreatorProfileAlreadyExistsException(String message) {
        super(message);
    }
}
