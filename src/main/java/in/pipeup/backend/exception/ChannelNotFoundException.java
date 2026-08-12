package in.pipeup.backend.exception;

public class ChannelNotFoundException extends RuntimeException {

    public ChannelNotFoundException(String message) {
        super(message);
    }
}