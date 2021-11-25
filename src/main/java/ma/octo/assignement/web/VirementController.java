package ma.octo.assignement.web;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.dto.VirementDto;
import ma.octo.assignement.exceptions.*;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.UtilisateurRepository;
import ma.octo.assignement.repository.VirementRepository;
import ma.octo.assignement.service.AutiService;
import ma.octo.assignement.service.VirementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/virements")
public class VirementController {


    @Autowired
    private VirementService virementService;


    @GetMapping("/lister_virements")
    public List<Virement> loadAll() {
        return virementService.loadAll();
    }

    @GetMapping("/lister_comptes")
    public List<Compte> loadAllCompte() {
        return virementService.loadAllCompte();
    }

    @PostMapping("/executerVirements")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createTransaction(@RequestBody VirementDto virementDto)
            throws SoldeDisponibleInsuffisantException, CompteNonExistantException, TransactionException, MontanNonAtteintException, MontanMaxDepasseException {

        return virementService.createTransaction(virementDto);

    }

}
