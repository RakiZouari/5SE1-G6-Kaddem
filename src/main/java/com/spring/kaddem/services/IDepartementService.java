package com.spring.kaddem.services;


import com.spring.kaddem.entities.Departement;
import com.spring.kaddem.entities.DepartementDTO;

import java.util.List;

public interface IDepartementService {
    public int ajouterdepartement(DepartementDTO departmentDTO);
    public List<Departement> getAllD();

    void update (Departement d);
    void delete (Departement d);



}
