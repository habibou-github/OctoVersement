package ma.octo.assignement.service;

import ma.octo.assignement.domain.AuditVersement;
import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.dto.VersementDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.MontanMaxDepasseException;
import ma.octo.assignement.exceptions.MontanNonAtteintException;
import ma.octo.assignement.exceptions.TransactionException;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface VersementService {

    public ResponseEntity createVersement(VersementDto versementDto)
            throws MontanNonAtteintException,CompteNonExistantException, TransactionException, MontanMaxDepasseException;

    public List<Versement> loadAll();

    public List<AuditVersement> loadAllAudit();

}
