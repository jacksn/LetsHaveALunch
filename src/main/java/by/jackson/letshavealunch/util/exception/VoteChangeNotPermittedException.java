package by.jackson.letshavealunch.util.exception;

public class VoteChangeNotPermittedException extends RuntimeException {
    public VoteChangeNotPermittedException(String message) {
        super(message);
    }
}
