package com.spring.kaddem.services;

import com.spring.kaddem.entities.Departement;
import com.spring.kaddem.entities.DepartementDTO;
import com.spring.kaddem.repositories.DepartementRepository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
class DepartementServiceImplTest {

    @Autowired
    private DepartementServiceImpl departementService;

    @MockBean
    private DepartementRepository departementRepository;
    private Departement departement;

    private List<Departement> departements;

    @BeforeEach
    void setUp() {
        departement = new Departement();
        departement.setNomDepart("Test Department");
        departements = new ArrayList<>(); // Initialize departements list
    }

    @Test
    void ajouterdepartement() {
        DepartementDTO departmentDTO = new DepartementDTO("Département de test", "Test Department");

        when(departementRepository.save(any(Departement.class))).thenAnswer(invocation -> {
            Departement savedDepartement = invocation.getArgument(0);
            savedDepartement.setIdDepartement(1); // Set the ID as it would be generated during save
            return savedDepartement;
        });

        int id = departementService.ajouterdepartement(departmentDTO);

        assertTrue(id > 0);

        verify(departementRepository, times(1)).save(any(Departement.class));
    }


    @Test
    void getAllD() {
        when(departementRepository.findAll()).thenReturn(departements);

        List<Departement> departments = departementService.getAllD();

        assertNotNull(departments);
        assertTrue(departments.size() > 0);

        verify(departementRepository, times(1)).findAll();
    }

   @Test
void update() {
    DepartementDTO departmentDTO = new DepartementDTO("Département de test", "Test Department");
    int id = departementService.ajouterdepartement(departmentDTO);

    // Configure the behavior of your service method
    when(departementService.update(departmentDTO)).thenReturn(true);

    // Update the department DTO
    departmentDTO.setNomDepart("Updated Name");

    // Call your update method and assert the result
    boolean updated = departementService.update(departmentDTO);

    assertTrue(updated);

    // Configure the behavior of your service method to get the updated department
    when(departementService.getDepartmentById(id)).thenReturn(
        new Departement(id, "Updated Name", "Updated Description")
    );

    // Call your getDepartmentById method to retrieve the updated department
    Departement updatedDepartment = departementService.getDepartmentById(id);

    // Assert the updated department properties
    assertNotNull(updatedDepartment);
    assertEquals("Updated Name", updatedDepartment.getName());
    assertEquals("Updated Description", updatedDepartment.getNomDepart());
}}
/*

    @Test
    void delete() {
        DepartementDTO departmentDTO = new DepartementDTO("Département de test", "Test Department");
        int id = departementService.ajouterdepartement(departmentDTO);

        // Set the expected behavior for deleteById in the repository
        doNothing().when(departementRepository).deleteById(id);

        boolean deleted = departementService.delete(departmentDTO);

        assertTrue(deleted);

        when(departementService.getDepartmentById(id)).thenReturn(null);

        Departement deletedDepartment = departementService.getDepartmentById(id);

        assertNull(deletedDepartment);
    }
}
*/