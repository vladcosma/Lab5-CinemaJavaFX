package Service.Exceptions;

public class BookingServiceException extends RuntimeException {
    public BookingServiceException(String message){
        super("BookingServiceException ||| " + message);
    }
}
