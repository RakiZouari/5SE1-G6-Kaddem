package tn.esprit.spring.kaddem.repositories;


import tn.esprit.spring.kaddem.entities.DetailEquipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailEquipeRepository extends JpaRepository<DetailEquipe, Integer> {
}
