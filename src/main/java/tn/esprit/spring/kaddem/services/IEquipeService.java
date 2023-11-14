package tn.esprit.spring.kaddem.services;


import tn.esprit.spring.kaddem.entities.Equipe;

import java.util.List;

public interface IEquipeService {

    List<Equipe> retrieveAllEquipes();

    Equipe  addEquipe(Equipe  e);

    Equipe saveEquipe(Equipe e);
    Equipe retrieveEquipe (Integer idEquipe);

    public void evoluerEquipes();

}
