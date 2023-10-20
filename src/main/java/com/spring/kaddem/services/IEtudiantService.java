package com.spring.kaddem.services;

import com.spring.kaddem.entities.*;

import java.util.List;

public interface IEtudiantService {

    List<Etudiant> retrieveAllEtudiants();
    Etudiant addOrUpdateEtudiant(Etudiant e);
    Etudiant retrieveEtudiant(Integer idEtudiant);
    void removeEtudiant(Integer idEtudiant);

    public void assignEtudiantToDepartement(Integer etudiantId, Integer departementId) ;

    List<Etudiant> findByDepartementIdDepartement(Integer idDepartement);

    List<Etudiant> findByEquipesNiveau(Niveau niveau);

    
    List<Etudiant> retrieveEtudiantsByContratSpecialite(Specialite specialite);

    List<Etudiant> retrieveEtudiantsByContratSpecialiteSQL(String specialite);

    Etudiant addAndAssignEtudiantToEquipeAndContract (Etudiant e, Integer idContrat , Integer idEquipe);

    List<Etudiant> getEtudiantsByDepartement (Integer idDepartement);



}
