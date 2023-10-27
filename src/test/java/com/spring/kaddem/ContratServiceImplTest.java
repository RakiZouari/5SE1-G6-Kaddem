package com.spring.kaddem;

import com.spring.kaddem.dto.ContratDTO;
import com.spring.kaddem.entities.Contrat;
import com.spring.kaddem.entities.Specialite;
import com.spring.kaddem.repositories.ContratRepository;
import com.spring.kaddem.services.IContratService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.mockito.ArgumentMatchers;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Slf4j
class ContratServiceImplTest {

    @Mock
	ContratRepository contratRepository;

	@Autowired
	IContratService contratService;
	@Mock
	private Contrat contrat;
	private List<Contrat> contrats;
	@BeforeEach
	void setUp() {
		contrat = new Contrat();
		contrat.setDateDebutContrat("2022/04/22");
		contrat.setDateFinContrat("2022/06/24");
		contrat.setMontantContrat(1000);
		contrat.setArchived(true);
		contrat.setSpecialite(Specialite.IA);
	}
	@BeforeEach
	void setEtudiants(){
		contrats=new ArrayList();
		Contrat contrat1 = new Contrat();
		contrat1.setDateDebutContrat("2022/04/29");
		contrat1.setDateFinContrat("2022/06/30");
		contrat1.setMontantContrat(2000);
		contrat1.setArchived(true);
		contrat1.setSpecialite(Specialite.SECURITE);
		contrats.add(contrat1);

		Contrat contrat2 = new Contrat();
		contrat2.setDateDebutContrat("2022/04/29");
		contrat2.setDateFinContrat("2022/06/30");
		contrat2.setArchived(true);
		contrat2.setMontantContrat(3000);
		contrat2.setSpecialite(Specialite.CLOUD);
		contrats.add(contrat2);

	}

