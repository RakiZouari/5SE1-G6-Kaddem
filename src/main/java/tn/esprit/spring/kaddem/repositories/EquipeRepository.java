package tn.esprit.spring.kaddem.repositories;


import tn.esprit.spring.kaddem.entities.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EquipeRepository  extends JpaRepository<Equipe, Integer> {
    

}
