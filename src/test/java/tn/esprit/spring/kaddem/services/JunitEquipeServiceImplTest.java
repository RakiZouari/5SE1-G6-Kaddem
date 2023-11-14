package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Equipe;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class JunitEquipeServiceImplTest {
    @Autowired
    private EquipeServiceImpl equipeService;


    @Test
    @Order(1)

    void retrieveAllEquipes() {
        List<Equipe> equipes = equipeService.retrieveAllEquipes();

        // Assert that the returned list is not null
        assertNotNull(equipes);

        // Assert that the returned list is not empty
        assertTrue(equipes.isEmpty());

        // You can add more specific assertions based on your requirements

        System.out.println("RetrieveAllEquipesTest : Ok");
    }

    @Test
    @Order(2)
    void addEquipe() {
        Equipe equipeToAdd = new Equipe();
        equipeToAdd.setNomEquipe("TestEquipe");
        // Set other properties as needed

        Equipe addedEquipe = equipeService.addEquipe(equipeToAdd);
        assertNotNull(addedEquipe);
        assertEquals("TestEquipe", addedEquipe.getNomEquipe());
        // Add more assertions based on your requirements

        System.out.println("AddEquipeTest : Ok");

    }

    @Test
    @Order(3)

    void retrieveEquipe() {
        // Assuming equipeService.retrieveEquipe method returns an Equipe
        Equipe equipe = equipeService.retrieveEquipe(1); // Replace 1 with the actual ID you want to retrieve

        assertNotNull(equipe);
        // Additional assertions...

        System.out.println("RetrieveEquipeTest : Ok");
    }
}