	@Test
	void addContrat() {
		ContratDTO newcontratDto = new ContratDTO();
		newcontratDto.setDateDebutContrat("2022/04/29");
		newcontratDto.setDateFinContrat("2022/06/30");
		newcontratDto.setArchived(true);
		newcontratDto.setMontantContrat(3000);
		newcontratDto.setSpecialite(Specialite.CLOUD);

		when(contratRepository.save(any(Contrat.class))).thenAnswer(invocation -> {
			Contrat saveContrat = invocation.getArgument(0);
			saveContrat.setIdContrat(1); // Set the ID as it would be generated during save
			return saveContrat;
		});

		ContratDTO addContrattoDto = ContratDTO.toDto(contratService.addContrat(newcontratDto));

		verify(contratRepository).save(any(Contrat.class));

		assertEquals("2022/04/29", addContrattoDto.getDateDebutContrat());
		assertEquals("2022/06/30", addContrattoDto.getDateFinContrat());
		assertEquals(3000, addContrattoDto.getMontantContrat());
		assertEquals(true, addContrattoDto.getArchived());
		assertEquals(Specialite.CLOUD, addContrattoDto.getSpecialite());
	}
/*
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	@Transactional
	@Rollback(true)
	void testRetrieveAllContrats() {
		// Créez une liste fictive de contrats
		List<Contrat> contrats = new ArrayList<>();
		contrats.add(new Contrat());
		contrats.add(new Contrat());

		// Définissez le comportement du mock ContratRepository
		when(contratRepository.findAll()).thenReturn(contrats);

		// Appelez la méthode à tester
		List<Contrat> result = contratService.retrieveAllContrats();

		// Vérifiez le résultat
		assertEquals(2, result.size());
	}
	 @Test
	 @Transactional
	 @Rollback(true)
	    void testAddContrat() {
		// Créez un objet ContratDTO fictif
		ContratDTO contratDTO = new ContratDTO();
		contratDTO.setDateDebutContrat(new Date());
		contratDTO.setDateFinContrat(new Date());
		contratDTO.setMontantContrat(1000);

		// Créez un objet Contrat fictif
		Contrat contrat = new Contrat();
		contrat.setDateDebutContrat(contratDTO.getDateDebutContrat());
		contrat.setDateFinContrat(contratDTO.getDateFinContrat());
		contrat.setMontantContrat(contratDTO.getMontantContrat());

		// Définissez le comportement du mock ContratRepository
		when(contratRepository.save(any(Contrat.class))).thenReturn(contrat);

		// Appelez la méthode à tester
		Contrat resultContrat = contratService.addContrat(contratDTO);

		// Vérifiez le résultat
		assertEquals(contrat, resultContrat);

		// Vérifiez que la méthode save de ContratRepository a été appelée avec le bon objet Contrat
		verify(contratRepository).save(argThat(savedContrat -> savedContrat.equals(contratDTO)));
	}

	@Test
	@Transactional
	@Rollback(true)
	    void testUpdateContrat_ExistingContrat() {
		// Créez un objet ContratDTO fictif
		ContratDTO contratDTO = new ContratDTO();
		contratDTO.setDateDebutContrat(new Date());
		contratDTO.setDateFinContrat(new Date());
		contratDTO.setMontantContrat(2000);

		// Créez un objet Contrat fictif existant
		Contrat existingContrat = new Contrat();
		existingContrat.setIdContrat(1); // ID de l'objet existant
		existingContrat.setDateDebutContrat(new Date());
		existingContrat.setDateFinContrat(new Date());
		existingContrat.setMontantContrat(1000);

		// Définissez le comportement du mock ContratRepository pour findById()
		when(contratRepository.findById(1)).thenReturn(Optional.of(existingContrat));

		// Définissez le comportement du mock ContratRepository pour save()
		when(contratRepository.save(any(Contrat.class))).thenReturn(existingContrat);

		// Appelez la méthode à tester
		Contrat updatedContrat = contratService.updateContrat(1, contratDTO);

		// Vérifiez le résultat
		assertEquals(contratDTO.getDateDebutContrat(), updatedContrat.getDateDebutContrat());
		assertEquals(contratDTO.getDateFinContrat(), updatedContrat.getDateFinContrat());
		assertEquals(contratDTO.getMontantContrat(), updatedContrat.getMontantContrat());
	}

	@Test
	@Transactional
	@Rollback(true)
	    void testRetrieveContrat_ExistingContrat() {
		// Créez un objet Contrat fictif
		Contrat existingContrat = new Contrat();
		existingContrat.setIdContrat(1); // ID de l'objet existant
		existingContrat.setDateDebutContrat(new Date());
		existingContrat.setDateFinContrat(new Date());
		existingContrat.setMontantContrat(1000);

		// Définissez le comportement du mock ContratRepository pour findById()
		when(contratRepository.findById(1)).thenReturn(Optional.of(existingContrat));

		// Appelez la méthode à tester
		Contrat retrievedContrat = contratService.retrieveContrat(1);

		// Vérifiez le résultat
		assertEquals(existingContrat, retrievedContrat);
	}

	@Test
	@Transactional
	@Rollback(true)
	   void testRetrieveContrat_NonExistingContrat() {
		// Définissez le comportement du mock ContratRepository pour findById()
		when(contratRepository.findById(1)).thenReturn(Optional.empty());

		// Appelez la méthode à tester et vérifiez qu'elle lance une exception EntityNotFoundException
		assertThrows(EntityNotFoundException.class, () -> contratService.retrieveContrat(1));
	}

	@Test
	@Transactional
	@Rollback(true)
	  void testRemoveContrat() {
		// Définissez l'ID du contrat à supprimer
		Integer idContrat = 1;

		// Appelez la méthode à tester
		contratService.removeContrat(idContrat);

		// Vérifiez que la méthode deleteById de ContratRepository a été appelée avec le bon ID
		verify(contratRepository).deleteById(idContrat);
	}









	//on a initialiser un objet sa pour tester avec

	Contrat f = new Contrat(0,java.sql.Date.valueOf("2020-10-10"),java.sql.Date.valueOf("2022-10-05"), Specialite.IA,null,null,null);
	List<Contrat> contratsInit = new ArrayList<Contrat>() {
		{
		add (new Contrat(3,java.sql.Date.valueOf("2020-10-10"),java.sql.Date.valueOf("2019-10-05"), Specialite.CLOUD,null,null,null));
		add (new Contrat(4,java.sql.Date.valueOf("2020-10-10"),java.sql.Date.valueOf("2018-10-20"), Specialite.RESEAU,null,null,null));
		add (new Contrat(5,java.sql.Date.valueOf("2020-10-10"),java.sql.Date.valueOf("2017-05-10"), Specialite.SECURITE,null,null,null));

		}
	};
	
	
	@Test
	void testRetrieveAllContrats() {

		
		Mockito.doReturn(contratsInit).when(contratRepository).findAll();
        List<Contrat> contrats = contratService.retrieveAllContrats();
		Assertions.assertNotNull(contrats);


		
	}	

	@Test
	void testAddContrat() {
		
		Contrat c = new Contrat();
		Mockito.when(contratRepository.save(Mockito.any(Contrat.class))).thenReturn(c);
		Contrat fou=contratService.addContrat(c);
		Assertions.assertNotNull(fou);	
	}

 */
	

	

}
