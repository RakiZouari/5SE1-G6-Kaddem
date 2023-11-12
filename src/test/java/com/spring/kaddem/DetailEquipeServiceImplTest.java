/*package com.spring.kaddem;

import com.spring.kaddem.entities.DetailEquipe;
import com.spring.kaddem.repositories.DetailEquipeRepository;
import com.spring.kaddem.services.IDetailEquipeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
public class DetailEquipeServiceImplTest {
    @Autowired
    private IDetailEquipeService iDetailEquipeService;

    @MockBean
    private DetailEquipeRepository detailEquipeRepository;
    private DetailEquipe detailEquipe;

    private List<DetailEquipe> detailEquipes;

    @BeforeEach
    void setUp() {
        detailEquipe = new DetailEquipe();
        detailEquipe.setThematique("Test Department");
        detailEquipes = new ArrayList<>();
    }

    @Test
    void ajouterDetailEquipe() {
        when(detailEquipeRepository.save(detailEquipe)).thenReturn(detailEquipe);
        DetailEquipe result = iDetailEquipeService.addDetailEquipe(detailEquipe);
        verify(detailEquipeRepository).save(detailEquipe);
        assertEquals(detailEquipe, result);
         }
    }*/


