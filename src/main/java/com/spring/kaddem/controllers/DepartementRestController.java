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
    IDepartementService DepartementService ;
    @PostMapping("/addDepartment")
    @ResponseBody
    public void addDepartement(@RequestBody Departement d) {
        DepartementService.AjouterDepartement(d) ;
    }

    @GetMapping(value = "/getDepartment")
    @ResponseBody
    public List<Departement> getAllDE() {
        return  DepartementService.getAllD();
    }

    @PutMapping("/updateDepatment")
    @ResponseBody
    public void updateEtudiant(@RequestBody Departement e){
        DepartementService.update(e);
    }

    @DeleteMapping("/deleteEtudiant")
    @ResponseBody
    public void deleteStudent (@RequestBody Departement e){
        DepartementService.delete(e);
    }

}
