package com.spring.kaddem.services;


import com.spring.kaddem.entities.Departement;

import java.util.List;

public interface IDepartementService {
    public int AjouterDepartement(Departement D) ;
    public List<Departement> getAllD();

    void update (Departement D);
    void delete (Departement D);



}
