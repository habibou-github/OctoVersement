package ma.octo.assignement.mapper;

import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.dto.VersementDto;

public class VersementMapper {
    private static VersementDto versementDto;

    public static VersementDto map(Versement versement){
        versementDto =  new VersementDto();
        versementDto.setMontantVersement(versement.getMontantVersement());
        versementDto.setDate(versement.getDateExecution());
        versementDto.setNrCompteBeneficiaire(versement.getCompteBeneficiaire().getNrCompte());
        versementDto.setMotifVersement(versement.getMotifVersement());

        return versementDto;
    }
}
