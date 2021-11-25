package ma.octo.assignement.service.ServiceImplementation;

import ma.octo.assignement.domain.AuditVersement;
import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.dto.VersementDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.MontanMaxDepasseException;
import ma.octo.assignement.exceptions.MontanNonAtteintException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.AuditVersementRepository;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.VersementRepository;
import ma.octo.assignement.service.AutiService;
import ma.octo.assignement.service.VersementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class VersementServiceImpl implements VersementService {

    public static final int MONTANT_MAXIMAL = 10000;


    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private VersementRepository versementRepository;
    @Autowired
    private AuditVersementRepository auditVersementRepository;
    @Autowired
    private AutiService monservice;


    @Override
    public ResponseEntity createVersement(VersementDto versementDto) throws MontanNonAtteintException,MontanMaxDepasseException,CompteNonExistantException, TransactionException {
        Compte compteBeneficiaire = compteRepository.findByNrCompte(versementDto.getNrCompteBeneficiaire());

        if (compteBeneficiaire == null) {
            System.out.println("Compte Non existant");
            throw new CompteNonExistantException("Compte Non existant");

        }
        if (versementDto.getMontantVersement().equals(null)) {
            System.out.println("Montant vide");
            throw new TransactionException("Montant vide");
        } else if (versementDto.getMontantVersement().intValue() == 0) {
            System.out.println("Montant vide");
            throw new TransactionException("Montant vide");
        } else if (versementDto.getMontantVersement().intValue() < 10) {
            System.out.println("Montant minimal de versemen non atteint (Minimum 10 Dh)");
            throw new MontanNonAtteintException("Montant versemen de virement non atteint (Minimum 10 Dh)");
        } else if (versementDto.getMontantVersement().intValue() > MONTANT_MAXIMAL) {
            System.out.println("Montant maximal de versemen dépassé (Maximum 10.000 Dh)");
            throw new MontanMaxDepasseException("Montant maximal de versemen dépassé (Maximum 10.000 Dh)");
        }
        if (versementDto.getMotifVersement().length() < 0) {
            System.out.println("Motif vide");
            throw new TransactionException("Motif vide");
        }

        compteBeneficiaire
                .setSolde(new BigDecimal(compteBeneficiaire.getSolde().intValue()
                        + versementDto.getMontantVersement().intValue()));

        compteRepository.save(compteBeneficiaire);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        Versement versement = new Versement();
        versement.setMontantVersement(versementDto.getMontantVersement());
        versement.setDateExecution(date);
        versement.setNom_prenom_emetteur(versementDto.getNom_prenom_emetteur());
        versement.setCompteBeneficiaire(compteBeneficiaire);
        versement.setMotifVersement(versementDto.getMotifVersement());

        versementRepository.save(versement);


        String message = "Versement depuis " + versementDto.getNom_prenom_emetteur() + " vers "
                + compteBeneficiaire.getUtilisateur().getFirstname() + " "
                + compteBeneficiaire.getUtilisateur().getLastname() + " ( "
                + versementDto.getNrCompteBeneficiaire() + " ) d'un montant de "
                + versementDto.getMontantVersement().toString() +
                " ,Le " + formatter.format(date);

        monservice.auditVersement(message);

        return new ResponseEntity<>(message, null, HttpStatus.CREATED);
    }

    @Override
    public List<Versement> loadAll() {
        List<Versement> all = versementRepository.findAll();

        if (CollectionUtils.isEmpty(all)) {
            return null;
        } else {
            return all;
        }
    }

    @Override
    public List<AuditVersement> loadAllAudit() {
        List<AuditVersement> all = auditVersementRepository.findAll();

        if (CollectionUtils.isEmpty(all)) {
            return null;
        } else {
            return all;
        }
    }
}
