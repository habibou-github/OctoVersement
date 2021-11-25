package ma.octo.assignement.service.ServiceImplementation;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.dto.VirementDto;
import ma.octo.assignement.exceptions.*;
import ma.octo.assignement.exceptions.MontanNonAtteintException;
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
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class VirementServiceImpl implements VirementService {

    public static final int MONTANT_MAXIMAL = 10000;
    Logger LOGGER = LoggerFactory.getLogger(VirementServiceImpl.class);

    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private VirementRepository virementRepository;
    @Autowired
    private AutiService monservice;
    @Autowired
    private UtilisateurRepository utilisateurRepository;


    @Override
    public ResponseEntity createTransaction(VirementDto virementDto) throws MontanNonAtteintException, SoldeDisponibleInsuffisantException, CompteNonExistantException, TransactionException, MontanMaxDepasseException {
        Compte compteEmetteur = compteRepository.findByNrCompte(virementDto.getNrCompteEmetteur());
        Compte compteBeneficiaire = compteRepository.findByNrCompte(virementDto.getNrCompteBeneficiaire());

        if (compteEmetteur == null) {
            System.out.println("Compte Non existant");
            throw new CompteNonExistantException("Compte Non existant");
        }

        if (compteBeneficiaire == null) {
            System.out.println("Compte Non existant");
            throw new CompteNonExistantException("Compte Non existant");
        }

        if (virementDto.getMontantVirement().equals(null)) {
            System.out.println("Montant vide");
            throw new TransactionException("Montant vide");
        } else if (virementDto.getMontantVirement().intValue() == 0) {
            System.out.println("Montant vide");
            throw new TransactionException("Montant vide");
        } else if (virementDto.getMontantVirement().intValue() < 10) {
            System.out.println("Montant minimal de virement non atteint");
            throw new MontanNonAtteintException("Montant minimal de virement non atteint");
        } else if (virementDto.getMontantVirement().intValue() > MONTANT_MAXIMAL) {
            System.out.println("Montant maximal de virement dépassé");
            throw new MontanMaxDepasseException("Montant maximal de virement dépassé");
        }

        if (virementDto.getMotifVirement().length() < 0) {
            System.out.println("Motif vide");
            throw new TransactionException("Motif vide");
        }

        if (compteEmetteur.getSolde().intValue() - virementDto.getMontantVirement().intValue() < 0) {
            LOGGER.error("Solde insuffisant pour l'utilisateur");
        }

        compteEmetteur.setSolde(compteEmetteur.getSolde().subtract(virementDto.getMontantVirement()));
        compteRepository.save(compteEmetteur);

        compteBeneficiaire
                .setSolde(new BigDecimal(compteBeneficiaire.getSolde().intValue() + virementDto.getMontantVirement().intValue()));
        compteRepository.save(compteBeneficiaire);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        Virement virement = new Virement();
        virement.setDateExecution(date);
        virement.setCompteBeneficiaire(compteBeneficiaire);
        virement.setCompteEmetteur(compteEmetteur);
        virement.setMontantVirement(virementDto.getMontantVirement());
        virement.setMotifVirement(virementDto.getMotifVirement());

        virementRepository.save(virement);

        String message = "Virement depuis " + virementDto.getNrCompteEmetteur() + " vers " + virementDto
                .getNrCompteBeneficiaire() + " d'un montant de " + virementDto.getMontantVirement()
                .toString() + " ,Le " + formatter.format(date);

        monservice.auditVirement(message);

        return new ResponseEntity<>(message, null, HttpStatus.CREATED);

    }

    @Override
    public List<Compte> loadAllCompte() {
        List<Compte> all = compteRepository.findAll();

        if (CollectionUtils.isEmpty(all)) {
            return null;
        } else {
            return all;
        }
    }

    @Override
    public List<Virement> loadAll() {
        List<Virement> all = virementRepository.findAll();

        if (CollectionUtils.isEmpty(all)) {
            return null;
        } else {
            return all;
        }
    }
}
