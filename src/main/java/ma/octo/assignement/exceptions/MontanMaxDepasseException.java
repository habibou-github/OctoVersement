package ma.octo.assignement.exceptions;

public class MontanMaxDepasseException extends Exception{

    private static final long serialVersionUID = 1L;

    public MontanMaxDepasseException() {
    }

    public MontanMaxDepasseException(String message) {
        super(message);
    }

}
