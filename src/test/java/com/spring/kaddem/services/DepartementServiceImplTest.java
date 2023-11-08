package com.spring.kaddem.services;

import com.spring.kaddem.entities.Departement;
import com.spring.kaddem.entities.DepartementDTO;
import com.spring.kaddem.repositories.DepartementRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
class DepartementServiceImplTest {

    @Mock
    private DepartementServiceImpl departementService;

    @Mock
    private DepartementRepository departementRepository;

    @Mock
    private Departement departement;
    private List<Departement> departements;
/*
    @BeforeEach
    void setUp() {
        departement = new Departement();
        departement.setNomDepart("Test Department");
    }

    @Test
    void ajouterdepartement() {
        DepartementDTO departmentDTO = new DepartementDTO("Département de test", "Test Department");

        when(departementRepository.save(any(Departement.class))).thenReturn(new Departement());

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

        when(departementService.update(departmentDTO)).thenReturn(true);

        departmentDTO.setName("Updated Name");
        departmentDTO.setNomDepart("Updated Description");

        boolean updated = departementService.update(departmentDTO);

        assertTrue(updated);

        when(departementService.getDepartmentById(id)).thenReturn(new Departement(id, "Updated Name", "Updated Description"));

        Departement updatedDepartment = departementService.getDepartmentById(id);

        assertNotNull(updatedDepartment);
        assertEquals("Updated Name", updatedDepartment.getName());
        assertEquals("Updated Description", updatedDepartment.getNomDepart());
    }

    @Test
    void delete() {
        DepartementDTO departmentDTO = new DepartementDTO("Département de test", "Test Department");
        int id = departementService.ajouterdepartement(departmentDTO);

        // No need to use 'when' for void methods
        doNothing().when(departementService).delete(departmentDTO);

        boolean deleted = departementService.delete(departmentDTO);

        assertTrue(deleted);

        when(departementService.getDepartmentById(id)).thenReturn(null);

        Departement deletedDepartment = departementService.getDepartmentById(id);

        assertNull(deletedDepartment);
    }
*/
}
