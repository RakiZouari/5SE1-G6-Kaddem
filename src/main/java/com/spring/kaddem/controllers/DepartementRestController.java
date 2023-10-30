package com.spring.kaddem.controllers;

import com.spring.kaddem.entities.Departement;
import com.spring.kaddem.entities.DepartementDTO;
import com.spring.kaddem.services.IDepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departement")

public class DepartementRestController {

    @Autowired
    IDepartementService departementservice ;
    @PostMapping("/addDepartment")
    @ResponseBody
    public void addDepartement(@RequestBody DepartementDTO d) {
        departementservice.ajouterdepartement(d) ;
    }

    @GetMapping(value = "/getDepartment")
    @ResponseBody
    public List<Departement> getAllDE() {
        return  departementservice.getAllD();
    }

    @PutMapping("/updateDepatment")
    @ResponseBody
    public void updateEtudiant(@RequestBody Departement d){
        departementservice.update(d);
    }

    @DeleteMapping("/deleteEtudiant")
    @ResponseBody
    public void deleteStudent (@RequestBody Departement d){
        departementservice.delete(d);
    }

}
