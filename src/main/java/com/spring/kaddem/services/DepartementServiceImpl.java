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
    public void update(DepartementDTO d) {
        Departement department = departementRepository.findById(d.getIDepartementID()).orElse(null);
        if (department != null) {
            department.setNomDepart(d.getNomDepart());
            department.setName(d.getName());
            departementRepository.save(department);
        }
    }
    @Override
    public void delete(DepartementDTO d){
            Departement department = departementRepository.findById(d.getIDepartementID()).orElse(null);
            if (department != null) {
                departementRepository.delete(department);
            }
        }


}
