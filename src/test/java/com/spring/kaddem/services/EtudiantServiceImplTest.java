package com.spring.kaddem.services;

import com.spring.kaddem.dto.EtudiantDto;
import com.spring.kaddem.entities.Etudiant;
import com.spring.kaddem.entities.Option;
import com.spring.kaddem.repositories.EtudiantRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Slf4j
class EtudiantServiceImplTest {

    @Autowired
    private EtudiantServiceImpl etudiantService;
    @MockBean
    private EtudiantRepository etudiantRepository;
    private Etudiant etudiant;
    private List<Etudiant> etudiants;

    @BeforeEach
    void setUp() {
        etudiant = new Etudiant();
        etudiant.setPrenomE("ismail");
        etudiant.setNomE("khlif");
        etudiant.setOp(Option.GAMIX);
    }
    @BeforeEach
    void setEtudiants(){
        etudiants = new ArrayList<>();
        Etudiant etudiant1 = new Etudiant();
        etudiant1.setPrenomE("John");
        etudiant1.setNomE("Doe");
        etudiant1.setOp(Option.GAMIX);
        etudiants.add(etudiant1);

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setPrenomE("Alice");
        etudiant2.setNomE("Smith");
        etudiant2.setOp(Option.SE);
        etudiants.add(etudiant2);

    }

    @Test
    void retrieveAllEtudiants() {
        setEtudiants();
        when(etudiantRepository.findAll()).thenReturn(etudiants);
        List<Etudiant> retrievedetudiants = etudiantService.retrieveAllEtudiants();
        assertEquals(etudiants, retrievedetudiants);

    }

    @Test
    void addEtudiant() {
        /*
        Etudiant newEtudiant = new Etudiant();

        when(etudiantRepository.save(newEtudiant)).thenReturn(newEtudiant);
        EtudiantDto addedEtudiantDto = etudiantService.addOrUpdateEtudiant(EtudiantDto.toDto(newEtudiant));
        verify(etudiantRepository).save(newEtudiant);
        assertEquals(newEtudiant, EtudiantDto.toEntity(addedEtudiantDto));
        */
    }
    @Test
    void UpdateEtudiant() {
        /*
        Etudiant newEtudiant = new Etudiant();
        newEtudiant.setIdEtudiant(1);
        newEtudiant.setNomE("BenFoulen");
        when(etudiantRepository.save(newEtudiant)).thenReturn(newEtudiant);
        EtudiantDto updatedEtudiantDto = etudiantService.addOrUpdateEtudiant(EtudiantDto.toDto(newEtudiant));
        verify(etudiantRepository).save(newEtudiant);
        assertEquals(newEtudiant,  EtudiantDto.toEntity(updatedEtudiantDto));
        */
    }

    @Test
    void retrieveEtudiant() {


        Etudiant etudiant1 = new Etudiant();
        etudiant1.setIdEtudiant(1);
        etudiant1.setPrenomE("Heni");
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant1));
        Etudiant retrievedDepartement = etudiantService.retrieveEtudiant(1);
        verify(etudiantRepository).findById(1);
        assertEquals(etudiant1, retrievedDepartement);

    }

    @Test
    void removeEtudiant() {
    }

    @Test
    void assignEtudiantToDepartement() {
    }

    @Test
    void findByDepartementIdDepartement() {
    }

    @Test
    void findByEquipesNiveau() {
    }

    @Test
    void retrieveEtudiantsByContratSpecialite() {
    }

    @Test
    void retrieveEtudiantsByContratSpecialiteSQL() {
    }

    @Test
    void addAndAssignEtudiantToEquipeAndContract() {
    }

    @Test
    void getEtudiantsByDepartement() {
    }
}
