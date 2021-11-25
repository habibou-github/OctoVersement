package ma.octo.assignement.exceptions;

public class MontanNonAtteintException extends Exception{

    private static final long serialVersionUID = 1L;

    public MontanNonAtteintException() {
    }

    public MontanNonAtteintException(String message) {
        super(message);
    }
}
