package com.spring.kaddem.services;

import com.spring.kaddem.entities.Departement;
import com.spring.kaddem.entities.DepartementDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest

class DepartementServiceImplTest {
    @Autowired
    private DepartementServiceImpl departementService;
    @Test
    void ajouterdepartement() {
        DepartementDTO departmentDTO = new DepartementDTO("Département de test", "Test Department");
        int id = departementService.ajouterdepartement(departmentDTO);
        assertTrue(id > 0);
    }

    @Test
    void getAllD() {
        List<Departement> departments = departementService.getAllD();
        assertNotNull(departments);
        assertTrue(departments.size() > 0);

    }


    @Test
    void update() {

        DepartementDTO departmentDTO = new DepartementDTO("Département de test", "Test Department");
        int id = departementService.ajouterdepartement(departmentDTO);


        departmentDTO.setName("Updated Name");
        departmentDTO.setNomDepart("Updated Description");


        boolean updated = departementService.update(departmentDTO);

        assertTrue(updated);


        Departement updatedDepartment = departementService.getDepartmentById(id);

        assertNotNull(updatedDepartment);
        assertEquals("Updated Name", updatedDepartment.getName());
        assertEquals("Updated Description", updatedDepartment.getNomDepart()); 
    }



    @Test
    void delete() {
    }
}