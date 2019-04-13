package Service.Exceptions;

public class CardServiceException extends RuntimeException {

    public  CardServiceException(String message){
        super("CardServiceException ||| " + message);
    }
}