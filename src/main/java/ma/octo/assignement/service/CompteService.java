package ma.octo.assignement.service;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.dto.CompteDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CompteService {

    public Compte saveCompte(CompteDto compteDto);
    public List<Compte> findAllComptes();
    public Compte findCompteById(Long id);
    public Compte update(CompteDto CompteDto);
    public ResponseEntity<?> deletCompte(Long id);
}
