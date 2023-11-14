package tn.esprit.spring.kaddem.repositories;


import tn.esprit.spring.kaddem.entities.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ContratRepository extends JpaRepository<Contrat, Integer> {

    List<Contrat> findByEtudiantIdEtudiant(Integer idEtudiant);

}
