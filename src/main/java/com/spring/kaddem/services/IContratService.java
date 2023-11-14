package com.spring.kaddem.services;


import com.spring.kaddem.dto.ContratDTO;
import com.spring.kaddem.entities.Contrat;
import java.util.List;

public interface IContratService {

    List<Contrat> retrieveAllContrats();

    Contrat retrieveContrat(Integer idContrat);
    void removeContrat(Integer idContrat);
    ContratDTO addUpdateContrat(ContratDTO c);


}



