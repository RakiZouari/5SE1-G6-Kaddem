package com.spring.kaddem.services;


import com.spring.kaddem.dto.ContratDTO;
import com.spring.kaddem.entities.Contrat;

import java.util.Date;
import java.util.List;

public interface IContratService {

    List<Contrat> retrieveAllContrats();

    Contrat updateContrat(Integer idContrat, ContratDTO contratDTO);
    Contrat retrieveContrat(Integer idContrat);
    void removeContrat(Integer idContrat);
    Contrat addContrat(ContratDTO contratDTO);

    ContratDTO addAndAffectContratToEtudiant(ContratDTO contratDTO, String nomE, String prenomE);

    public 	Integer nbContratsValides(Date startDate, Date endDate);


    public float getChiffreAffaireEntreDeuxDates(Date startDate, Date endDate);

    public void retrieveAndUpdateStatusContrat();
}



