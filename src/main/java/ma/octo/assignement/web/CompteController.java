package ma.octo.assignement.web;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.dto.CompteDto;
import ma.octo.assignement.dto.UtilisateurDto;
import ma.octo.assignement.exceptions.UserNotFoundException;
import ma.octo.assignement.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/compte")
public class CompteController {

    @Autowired
    CompteService compteService;


    @PostMapping("/save_compte")
    public Compte saveCompte(@RequestBody CompteDto compteDto){

        return compteService.saveCompte(compteDto);
    }

    @GetMapping("/all_comptes")
    public List<Compte> getAllComptes() {

        return compteService.findAllComptes();
    }

    @GetMapping("/compte/{id}")
    public Compte getCompte(@PathVariable(value = "id") Long id){

        return compteService.findCompteById(id);
    }

    @PutMapping("/edit_compte/{id}")
    public Compte editUser(@RequestBody CompteDto compteDto){

        return compteService.update(compteDto);

    }

    @DeleteMapping("/delet_compte/{id}")
    public ResponseEntity<?> deletUser(@PathVariable(value = "id") Long id){

        return compteService.deletCompte(id);
    }

}
