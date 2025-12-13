package lt.viko.eif.dscerbinkinas.PlanuokBack.exception;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(String message) {
        super(message);
    }
}
