package tn.esprit.spring.kaddem.repositories;


import tn.esprit.spring.kaddem.entities.Universite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UniversiteRepository extends JpaRepository<Universite, Integer> {
}
