package ma.octo.assignement.web;

import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.dto.UtilisateurDto;
import ma.octo.assignement.exceptions.UserNotFoundException;
import ma.octo.assignement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save_user")
    public Utilisateur saveUser(@RequestBody Utilisateur user){

        return userService.saveUser(user);
    }

    @GetMapping("/all_users")
    public List<Utilisateur> getAllUsers() {

        return userService.findAllUtilisateurs();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Utilisateur> getUser(@PathVariable(value = "id") Long id) throws UserNotFoundException {

        return userService.findUserById(id);
    }

    @PutMapping("/edit_user/{id}")
    public ResponseEntity<Utilisateur> editUser(@PathVariable(value = "id") Long id, @RequestBody UtilisateurDto userDto) throws UserNotFoundException {

        Utilisateur userUpdated = userService.update(id,userDto);

        return ResponseEntity.ok().body(userUpdated);

    }

    @DeleteMapping("/delet_user/{id}")
    public ResponseEntity<?> deletUser(@PathVariable(value = "id") Long id) throws UserNotFoundException {

        return userService.deletUser(id);
    }





}
