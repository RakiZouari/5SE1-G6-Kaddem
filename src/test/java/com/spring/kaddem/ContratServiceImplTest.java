package com.spring.kaddem;

import com.spring.kaddem.entities.Contrat;
import com.spring.kaddem.entities.Specialite;
import com.spring.kaddem.repositories.ContratRepository;
import com.spring.kaddem.services.IContratService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Slf4j
class ContratServiceImplTest {

    @Mock
	ContratRepository contratRepository;

	@InjectMocks
	IContratService contratService;
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
	

	

}
