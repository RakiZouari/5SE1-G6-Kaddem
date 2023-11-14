package tn.esprit.spring.kaddem.repositories;


import tn.esprit.spring.kaddem.entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepartementRepository extends JpaRepository<Departement, Integer> {
}
