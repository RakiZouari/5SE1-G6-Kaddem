package com.spring.kaddem.services;

import com.spring.kaddem.dto.ContratDTO;
import com.spring.kaddem.entities.Contrat;
import com.spring.kaddem.entities.Etudiant;
import com.spring.kaddem.entities.Specialite;
import com.spring.kaddem.repositories.ContratRepository;
import com.spring.kaddem.repositories.EtudiantRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;



@Service
@AllArgsConstructor
@Slf4j
public class ContratServiceImpl implements  IContratService{


    ContratRepository contratRepository;
    EtudiantRepository etudiantRepository;

    @Override
    public List<Contrat> retrieveAllContrats() {
        return contratRepository.findAll();
    }


    @Override
    public Contrat retrieveContrat(Integer idContrat) {
        Optional<Contrat> contratOptional = contratRepository.findById(idContrat);

        if (contratOptional.isPresent()) {
            return contratOptional.get();
        } else {

            throw new EntityNotFoundException(" Contrat not found");
        }
    }

    @Override
    public void removeContrat(Integer idContrat) {
        log.info("debut methode removeContrat");
        contratRepository.deleteById(idContrat);
    }

    @Override
    public ContratDTO addUpdateContrat(ContratDTO c) {

        contratRepository.save(ContratDTO.toEntity(c));

        return c;
    }

    @Transactional
    public ContratDTO addAndAffectContratToEtudiant(ContratDTO contratDTO, String nomE, String prenomE) {
        long startTime = System.currentTimeMillis();
        log.info("Start Time: " + startTime);
        log.info("Start of addAndAffectContratToEtudiant method");

        Etudiant etudiant = etudiantRepository.findByNomEAndPrenomE(nomE, prenomE);

        if (etudiant == null) {
            log.error("Student not found with name: " + nomE + " " + prenomE);
            throw new EntityNotFoundException("Student not found with name: " + nomE + " " + prenomE);
        }

        // Number of active contracts
        int nbContratsActifs = etudiant.getContrats().size();

        if (nbContratsActifs >= 5) {
            log.info("Number of allowed contracts is reached");
            log.info("End of addAndAffectContratToEtudiant method");
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            log.info("End Time: " + endTime);
            log.info("Execution Time: " + executionTime + " ms");
            return contratDTO;
        }

        // Convert ContratDTO to Contrat entity if needed
        Contrat contrat = convertToEntity(contratDTO);

        // Save the contrat
        contrat.setEtudiant(etudiant);
        contrat = contratRepository.save(contrat);

        // Convert Contrat entity back to ContratDTO
        ContratDTO savedContratDTO = convertToDTO(contrat);

        log.info("End of addAndAffectContratToEtudiant method");
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        log.info("End Time: " + endTime);
        log.info("Execution Time: " + executionTime + " ms");

        return savedContratDTO;
    }

    private Contrat convertToEntity(ContratDTO contratDTO) {
        Contrat contrat = new Contrat();
        contrat.setDateDebutContrat(contratDTO.getDateDebutContrat());
        contrat.setDateFinContrat(contratDTO.getDateFinContrat());
        contrat.setMontantContrat(contratDTO.getMontantContrat());
        // Set other fields if there are more properties in ContratDTO

        return contrat;
    }

    private ContratDTO convertToDTO(Contrat contrat) {
        ContratDTO contratDTO = new ContratDTO();
        contratDTO.setDateDebutContrat(contrat.getDateDebutContrat());
        contratDTO.setDateFinContrat(contrat.getDateFinContrat());
        contratDTO.setMontantContrat(contrat.getMontantContrat());
        // Set other fields if there are more properties in ContratDTO

        return contratDTO;
    }


}
