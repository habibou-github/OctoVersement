package ma.octo.assignement.service.ServiceImplementation;

import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.dto.UtilisateurDto;
import ma.octo.assignement.exceptions.UserNotFoundException;
import ma.octo.assignement.repository.UtilisateurRepository;
import ma.octo.assignement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public Utilisateur saveUser(Utilisateur user) {
        return utilisateurRepository.save(user);
    }

    @Override
    public List<Utilisateur> findAllUtilisateurs() {
        List<Utilisateur> all = utilisateurRepository.findAll();

        if (CollectionUtils.isEmpty(all)) {
            return null;
        } else {
            return all;
        }
    }

    @Override
    public ResponseEntity<Utilisateur> findUserById(Long id) throws UserNotFoundException {
        Utilisateur user = utilisateurRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User Not Founde Exeptions with the id = " + id));

        return ResponseEntity.ok().body(user);
    }


    public Utilisateur update(Long id, UtilisateurDto userDto) throws UserNotFoundException{

        Utilisateur userUpdated = utilisateurRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User Not Founde Exeptions with the id = " + id));

        userUpdated.setUsername(userDto.getUsername());
        userUpdated.setFirstname(userDto.getFirstname());
        userUpdated.setLastname(userDto.getLastname());
        userUpdated.setGender(userDto.getGender());

        return utilisateurRepository.save(userUpdated);

    }

    @Override
    public ResponseEntity<?> deletUser(Long id) throws UserNotFoundException{
        utilisateurRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User Not Founde Exeptions with the id = " + id));

        utilisateurRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
