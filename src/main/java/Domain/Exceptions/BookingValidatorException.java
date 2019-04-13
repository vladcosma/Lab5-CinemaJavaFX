package Domain.Exceptions;

public class BookingValidatorException extends RuntimeException {
    public BookingValidatorException(String message) {
        super("BookingValidatorException ||| " + message);
    }
}
