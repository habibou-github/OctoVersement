package ma.octo.assignement.web.common;

import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.MontanMaxDepasseException;
import ma.octo.assignement.exceptions.MontanNonAtteintException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandelingController {

    @ExceptionHandler(SoldeDisponibleInsuffisantException.class)
    public ResponseEntity<String> handleSoldeDisponibleInsuffisantException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Pas de solde pas de virement", null, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
    }

    @ExceptionHandler(CompteNonExistantException.class)
    public ResponseEntity<String> handleCompteNonExistantException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Compte introuvable", null, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MontanNonAtteintException.class)
    public ResponseEntity<String> handleMontanNonATTeintException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Montant minimal de virement non atteint (min 10 Dh)", null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MontanMaxDepasseException.class)
    public ResponseEntity<String> handleMontanMaxDepasseException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Montan Maximum Dépasse (max 10.000 Dh)", null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
