package tn.esprit.spring.kaddem.repositories;


import tn.esprit.spring.kaddem.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {


    Etudiant findByNomEAndPrenomE(String nomE, String prenomE);
}
