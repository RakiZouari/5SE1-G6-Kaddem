package com.spring.kaddem.services;


import com.spring.kaddem.entities.Departement;
import com.spring.kaddem.entities.DepartementDTO;
import com.spring.kaddem.repositories.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DepartementServiceImpl implements IDepartementService{

    @Autowired
    DepartementRepository departementRepository ;


    @Override
    public int ajouterdepartement(DepartementDTO departmentDTO) {
        Departement department = new Departement();
        department.setNomDepart(departmentDTO.getNomDepart());
        department.setName(departmentDTO.getName());

        departementRepository.save(department);
        return department.getIdDepartement();
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
