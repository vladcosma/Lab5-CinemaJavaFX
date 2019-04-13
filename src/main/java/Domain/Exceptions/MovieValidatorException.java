package Domain.Exceptions;

public class MovieValidatorException extends RuntimeException {
    public MovieValidatorException(String message) {
        super("MovieValidatiorException ||| " + message);
    }
}
