package com.spring.kaddem.services;


import com.spring.kaddem.entities.Departement;

import java.util.List;

public interface IDepartementService {
    public int Ajouter_Departement(Departement d_ep) ;
    public List<Departement> getAllD();

    void update (Departement d_ep);
    void delete (Departement d_ep);



}
