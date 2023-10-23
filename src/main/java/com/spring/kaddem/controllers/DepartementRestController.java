package com.spring.kaddem.controllers;

import com.spring.kaddem.entities.Departement;
import com.spring.kaddem.services.IDepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departement")

public class DepartementRestController {

    @Autowired
    IDepartementService departement_service ;
    @PostMapping("/addDepartment")
    @ResponseBody
    public void addDepartement(@RequestBody Departement d_ep) {
        departement_service.ajouter_departement(d_ep) ;
    }

    @GetMapping(value = "/getDepartment")
    @ResponseBody
    public List<Departement> getAllDE() {
        return  departement_service.getAllD();
    }

    @PutMapping("/updateDepatment")
    @ResponseBody
    public void updateEtudiant(@RequestBody Departement d_ep){
        departement_service.update(d_ep);
    }

    @DeleteMapping("/deleteEtudiant")
    @ResponseBody
    public void deleteStudent (@RequestBody Departement d_ep){
        departement_service.delete(d_ep);
    }

}
