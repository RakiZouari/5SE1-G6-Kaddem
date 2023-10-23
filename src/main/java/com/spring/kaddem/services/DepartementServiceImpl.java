package com.spring.kaddem.services;


import com.spring.kaddem.entities.Departement;
import com.spring.kaddem.repositories.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DepartementServiceImpl implements IDepartementService{

    @Autowired
    DepartementRepository departementRepository ;


    @Override
    public int Ajouter_Departement(Departement d_ep) {
        departementRepository.save(d_ep);
        return d_ep.getIdDepartement();

    }

    @Override
    public List<Departement> getAllD() {
        return departementRepository.findAll();
    }

    @Override
    public void update(Departement d_ep) {
        departementRepository.save(d_ep);
    }

    @Override
    public void delete(Departement d_ep) {
        departementRepository.delete(d_ep);
    }


}
