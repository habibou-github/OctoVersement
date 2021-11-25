package ma.octo.assignement.web;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.dto.VersementDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.MontanMaxDepasseException;
import ma.octo.assignement.exceptions.MontanNonAtteintException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.service.VersementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VersementController.class)
class VersementControllerTest {
/*
    @Autowired
    private MockMvc mvc;

    public static final int MONTANT_MAXIMAL = 10000;

    Logger LOGGER = LoggerFactory.getLogger(VirementController.class);

    @Autowired
    CompteRepository compteRepository;
    @Autowired
    VersementService versementService;

    @Test
    void createVersement() throws Exception {
        VersementController versementController = new VersementController();

        VersementDto versementDto = new VersementDto();

        versementDto.setNom_prenom_emetteur("HABIBOU Hamza");
        versementDto.setNrCompteBeneficiaire("010000B025001000");
        versementDto.setMotifVersement("Pour un motif de Versement");
        versementDto.setMontantVersement(BigDecimal.valueOf(5000));
        versementDto.setDate(null);

        RequestBuilder request = MockMvcRequestBuilders.post("/versement/executerVersements");
        MvcResult result = mvc.perform(request).andReturn();



        //Assert
        Compte compteBeneficiaire = compteRepository.findByNrCompte(versementDto.getNrCompteBeneficiaire());

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        String message = "Versement depuis " + versementDto.getNom_prenom_emetteur() + " vers "
                + compteBeneficiaire.getUtilisateur().getFirstname() + " "
                + compteBeneficiaire.getUtilisateur().getLastname() + " ( "
                + versementDto.getNrCompteBeneficiaire() + " ) d'un montant de "
                + versementDto.getMontantVersement().toString() +
                " ,Le " + formatter.format(date);



        assertEquals(new ResponseEntity<>(message, null, HttpStatus.CREATED), result.getResponse().getContentAsString());
    }

 */
}