package com.spring.kaddem;

import com.spring.kaddem.entities.DetailEquipe;
import com.spring.kaddem.repositories.DetailEquipeRepository;
import com.spring.kaddem.services.DetailEquipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class DetailEquipeServiceTest {

    @Mock
    private DetailEquipeRepository detailEquipeRepository;

    @InjectMocks
    private DetailEquipeService detailEquipeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetEquipeById() {
        Long id = 1L;
        DetailEquipe expectedDetailEquipe = new DetailEquipe();
        expectedDetailEquipe.setIdDetailEquipe(Math.toIntExact(id));

        when(detailEquipeRepository.findById(id)).thenReturn(Optional.of(expectedDetailEquipe));

        DetailEquipe result = detailEquipeService.getEquipeById(id);

        assertNotNull(result);
        assertEquals(expectedDetailEquipe, result);

        verify(detailEquipeRepository, times(1)).findById(id);
    }

    @Test
    void testAddDetailEquipe() {
        DetailEquipe detailEquipe = new DetailEquipe();

        detailEquipeService.addDetailEquipe(detailEquipe);

        verify(detailEquipeRepository, times(1)).save(detailEquipe);
    }

    @Test
    void testRemoveDetailEquipe() {
        Long id = 1L;

        detailEquipeService.removeDetailEquipe(id);

        verify(detailEquipeRepository, times(1)).deleteById(id);
    }

    @Test
    void testUpdateDetailEquipe() {
        Long id = 1L;
        DetailEquipe existingDetailEquipe = new DetailEquipe();
        existingDetailEquipe.setIdDetailEquipe(Math.toIntExact(id));

        DetailEquipe updatedDetailEquipe = new DetailEquipe();
        updatedDetailEquipe.setSalle(1);
        updatedDetailEquipe.setThematique("Updated Thematique");

        when(detailEquipeRepository.findById(id)).thenReturn(Optional.of(existingDetailEquipe));
        when(detailEquipeRepository.save(existingDetailEquipe)).thenReturn(updatedDetailEquipe);

        DetailEquipe result = detailEquipeService.updateDetailEquipe(id, updatedDetailEquipe);

        assertNotNull(result);
        assertEquals(updatedDetailEquipe, result);

        verify(detailEquipeRepository, times(1)).findById(id);
        verify(detailEquipeRepository, times(1)).save(existingDetailEquipe);
    }
}
