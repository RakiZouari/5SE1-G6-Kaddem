package com.spring.kaddem.services;


import com.spring.kaddem.entities.Departement;

import java.util.List;

public interface IDepartementService {
    public int ajouter_departement(Departement d) ;
    public List<Departement> getAllD();

    void update (Departement d);
    void delete (Departement d);



}
