package in.pipeup.backend.exception;

public class BrandProfileNotFoundException extends RuntimeException {
    public BrandProfileNotFoundException(String message) {
        super(message);
    }
}
