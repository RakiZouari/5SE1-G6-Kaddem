package com.spring.kaddem.services;

import com.spring.kaddem.entities.Departement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@SpringBootTest

class DepartementServiceImplTest {
    @Autowired
    private DepartementServiceImpl departementService;
    @Test
    void ajouterdepartement() {
        Departement departement = new Departement();
        departement.setName("Département de test");
        int id = departementService.ajouterdepartement(departement);
        assertNotNull(id);
        assertTrue(id > 0);
    }


    @Test
    void getAllD() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}