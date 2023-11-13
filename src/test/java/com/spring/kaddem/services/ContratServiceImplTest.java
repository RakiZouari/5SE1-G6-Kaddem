package com.spring.kaddem.services;

import com.spring.kaddem.dto.ContratDTO;
import com.spring.kaddem.entities.Contrat;
import com.spring.kaddem.entities.Etudiant;
import com.spring.kaddem.entities.Specialite;
import com.spring.kaddem.repositories.ContratRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Slf4j
class ContratServiceImplTest {

    @MockBean
	ContratRepository contratRepository;

	@Autowired
	IContratService contratService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	void testRetrieveAllContrats() {

		List<Contrat> sampleContrats = new ArrayList<>();

		Contrat contrat1 = new Contrat();
		contrat1.setIdContrat(1);
		contrat1.setMontantContrat(1000);

		sampleContrats.add(contrat1);

		Contrat contrat2 = new Contrat();
		contrat2.setIdContrat(2);
		contrat2.setMontantContrat(2000);

		sampleContrats.add(contrat2);

		when(contratRepository.findAll()).thenReturn(sampleContrats);

		// Call the service method
		List<Contrat> result = contratService.retrieveAllContrats();

		// Verify that the service method returned the expected data
		assertEquals(2, result.size());
		verify(contratRepository, times(1)).findAll();

		System.err.println("GetAllContratsTest : Ok");
	}

	@Test
	void testAddContrat() {
		// Create a sample Contrat to add
		ContratDTO newContrat = new ContratDTO();
		newContrat.setIdContrat(3);
		newContrat.setDateDebutContrat(new Date()); // You can use java.util.Date for date fields
		newContrat.setDateFinContrat(new Date());
		newContrat.setSpecialite(Specialite.IA); // Replace with a valid enum value
		newContrat.setArchived(false);
		newContrat.setMontantContrat(5000);

		// Create a sample Etudiant
		Etudiant etudiant = new Etudiant();
		etudiant.setNomE("Raki");
		newContrat.setEtudiant(etudiant);

		// Mock the behavior of the contratRepository to save the newContrat and return it
		when(contratRepository.save(ContratDTO.toEntity(newContrat))).thenReturn(ContratDTO.toEntity(newContrat));

		// Call the service method
		ContratDTO addedContrat = contratService.addUpdateContrat(newContrat);



		// Verify that the service method returned the added contract
		assertEquals(newContrat, addedContrat);

		// Verify that the save method was called on the contratRepository
		verify(contratRepository).save(any(Contrat.class));

		System.err.println("AddContratTest : Ok");

	}

	@Test
	void testUpdateContrat() {
		// Create a sample ContratDTO to update
		ContratDTO existingContratDTO = ContratDTO.builder()
				.idContrat(1)
				// Set other properties as needed
				.build();

		// Mock the behavior of the contratRepository to save the updated ContratDTO and return it
		when(contratRepository.save(ContratDTO.toEntity(existingContratDTO))).thenReturn(ContratDTO.toEntity(existingContratDTO));

		// Call the service method
		ContratDTO updatedContratDTO = contratService.addUpdateContrat(existingContratDTO);

		// Verify that the service method returned the updated contractDTO
		assertEquals(existingContratDTO, updatedContratDTO);

		// Verify that the save method was called on the contratRepository
		verify(contratRepository).save(any(Contrat.class));

		System.err.println("UpdateContratTest : Ok");
	}
	@Test
	void testRetrieveContrat() {


		Contrat contrat1 = new Contrat();
		contrat1.setIdContrat(1);
		when(contratRepository.findById(1)).thenReturn(Optional.of(contrat1));
		Contrat retrievedContrat = contratService.retrieveContrat(1);
		verify(contratRepository).findById(1);
		assertEquals(contrat1, retrievedContrat);

	}
	@Test
	void testRemoveEtudiant() {
		int ContratIdToRemove = 1;
		contratService.removeContrat(ContratIdToRemove);
		verify(contratRepository).deleteById(ContratIdToRemove);
	}


}
