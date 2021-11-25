package ma.octo.assignement.dto;

import java.math.BigDecimal;

public class CompteDto {

    private String nrCompte;
    private String rib;
    private BigDecimal solde;
    private String utilisateurUsername;

    public String getNrCompte() {
        return nrCompte;
    }

    public void setNrCompte(String nrCompte) {
        this.nrCompte = nrCompte;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public BigDecimal getSolde() {
        return solde;
    }

    public void setSolde(BigDecimal solde) {
        this.solde = solde;
    }

    public String getUtilisateurUsername() {
        return utilisateurUsername;
    }

    public void setUtilisateurUsername(String utilisateurUsername) {
        this.utilisateurUsername = utilisateurUsername;
    }
}
