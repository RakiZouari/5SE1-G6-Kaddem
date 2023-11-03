package com.spring.kaddem.services;

import com.spring.kaddem.dto.EtudiantDto;
import com.spring.kaddem.entities.*;
import com.spring.kaddem.entities.Option;
import com.spring.kaddem.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows; 
import static org.junit.jupiter.api.Assertions.assertTrue;   
import static org.junit.jupiter.api.Assertions.assertEquals; 
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;

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
        Mockito.reset(etudiantRepository, departementRepository);
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
    MockitoAnnotations.initMocks(this);

        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);
        Departement departement = new Departement();
        departement.setIdDepartement(2);

        when(etudiantRepository.findById(etudiant.getIdEtudiant())).thenReturn(Optional.of(etudiant));
        when(departementRepository.findById(departement.getIdDepartement())).thenReturn(Optional.of(departement));

        etudiantService.assignEtudiantToDepartement(etudiant.getIdEtudiant(), departement.getIdDepartement());

        verify(etudiantRepository).findById(etudiant.getIdEtudiant());
        verify(departementRepository).findById(departement.getIdDepartement());

        assertEquals(departement, etudiant.getDepartement());
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
    when(etudiantRepository.save(any(Etudiant.class))).thenAnswer(invocation -> {
        Etudiant savedEtudiant = invocation.getArgument(0);
        savedEtudiant.setIdEtudiant(1); 
        return savedEtudiant;
    });

    EtudiantDto result = etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiantDto, idContrat, idEquipe);
    verify(etudiantRepository).save(any(Etudiant.class));

    assertEquals(1, result.getIdEtudiant());
}



@Test
void getEtudiantsByDepartement() {
    MockitoAnnotations.initMocks(this);

        Departement departement = new Departement();
        departement.setIdDepartement(1);

        Etudiant etudiant1 = new Etudiant();
        etudiant1.setIdEtudiant(2);
        Etudiant etudiant2 = new Etudiant();
        etudiant2.setIdEtudiant(3);

        List<Etudiant> etudiants = new ArrayList<>();
        etudiants.add(etudiant1);
        etudiants.add(etudiant2);
        departement.setEtudiants(etudiants);

        when(departementRepository.findById(departement.getIdDepartement())).thenReturn(Optional.of(departement));

        List<Etudiant> result = etudiantService.getEtudiantsByDepartement(departement.getIdDepartement());

        verify(departementRepository).findById(departement.getIdDepartement());

        assertEquals(2, result.size());
        assertEquals(2, result.get(0).getIdEtudiant());
        assertEquals(3, result.get(1).getIdEtudiant());
}
    @Test
void testAssignEtudiantToDepartement_WhenEtudiantAndDepartementExist() {
    Etudiant etudiant = new Etudiant();
    etudiant.setIdEtudiant(1);
    Departement departement = new Departement();
    departement.setIdDepartement(2);

    when(etudiantRepository.findById(etudiant.getIdEtudiant())).thenReturn(Optional.of(etudiant));
    when(departementRepository.findById(departement.getIdDepartement())).thenReturn(Optional.of(departement));

    etudiantService.assignEtudiantToDepartement(etudiant.getIdEtudiant(), departement.getIdDepartement());

    verify(etudiantRepository).findById(etudiant.getIdEtudiant());
    verify(departementRepository).findById(departement.getIdDepartement());

    assertEquals(departement, etudiant.getDepartement());
}

    @Test
void testRetrieveEtudiant_WhenEtudiantExists() {
    Etudiant etudiant = new Etudiant();
    etudiant.setIdEtudiant(1);
    
    when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));

    Etudiant retrievedEtudiant = etudiantService.retrieveEtudiant(1);

    verify(etudiantRepository).findById(1);
    assertEquals(etudiant, retrievedEtudiant);
}

@Test
void testRetrieveEtudiant_WhenEtudiantDoesNotExist() {
    when(etudiantRepository.findById(1)).thenReturn(Optional.empty());

    assertThrows(IllegalArgumentException.class, () -> {
        etudiantService.retrieveEtudiant(1);
    });

    verify(etudiantRepository).findById(1);
}

