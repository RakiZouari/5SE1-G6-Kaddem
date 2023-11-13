package com.spring.kaddem.controllers;

import com.spring.kaddem.dto.ContratDTO;
import com.spring.kaddem.entities.Contrat;
import com.spring.kaddem.services.IContratService;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/contrat")
@CrossOrigin(origins = "http://localhost:80")
public class ContratRestController {

    IContratService contratService;

    // http://localhost:8089/Kaddem/contrat/retrieve-all-contrats
    @GetMapping("/retrieve-all-contrats")
    @ResponseBody
    public List<Contrat> getContrats() {
        return contratService.retrieveAllContrats();
    }

    // http://localhost:8089/Kaddem/contrat/retrieve-contrat/8
    @GetMapping("/retrieve-contrat/{contrat-id}")
    @ResponseBody
    public Contrat retrieveContrat(@PathVariable("contrat-id") Integer contratId) {
        return contratService.retrieveContrat(contratId);
    }

    // http://localhost:8089/Kaddem/contrat/add-contrat
    @PostMapping("/add-contrat")
    @ResponseBody
    public ContratDTO addContrat(@RequestBody ContratDTO contratDTO) {

        return contratService.addUpdateContrat(contratDTO);
    }

    // http://localhost:8089/Kaddem/contrat/update-contrat
    @PutMapping("/update-contrat")
    @ResponseBody
    public ContratDTO updateContrat(@RequestBody ContratDTO contratDTO) {
        return contratService.addUpdateContrat(contratDTO);
    }

    // http://localhost:8089/Kaddem/contrat/addAndAffectContratToEtudiant/salah/ahmed
    @PostMapping("/addAndAffectContratToEtudiant/{nomE}/{prenomE}")
    @ResponseBody
    public ContratDTO addAndAffectContratToEtudiant(@RequestBody ContratDTO contratDTO,
                                                    @PathVariable("nomE") String nomE,
                                                    @PathVariable("prenomE") String prenomE) {

        return contratService.addAndAffectContratToEtudiant(contratDTO, nomE, prenomE);
    }


}
