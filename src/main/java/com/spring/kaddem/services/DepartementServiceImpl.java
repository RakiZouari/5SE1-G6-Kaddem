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
    public int ajouterdepartement(Departement d) {
        departementRepository.save(d);
        return d.getIdDepartement();

    }

    @Override
    public List<Departement> getAllD() {
        return departementRepository.findAll();
    }

    @Override
    public void update(Departement d) {
        departementRepository.save(d);
    }

    @Override
    public void delete(Departement d) {
        departementRepository.delete(d);
    }


}
