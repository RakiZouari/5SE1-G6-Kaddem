package com.spring.kaddem.services;


import com.spring.kaddem.entities.Universite;

import java.util.List;

public interface IUniversiteService {

    List<Universite> retrieveAllUniversites();

    Universite addUniversite (Universite u);

    Universite updateUniversite (Universite u);

    Universite retrieveUniversite (Integer idUniversite);

 void assignUniversiteToDepartement(Integer universiteId, Integer departementId);

}
