package com.spring.kaddem.services;

import com.spring.kaddem.dto.EtudiantDto;
import com.spring.kaddem.entities.Etudiant;
import com.spring.kaddem.repositories.EtudiantRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JunitEtudiantServiceImplTest {

    @Autowired
    private EtudiantServiceImpl etudiantService;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Test
    @Order(1)
    void addOrUpdateEtudiant() {
        EtudiantDto etudiantDto = new EtudiantDto();
        etudiantDto.setPrenomE("John");
        etudiantDto.setNomE("Doe");

        EtudiantDto etudiant = etudiantService.addOrUpdateEtudiant(etudiantDto);
        assertNotNull(etudiant);
        assertEquals("John", etudiant.getPrenomE());
        assertEquals("Doe", etudiant.getNomE());

        System.out.println("AddOrUpdateEtudiantTest : Ok");
    }

    @Test
    @Order(2)
    void retrieveAllEtudiants() {
        List<Etudiant> etudiants = etudiantService.retrieveAllEtudiants();

        assertNotNull(etudiants);

        assertFalse(etudiants.isEmpty());


        System.out.println("RetrieveAllEtudiantsTest : Ok");
    }

    @Test
    @Order(3)
    void retrieveEtudiant() {
        Etudiant etudiant = etudiantService.retrieveEtudiant(1); 

        assertNotNull(etudiant);

        System.out.println("RetrieveEtudiantTest : Ok");
    }


}
