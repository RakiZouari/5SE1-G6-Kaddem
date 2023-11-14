package com.spring.kaddem.controllers;

import com.spring.kaddem.entities.Contrat;
import com.spring.kaddem.entities.DetailEquipe;
import com.spring.kaddem.entities.Equipe;
import com.spring.kaddem.repositories.DetailEquipeRepository;
import com.spring.kaddem.services.DetailEquipeService;
import com.spring.kaddem.services.IDetailEquipeService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/detailsequipe")
@CrossOrigin("*")
public class DetailEquipeController {
    IDetailEquipeService iDetailEquipeService;
    DetailEquipeService detailEquipeService;
    DetailEquipeRepository detailEquipeRepository;
    @PostMapping("/add-DetailEquipe")
    @ResponseBody
    public DetailEquipe addDetailEquipe(@RequestBody DetailEquipe detailEquipe) {

        return iDetailEquipeService.addDetailEquipe(detailEquipe);
    }

    @GetMapping("/{idDetailEquipe}")
    public ResponseEntity<DetailEquipe> getCoursById(@PathVariable Long idDetailEquipe) {
        DetailEquipe equipe = detailEquipeService.getEquipeById(idDetailEquipe);
        if (equipe != null) {
            return new ResponseEntity<>(equipe, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/remove-detailEquipe/{idDetailEquipe}")
    public void removeDetailEquipe(@PathVariable("idDetailEquipe") Long idDetailEquipe) {
        iDetailEquipeService.removeDetailEquipe(idDetailEquipe);
    }

    @PutMapping("/updateDetailEquipe/{idDetailEquipe}")
    @ResponseBody
    public DetailEquipe updateDetailEquipe(@PathVariable("idDetailEquipe") Long idDetailEquipe, @RequestBody DetailEquipe detailEquipe) {
        return iDetailEquipeService.updateDetailEquipe(idDetailEquipe, detailEquipe);
    }
    @GetMapping("/all")
    public List<DetailEquipe> getAllEquipes() {
        return iDetailEquipeService.getAllEquipes();
    }
}