@Test
void testRetrieveEtudiantsByContratSpecialite_WithExistingSpecialite() {
    Specialite specialite = Specialite.CLOUD;
    
    List<Etudiant> expectedEtudiants = new ArrayList<>();
    Etudiant etudiant1 = new Etudiant();
    etudiant1.setIdEtudiant(1);
    expectedEtudiants.add(etudiant1);
    
    when(etudiantRepository.retrieveEtudiantsByContratSpecialite(specialite)).thenReturn(expectedEtudiants);

    List<Etudiant> retrievedEtudiants = etudiantService.retrieveEtudiantsByContratSpecialite(specialite);

    verify(etudiantRepository).retrieveEtudiantsByContratSpecialite(specialite);
    assertEquals(expectedEtudiants, retrievedEtudiants);
}

@Test
void testRetrieveEtudiantsByContratSpecialite_WithNonExistingSpecialite() {
    Specialite specialite = Specialite.CLOUD;
    
    when(etudiantRepository.retrieveEtudiantsByContratSpecialite(specialite)).thenReturn(new ArrayList<>());

    List<Etudiant> retrievedEtudiants = etudiantService.retrieveEtudiantsByContratSpecialite(specialite);

    verify(etudiantRepository).retrieveEtudiantsByContratSpecialite(specialite);
    assertTrue(retrievedEtudiants.isEmpty());
}
    @Test
void testGetEtudiantsByDepartement_Success() {
    int idDepartement = 1;
    Departement departement = new Departement();
    
    when(departementRepository.findById(idDepartement)).thenReturn(Optional.of(departement));

    List<Etudiant> result = etudiantService.getEtudiantsByDepartement(idDepartement);
    
    verify(departementRepository).findById(idDepartement);

}

@Test
void testGetEtudiantsByDepartement_DepartementNotFound() {
    int idDepartement = 1;
    
    when(departementRepository.findById(idDepartement)).thenReturn(Optional.empty());
    
    assertThrows(IllegalArgumentException.class, () -> {
        etudiantService.getEtudiantsByDepartement(idDepartement);
    });
}
    @Test
void testAssignEtudiantToDepartement_BothPresent() {
    Etudiant etudiant = new Etudiant();
    etudiant.setIdEtudiant(1);
    Departement departement = new Departement();
    departement.setIdDepartement(2);

    when(etudiantRepository.findById(etudiant.getIdEtudiant())).thenReturn(Optional.of(etudiant));
    when(departementRepository.findById(departement.getIdDepartement())).thenReturn(Optional.of(departement));

    etudiantService.assignEtudiantToDepartement(etudiant.getIdEtudiant(), departement.getIdDepartement());

    verify(etudiantRepository).findById(etudiant.getIdEtudiant());
    verify(departementRepository).findById(departement.getIdDepartement());

    assertEquals(departement, etudiant.getDepartement());
}

@Test
void testAssignEtudiantToDepartement_EtudiantNotFound() {
    int etudiantId = 1;
    Departement departement = new Departement();
    departement.setIdDepartement(2);

    when(etudiantRepository.findById(etudiantId)).thenReturn(Optional.empty());
    when(departementRepository.findById(departement.getIdDepartement())).thenReturn(Optional.of(departement));

    assertThrows(IllegalArgumentException.class, () -> {
        etudiantService.assignEtudiantToDepartement(etudiantId, departement.getIdDepartement());
    });

    verify(etudiantRepository).findById(etudiantId);
}

@Test
void testAssignEtudiantToDepartement_DepartementNotFound() {
    Etudiant etudiant = new Etudiant();
    etudiant.setIdEtudiant(1);
    int departementId = 2;

    when(etudiantRepository.findById(etudiant.getIdEtudiant())).thenReturn(Optional.of(etudiant));
    when(departementRepository.findById(departementId)).thenReturn(Optional.empty());

    assertThrows(IllegalArgumentException.class, () -> {
        etudiantService.assignEtudiantToDepartement(etudiant.getIdEtudiant(), departementId);
    });

    verify(etudiantRepository).findById(etudiant.getIdEtudiant());
    verify(departementRepository).findById(departementId);
}

