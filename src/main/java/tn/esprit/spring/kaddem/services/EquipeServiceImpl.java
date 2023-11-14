package tn.esprit.spring.kaddem.services;

import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    public Equipe saveEquipe(Equipe e) {
        return equipeRepository.save(e);
    }


    @Override
    public Equipe retrieveEquipe(Integer idEquipe) {
        Optional<Equipe> equipeOptional = equipeRepository.findById(idEquipe);

        if (equipeOptional.isPresent()) {
            return equipeOptional.get();
        } else {
            // Gérer le cas où l'objet recherché n'est pas présent dans la base de données
            // Vous pouvez lancer une exception, retourner une valeur par défaut, ou prendre une autre mesure en conséquence
            throw new EntityNotFoundException("Equipe with id " + idEquipe + " not found");
        }
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

    public void updateEquipeNiveau(Equipe equipe) {
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
