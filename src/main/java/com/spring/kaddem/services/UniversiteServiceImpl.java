package com.spring.kaddem.services;

import com.spring.kaddem.entities.Departement;
import com.spring.kaddem.entities.Universite;
import com.spring.kaddem.repositories.DepartementRepository;
import com.spring.kaddem.repositories.UniversiteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Slf4j
public class UniversiteServiceImpl implements  IUniversiteService{
    @Autowired
    UniversiteRepository universiteRepository;
    @Autowired
    DepartementRepository departementRepository;
    @Override
    public List<Universite> retrieveAllUniversites() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite addUniversite(Universite universite) {


        return universiteRepository.save(universite);
    }

    @Override
    public Universite updateUniversite(Universite universite) {

        return universiteRepository.save(universite);
    }

    @Override
    public Universite retrieveUniversite(Integer idUniversite) {
        return universiteRepository.findById(idUniversite).orElse(null);
    }

    @Transactional
    public void assignUniversiteToDepartement(Integer universiteId, Integer departementId) {
        Universite universite =universiteRepository.findById(universiteId).get();
        Departement departement=departementRepository.findById(departementId).get();
        universite.getDepartements().add(departement);
        log.info("departements number "+universite.getDepartements().size());
    }
}
