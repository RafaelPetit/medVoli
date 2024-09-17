package med.voli.api.infra.exception;

public class ExistingValidationException extends RuntimeException {
    public ExistingValidationException(String message) {
        super(message);
    }
}
