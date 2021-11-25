package ma.octo.assignement.dto;

import java.math.BigDecimal;
import java.util.Date;

public class VersementDto {
    private BigDecimal montantVersement;
    private Date date;
    private String nom_prenom_emetteur;
    private String nrCompteBeneficiaire;
    private String motifVersement;

    public BigDecimal getMontantVersement() {
        return montantVersement;
    }

    public void setMontantVersement(BigDecimal montantVersement) {
        this.montantVersement = montantVersement;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNom_prenom_emetteur() {
        return nom_prenom_emetteur;
    }

    public void setNom_prenom_emetteur(String nom_prenom_emetteur) {
        this.nom_prenom_emetteur = nom_prenom_emetteur;
    }

    public String getNrCompteBeneficiaire() {
        return nrCompteBeneficiaire;
    }

    public void setNrCompteBeneficiaire(String nrCompteBeneficiaire) {
        this.nrCompteBeneficiaire = nrCompteBeneficiaire;
    }

    public String getMotifVersement() {
        return motifVersement;
    }

    public void setMotifVersement(String motif) {
        this.motifVersement = motif;
    }
}
