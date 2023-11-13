package com.spring.kaddem.services;

import com.spring.kaddem.dto.ContratDTO;
import com.spring.kaddem.entities.Contrat;
import com.spring.kaddem.repositories.ContratRepository;
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
class JunitContratServiceImplTest {

    @Autowired
    private ContratServiceImpl contratService;

    @Autowired
    private ContratRepository contratRepository;
    @Test
    @Order(1)
    void addUpdateContrat() {
        ContratDTO cont = new ContratDTO();
        cont.setMontantContrat(700);
        ContratDTO contrat = contratService.addUpdateContrat(cont);
        assertNotNull(contrat);
        assertEquals(700, contrat.getMontantContrat());
        System.out.println("Add : Ok");
    }

    @Test
    @Order(2)
    void retrieveAllContrats() {
        List<Contrat> contrats = contratService.retrieveAllContrats();

        // Assert that the returned list is not null
        assertNotNull(contrats);

        // Assert that the returned list is not empty
        assertFalse(contrats.isEmpty());

        // You can add more specific assertions based on your requirements

        System.out.println("RetrieveAllContratsTest : Ok");
    }

    @Test
    @Order(3)
    void retrieveContrat() {
        // Assuming contratService.retrieveContrat method returns a Contrat
        Contrat contrat = contratService.retrieveContrat(1); // Replace 1 with the actual ID you want to retrieve

        // Add assertions based on your requirements
        assertNotNull(contrat);
        // Additional assertions...

        System.out.println("RetrieveContratTest : Ok");
    }



}
