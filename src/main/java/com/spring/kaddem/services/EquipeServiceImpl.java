package com.spring.kaddem.services;

import com.spring.kaddem.entities.Contrat;
import com.spring.kaddem.entities.Equipe;
import com.spring.kaddem.entities.Etudiant;
import com.spring.kaddem.entities.Niveau;
import com.spring.kaddem.repositories.ContratRepository;
import com.spring.kaddem.repositories.EquipeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class EquipeServiceImpl implements IEquipeService{

    EquipeRepository equipeRepository;

    ContratRepository contratRepository;

    @Override
    public List<Equipe> retrieveAllEquipes() {
        return equipeRepository.findAll();
    }

    @Transactional
    public Equipe addEquipe(Equipe e) {

        equipeRepository.save(e);
        return e;
    }

    @Override
    public Equipe updateEquipe(Equipe e) {
        equipeRepository.save(e);
        return e;
    }

    @Override
    public Equipe retrieveEquipe(Integer idEquipe) {
        return   equipeRepository.findById(idEquipe).get();
    }

    public void evoluerEquipes() {
        log.info("debut methode evoluerEquipes");

        List<Equipe> equipes = (List<Equipe>) equipeRepository.findAll();
        log.debug("nombre équipes : " + equipes.size());

        for (Equipe equipe : equipes) {
            processEquipe(equipe);
        }

        log.info("fin methode evoluerEquipes");
    }

    private void processEquipe(Equipe equipe) {
        if (equipe.getEtudiants() == null || equipe.getEtudiants().isEmpty()) {
            return;
        }

        log.debug("vérification niveau équipe");

        if (equipe.getNiveau().equals(Niveau.JUNIOR) || equipe.getNiveau().equals(Niveau.SENIOR)) {
            for (Etudiant etudiant : equipe.getEtudiants()) {
                processEtudiant(etudiant, equipe);
            }
        }
    }

    private void processEtudiant(Etudiant etudiant, Equipe equipe) {
        log.debug("in for etudiants");
        List<Contrat> contrats = contratRepository.findByEtudiantIdEtudiant(etudiant.getIdEtudiant());

        for (Contrat contrat : contrats) {
            processContrat(contrat, equipe);
        }
    }

    private void processContrat(Contrat contrat, Equipe equipe) {
        log.debug("in contrat");

        long differenceInTime = contrat.getDateFinContrat().getTime() - contrat.getDateDebutContrat().getTime();
        long differenceInYears = (differenceInTime / (1000L * 60 * 60 * 24 * 365));
        log.debug("differenceInYears: " + differenceInYears);

        if ((contrat.getArchived() == null || !contrat.getArchived()) && (differenceInYears > 1)) {
            updateEquipeNiveau(equipe);
        }
    }

    private void updateEquipeNiveau(Equipe equipe) {
        if (equipe.getNiveau().equals(Niveau.JUNIOR)) {
            log.info("mise à jour du niveau de l'équipe " + equipe.getNomEquipe() +
                    " du niveau " + equipe.getNiveau() + " vers le niveau supérieur SENIOR");
            equipe.setNiveau(Niveau.SENIOR);
            equipeRepository.save(equipe);
        } else if (equipe.getNiveau().equals(Niveau.SENIOR)) {
            log.info("mise à jour du niveau de l'équipe " + equipe.getNomEquipe() +
                    " du niveau " + equipe.getNiveau() + " vers le niveau supérieur EXPERT");
            equipe.setNiveau(Niveau.EXPERT);
            equipeRepository.save(equipe);
        }
    }


}

