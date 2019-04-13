package Domain.Exceptions;

public class CardValidatorException extends RuntimeException {
    public CardValidatorException(String message) {
        super("CardValidatorException ||| " + message);
    }
}
