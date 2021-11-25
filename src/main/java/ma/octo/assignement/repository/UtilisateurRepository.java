package ma.octo.assignement.repository;

import ma.octo.assignement.domain.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
        
    Utilisateur findUserById(Long id);

    Utilisateur findUserByUsername(String username);
}
