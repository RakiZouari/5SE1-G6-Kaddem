package com.spring.kaddem.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contrat")
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
    public Contrat addContrat(@RequestBody Contrat c) {

        return contratService.addContrat(c);
    }

    // http://localhost:8089/Kaddem/contrat/update-contrat
    @PutMapping("/update-contrat")
    @ResponseBody
    public Contrat updateEtudiant(@RequestBody Contrat cont) {
        return contratService.updateContrat(cont);
    }

    // http://localhost:8089/Kaddem/contrat/addAndAffectContratToEtudiant/salah/ahmed
    @PostMapping("/addAndAffectContratToEtudiant/{nomE}/{prenomE}")
    @ResponseBody
    public Contrat addAndAffectContratToEtudiant(@RequestBody Contrat contrat,@PathVariable("nomE") String nomE,@PathVariable("prenomE") String prenomE) {
        return contratService.addAndAffectContratToEtudiant(contrat,nomE,prenomE);
    }

    //The most common ISO Date Format yyyy-MM-dd — for example, "2000-10-31".
    @GetMapping(value = "/getnbContratsValides/{startDate}/{endDate}")
    public Integer getnbContratsValides(@PathVariable(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                        @PathVariable(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {

        return contratService.nbContratsValides(startDate, endDate);
    }

    //Only no-arg methods may be annotated with @Scheduled
    @Scheduled(cron="0 0 13 * * *")//(cron="0 0 13 * * ?")(fixedRate =21600)
    //  @Scheduled(cron="45 * * * * *")//(cron="0 0 13 * * ?")(fixedRate =21600)
    @PutMapping(value = "/majStatusContrat")
    public void majStatusContrat (){
        contratService.retrieveAndUpdateStatusContrat();
    }

    //public float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate)

    @GetMapping("/calculChiffreAffaireEntreDeuxDate/{startDate}/{endDate}")
    @ResponseBody
    public float calculChiffreAffaireEntreDeuxDates(@PathVariable(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                    @PathVariable(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {

        return contratService.getChiffreAffaireEntreDeuxDates(startDate, endDate);
    }

}
