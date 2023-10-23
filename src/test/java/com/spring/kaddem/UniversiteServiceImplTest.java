package com.spring.kaddem;

import com.spring.kaddem.entities.Universite;
import com.spring.kaddem.services.IUniversiteService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UniversiteServiceImplTest {

    @Autowired
    IUniversiteService universiteService;

    @Test
    @Order(1)
     void testRetrieveAllUniversites() {
        List<Universite> listUniversite = universiteService.retrieveAllUniversites();
        Assertions.assertEquals(0, listUniversite.size());
    }

}
