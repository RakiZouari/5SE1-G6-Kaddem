package com.spring.kaddem.services;

import com.spring.kaddem.entities.Contrat;
import com.spring.kaddem.entities.DetailEquipe;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface IDetailEquipeService {
  //  public DetailEquipe getEquipeById(Long idDetailEquipe);
    public DetailEquipe addDetailEquipe(DetailEquipe detailEquipe);
  public void removeDetailEquipe(Long idDetailEquipe);
  public DetailEquipe updateDetailEquipe(Long idDetailEquipe,DetailEquipe detailEquipe);
}
