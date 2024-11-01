package com.spring.kaddem.controllers;



import com.spring.kaddem.dto.ContratDTO;
import com.spring.kaddem.entities.Contrat;
import com.spring.kaddem.services.IContratService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;



@RestController
@RequestMapping("/contrat")
@CrossOrigin("*")
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

    //The most common ISO Date Format yyyy-MM-dd — for example, "2000-10-31".
    @GetMapping(value = "/getnbContratsValides/{startDate}/{endDate}")
    public Integer getnbContratsValides(@PathVariable(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                        @PathVariable(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {

        return contratService.nbContratsValides(startDate, endDate);
    }


    //public float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate)

    @GetMapping("/calculChiffreAffaireEntreDeuxDate/{startDate}/{endDate}")
    @ResponseBody
    public float calculChiffreAffaireEntreDeuxDates(@PathVariable(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                    @PathVariable(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {

        return contratService.getChiffreAffaireEntreDeuxDates(startDate, endDate);
    }

}
