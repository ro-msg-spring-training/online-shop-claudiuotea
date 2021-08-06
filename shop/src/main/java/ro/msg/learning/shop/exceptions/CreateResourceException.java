package ro.msg.learning.shop.exceptions;

public class CreateResourceException extends RuntimeException{
    public CreateResourceException(String message) {
        super(message);
    }

    public CreateResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}
