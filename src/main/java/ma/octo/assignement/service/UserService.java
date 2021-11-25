package ma.octo.assignement.service;

import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.dto.UtilisateurDto;
import ma.octo.assignement.exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    public Utilisateur saveUser(Utilisateur user);
    public List<Utilisateur> findAllUtilisateurs();
    public ResponseEntity<Utilisateur> findUserById(Long id) throws UserNotFoundException;
    public Utilisateur update(Long id, UtilisateurDto userDto) throws UserNotFoundException;
    public ResponseEntity<?> deletUser(Long id) throws UserNotFoundException;

}
