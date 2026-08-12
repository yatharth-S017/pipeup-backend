package in.pipeup.backend.exception;

public class BrandProfileAlreadyExistsException extends RuntimeException{
    public BrandProfileAlreadyExistsException(String message) {
        super(message);
    }
}
