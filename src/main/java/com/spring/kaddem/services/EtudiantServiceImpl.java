package com.spring.kaddem.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.kaddem.entities.*;
import com.spring.kaddem.dto.EtudiantDto;
import com.spring.kaddem.repositories.*;
import java.util.Optional;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class EtudiantServiceImpl implements IEtudiantService{

    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    DepartementRepository departementRepository;
    @Autowired
    ContratRepository contratRepository;
    @Autowired
    EquipeRepository equipeRepository;
    @Override
    public List<Etudiant> retrieveAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public EtudiantDto addOrUpdateEtudiant(EtudiantDto e) {

        etudiantRepository.save(EtudiantDto.toEntity(e));
        return e;
    }

    @Override
    public Etudiant retrieveEtudiant(Integer idEtudiant) {
        Optional<Etudiant> etudiantOptional = etudiantRepository.findById(idEtudiant);

    if (etudiantOptional.isPresent()) {
        return etudiantOptional.get();
    }else{
        throw new IllegalArgumentException("etudiant not found");
    }
    }

    @Override
    public void removeEtudiant(Integer idEtudiant) {
     etudiantRepository.deleteById(idEtudiant);
    }

    @Override
    public void assignEtudiantToDepartement(Integer etudiantId, Integer departementId) {
        Optional<Etudiant> etudiantOptional = etudiantRepository.findById(etudiantId);
        Optional<Departement> departementOptional = departementRepository.findById(departementId);
    
        if (etudiantOptional.isPresent() && departementOptional.isPresent()) {
            Etudiant etudiant = etudiantOptional.get();
            Departement departement = departementOptional.get();
            etudiant.setDepartement(departement);
            etudiantRepository.save(etudiant);
        }else{
        throw new IllegalArgumentException("etudiant or department not found");
    }
    }

    @Override
    public List<Etudiant> findByDepartementIdDepartement(Integer idDepartement) {
        return etudiantRepository.findByDepartementIdDepartement(idDepartement);
    }

    @Override
    public List<Etudiant> findByEquipesNiveau(Niveau niveau) {
        return etudiantRepository.findByEquipesNiveau(niveau);
    }

    @Override
    public List<Etudiant> retrieveEtudiantsByContratSpecialite(Specialite specialite) {
        return etudiantRepository.retrieveEtudiantsByContratSpecialite(specialite);
    }

    @Override
    public List<Etudiant> retrieveEtudiantsByContratSpecialiteSQL(String specialite) {
        return etudiantRepository.retrieveEtudiantsByContratSpecialiteSQL(specialite);
    }

    @Transactional
    public EtudiantDto addAndAssignEtudiantToEquipeAndContract(EtudiantDto e, Integer idContrat, Integer idEquipe) {
        Optional<Contrat> contratOptional = contratRepository.findById(idContrat);
        Optional<Equipe> equipeOptional = equipeRepository.findById(idEquipe);
    
        if (contratOptional.isPresent() && equipeOptional.isPresent()) {
            Contrat contrat = contratOptional.get();
            Equipe equipe = equipeOptional.get();
        
        Etudiant etudiant= etudiantRepository.save(EtudiantDto.toEntity(e));
        log.info("contrat: "+contrat.getSpecialite());
        log.info("equipe: "+equipe.getNomEquipe());
        log.info("etudiant: "+etudiant.getNomE()+" "+etudiant.getPrenomE()+" "+etudiant.getOp());
        List<Equipe> equipesMisesAjour = new ArrayList<>();
        contrat.setEtudiant(etudiant);
        equipesMisesAjour.add(equipe);
        log.info("taille apres ajout : "+equipesMisesAjour.size());
        etudiant.setEquipes(equipesMisesAjour);

        return e;
        }else{
        throw new IllegalArgumentException("contrat or equipe not found");
    }
    }

    @Override
    public List<Etudiant> getEtudiantsByDepartement(Integer idDepartement) {
        Optional<Departement> departementOptional = departementRepository.findById(idDepartement);
        if (departementOptional.isPresent()) {
        Departement departement = departementOptional.get();
    
        return departement.getEtudiants();
        }else{
        throw new IllegalArgumentException("departement not found ");
    }
    }




}
