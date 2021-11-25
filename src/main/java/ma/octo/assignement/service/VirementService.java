package ma.octo.assignement.service;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.dto.VirementDto;
import ma.octo.assignement.exceptions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface VirementService {

    public ResponseEntity createTransaction(@RequestBody VirementDto virementDto)
            throws MontanNonAtteintException, SoldeDisponibleInsuffisantException, CompteNonExistantException, TransactionException, MontanMaxDepasseException;

    public List<Compte> loadAllCompte();

    public List<Virement> loadAll();

}
