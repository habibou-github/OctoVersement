package ma.octo.assignement.web;

import ma.octo.assignement.domain.AuditVersement;
import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.dto.VersementDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.MontanMaxDepasseException;
import ma.octo.assignement.exceptions.MontanNonAtteintException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.service.VersementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/versement")
public class VersementController {

    public static final int MONTANT_MAXIMAL = 10000;

    Logger LOGGER = LoggerFactory.getLogger(VirementController.class);

    @Autowired
    private VersementService versementService;


    @GetMapping("/lister_versements")
    public List<Versement> loadAll() {

        return versementService.loadAll();
    }

    @GetMapping("/audit_versements")
    public List<AuditVersement> loadAllAudit() {

        return versementService.loadAllAudit();
    }

    @PostMapping("/executerVersements")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createVersement(@RequestBody VersementDto versementDto)
            throws MontanNonAtteintException,MontanMaxDepasseException,CompteNonExistantException, TransactionException {

        return versementService.createVersement(versementDto);
    }

}
