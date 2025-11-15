package lt.viko.eif.dscerbinkinas.PlanuokBack.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
