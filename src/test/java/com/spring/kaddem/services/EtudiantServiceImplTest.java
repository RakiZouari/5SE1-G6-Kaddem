package com.spring.kaddem.services;

import com.spring.kaddem.dto.EtudiantDto;
import com.spring.kaddem.entities.*;
import com.spring.kaddem.entities.Option;
import com.spring.kaddem.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
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
    @MockBean
    private ContratRepository contratRepository;
    @MockBean
    private EquipeRepository equipeRepository; 
    @MockBean
    private DepartementRepository departementRepository;
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
        EtudiantDto newEtudiantDto = new EtudiantDto();
        newEtudiantDto.setPrenomE("John");
        newEtudiantDto.setNomE("Doe");
        newEtudiantDto.setOp(Option.GAMIX);
    
        when(etudiantRepository.save(any(Etudiant.class))).thenAnswer(invocation -> {
            Etudiant savedEtudiant = invocation.getArgument(0);
            savedEtudiant.setIdEtudiant(1); // Set the ID as it would be generated during save
            return savedEtudiant;
        });
    
        EtudiantDto addedEtudiantDto = etudiantService.addOrUpdateEtudiant(newEtudiantDto);
    
        verify(etudiantRepository).save(any(Etudiant.class));
    
        assertEquals("John", addedEtudiantDto.getPrenomE());
        assertEquals("Doe", addedEtudiantDto.getNomE());
        assertEquals(Option.GAMIX, addedEtudiantDto.getOp());
    }
    
    @Test
    void UpdateEtudiant() {
        EtudiantDto updatedEtudiantDto = new EtudiantDto();
        updatedEtudiantDto.setIdEtudiant(1);
        updatedEtudiantDto.setPrenomE("UpdatedPrenom");
        updatedEtudiantDto.setNomE("BenFoulen");
        updatedEtudiantDto.setOp(Option.GAMIX);
    
        when(etudiantRepository.save(any(Etudiant.class))).thenAnswer(invocation -> invocation.getArgument(0));
    
        EtudiantDto updatedEtudiantDtoResult = etudiantService.addOrUpdateEtudiant(updatedEtudiantDto);
    
        verify(etudiantRepository).save(any(Etudiant.class));
    
        assertEquals(1, updatedEtudiantDtoResult.getIdEtudiant());
        assertEquals("UpdatedPrenom", updatedEtudiantDtoResult.getPrenomE());
        assertEquals("BenFoulen", updatedEtudiantDtoResult.getNomE());
        assertEquals(Option.GAMIX, updatedEtudiantDtoResult.getOp());
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
    int etudiantIdToRemove = 1;
    etudiantService.removeEtudiant(etudiantIdToRemove);
    verify(etudiantRepository).deleteById(etudiantIdToRemove);
}

@Test
void assignEtudiantToDepartement() {
    int etudiantId = 1;
    int departementId = 2;
    etudiantService.assignEtudiantToDepartement(etudiantId, departementId);
    verify(etudiantRepository).save(any(Etudiant.class));
}

@Test
void findByDepartementIdDepartement() {
    int idDepartement = 1;
    List<Etudiant> expectedEtudiants = new ArrayList<>();
    when(etudiantRepository.findByDepartementIdDepartement(idDepartement)).thenReturn(expectedEtudiants);
    List<Etudiant> retrievedEtudiants = etudiantService.findByDepartementIdDepartement(idDepartement);
    assertEquals(expectedEtudiants, retrievedEtudiants);
}

@Test
void findByEquipesNiveau() {
    Niveau niveau = Niveau.SENIOR; 

    List<Etudiant> expectedEtudiants = new ArrayList<>();
    when(etudiantRepository.findByEquipesNiveau(niveau)).thenReturn(expectedEtudiants);
    List<Etudiant> retrievedEtudiants = etudiantService.findByEquipesNiveau(niveau);
    assertEquals(expectedEtudiants, retrievedEtudiants);
}

    @Test
void retrieveEtudiantsByContratSpecialite() {
    Specialite specialite = Specialite.CLOUD; 
    List<Etudiant> expectedEtudiants = new ArrayList<>();
    when(etudiantRepository.retrieveEtudiantsByContratSpecialite(specialite)).thenReturn(expectedEtudiants);
    List<Etudiant> retrievedEtudiants = etudiantService.retrieveEtudiantsByContratSpecialite(specialite);
    assertEquals(expectedEtudiants, retrievedEtudiants);
}

@Test
void retrieveEtudiantsByContratSpecialiteSQL() {
    String specialite = "CLOUD"; 

    List<Etudiant> expectedEtudiants = new ArrayList<>();
    when(etudiantRepository.retrieveEtudiantsByContratSpecialiteSQL(specialite)).thenReturn(expectedEtudiants);
    List<Etudiant> retrievedEtudiants = etudiantService.retrieveEtudiantsByContratSpecialiteSQL(specialite);
    assertEquals(expectedEtudiants, retrievedEtudiants);
}

@Test
void addAndAssignEtudiantToEquipeAndContract() {
    
    int idContrat = 1; 
    int idEquipe = 2; 
    EtudiantDto etudiantDto = new EtudiantDto();
    when(contratRepository.findById(idContrat)).thenReturn(Optional.of(new Contrat()));
    when(equipeRepository.findById(idEquipe)).thenReturn(Optional.of(new Equipe()));
    EtudiantDto result = etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiantDto, idContrat, idEquipe);
    verify(etudiantRepository).save(any(Etudiant.class));
    assertEquals(etudiantDto.getIdEtudiant(), result.getIdEtudiant());
}

@Test
void getEtudiantsByDepartement() {
    
    int idDepartement = 1; 
    List<Etudiant> expectedEtudiants = new ArrayList<>();
    when(departementRepository.findById(idDepartement)).thenReturn(Optional.of(new Departement()));
    when(etudiantRepository.findByDepartementIdDepartement(idDepartement)).thenReturn(expectedEtudiants);
    List<Etudiant> retrievedEtudiants = etudiantService.getEtudiantsByDepartement(idDepartement);
    assertEquals(expectedEtudiants, retrievedEtudiants);
}

}
