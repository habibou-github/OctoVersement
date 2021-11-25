package ma.octo.assignement.service.ServiceImplementation;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.dto.CompteDto;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.UtilisateurRepository;
import ma.octo.assignement.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CompteServiceImpl implements CompteService {

    @Autowired
    CompteRepository compteRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public Compte saveCompte(CompteDto compteDto) {

        Compte compte = compteRepository.findByNrCompte(compteDto.getNrCompte());
        return compteRepository.save(compte);
    }

    @Override
    public List<Compte> findAllComptes() {
        List<Compte> Comptes = compteRepository.findAll();

        if (CollectionUtils.isEmpty(Comptes)) {
            return null;
        } else {
            return Comptes;
        }
    }

    @Override
    public Compte findCompteById(Long id) {
        return compteRepository.findCompteById(id);
    }

    @Override
    public Compte update(CompteDto compteDto) {
        Compte compteUpdated = compteRepository.findByNrCompte(compteDto.getNrCompte());

        compteUpdated.setRib(compteDto.getRib());
        compteUpdated.setSolde(compteDto.getSolde());
        Utilisateur utilisateur = utilisateurRepository.findUserByUsername(compteDto.getUtilisateurUsername());
        compteUpdated.setUtilisateur(utilisateur);

        return compteRepository.save(compteUpdated);
    }

    @Override
    public ResponseEntity<?> deletCompte(Long id) {
        Compte compte = compteRepository.findCompteById(id);
        compteRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