@Test
void testAssignEtudiantToDepartement_BothNotFound() {
    int etudiantId = 1;
    int departementId = 2;

    when(etudiantRepository.findById(etudiantId)).thenReturn(Optional.empty());
    when(departementRepository.findById(departementId)).thenReturn(Optional.empty());

    assertThrows(IllegalArgumentException.class, () -> {
        etudiantService.assignEtudiantToDepartement(etudiantId, departementId);
    });

    verify(etudiantRepository).findById(etudiantId);
}

@Test
void testAddAndAssignEtudiantToEquipeAndContract_Success() {
    int idContrat = 1;
    int idEquipe = 2;
    EtudiantDto etudiantDto = new EtudiantDto();

    when(contratRepository.findById(idContrat)).thenReturn(Optional.of(new Contrat()));
    when(equipeRepository.findById(idEquipe)).thenReturn(Optional.of(new Equipe()));
    when(etudiantRepository.save(any(Etudiant.class))).thenAnswer(invocation -> {
        Etudiant savedEtudiant = invocation.getArgument(0);
        savedEtudiant.setIdEtudiant(1);
        return savedEtudiant;
    });

    EtudiantDto result = etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiantDto, idContrat, idEquipe);

    verify(etudiantRepository).save(any(Etudiant.class));

    assertEquals(1, result.getIdEtudiant());
}

@Test
void testAddAndAssignEtudiantToEquipeAndContract_ContratNotFound() {
    int idContrat = 1;
    int idEquipe = 2;
    EtudiantDto etudiantDto = new EtudiantDto();

    when(contratRepository.findById(idContrat)).thenReturn(Optional.empty());
    when(equipeRepository.findById(idEquipe)).thenReturn(Optional.of(new Equipe()));

    assertThrows(IllegalArgumentException.class, () -> {
        etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiantDto, idContrat, idEquipe);
    });

    verify(etudiantRepository, never()).save(any(Etudiant.class));
}

@Test
void testAddAndAssignEtudiantToEquipeAndContract_EquipeNotFound() {
    int idContrat = 1;
    int idEquipe = 2;
    EtudiantDto etudiantDto = new EtudiantDto();

    when(contratRepository.findById(idContrat)).thenReturn(Optional.of(new Contrat()));
    when(equipeRepository.findById(idEquipe)).thenReturn(Optional.empty());

    assertThrows(IllegalArgumentException.class, () -> {
        etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiantDto, idContrat, idEquipe);
    });

    verify(etudiantRepository, never()).save(any(Etudiant.class));
}

@Test
void testAddAndAssignEtudiantToEquipeAndContract_BothNotFound() {
    int idContrat = 1;
    int idEquipe = 2;
    EtudiantDto etudiantDto = new EtudiantDto();

    when(contratRepository.findById(idContrat)).thenReturn(Optional.empty());
    when(equipeRepository.findById(idEquipe)).thenReturn(Optional.empty());

    assertThrows(IllegalArgumentException.class, () -> {
        etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiantDto, idContrat, idEquipe);
    });

    verify(etudiantRepository, never()).save(any(Etudiant.class));
}
    @Test
    public void testToString() {
        // Create an Etudiant instance with sample data
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);
        etudiant.setPrenomE("John");
        etudiant.setNomE("Doe");

        Equipe equipe1 = new Equipe();
        equipe1.setIdEquipe(1);
        Contrat contrat1 = new Contrat();
        contrat1.setIdContrat(1);
        etudiant.getEquipes().add(equipe1);
        etudiant.getContrats().add(contrat1);
        String result = etudiant.toString();
        String expected = "Etudiant{idEtudiant=1, prenomE='John', nomE='Doe', equipes=[Equipe{id=1, ...}], contrats=[Contrat{id=1, ...}], ...}";
        assertEquals(expected, result);
    }
}
