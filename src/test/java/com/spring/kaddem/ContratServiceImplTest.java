package com.spring.kaddem;

import com.spring.kaddem.entities.Contrat;
import com.spring.kaddem.services.IContratService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContratServiceImplTest {

    @Autowired
    IContratService contratService;

    @Test
    @Order(1)
    void testRetrieveAllUsers() {
        List<Contrat> listContrat = contratService.retrieveAllContrats();
        Assertions.assertEquals(0, listContrat.size());
    }
}
