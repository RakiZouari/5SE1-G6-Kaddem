package com.spring.kaddem.services;

import com.spring.kaddem.entities.Contrat;
import com.spring.kaddem.entities.DetailEquipe;
import com.spring.kaddem.repositories.DetailEquipeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class DetailEquipeService implements IDetailEquipeService{
    @Autowired
    DetailEquipeRepository detailEquipeRepository;
    public DetailEquipe getEquipeById(Long idDetailEquipe) {
        Optional<DetailEquipe> equipeOptional = detailEquipeRepository.findById(idDetailEquipe);
        return equipeOptional.orElse(null);
    }
    @Override
    public DetailEquipe addDetailEquipe(DetailEquipe detailEquipe) {
        detailEquipeRepository.save(detailEquipe);
        return detailEquipe;
    }

    @Override
    public void removeDetailEquipe(Long idDetailEquipe)
    {
        detailEquipeRepository.deleteById(idDetailEquipe);
    }

    @Override
    @Transactional
    public DetailEquipe updateDetailEquipe(Long idDetailEquipe,DetailEquipe detailEquipe)
    {
        DetailEquipe detailEquipe1 = detailEquipeRepository.findById(idDetailEquipe).orElse(null);
        if (detailEquipe1 == null) {
            return null;
        }
        detailEquipe1.setSalle(detailEquipe.getSalle());
        detailEquipe1.setThematique(detailEquipe.getThematique());



        return detailEquipeRepository.save(detailEquipe1);
    }
}
