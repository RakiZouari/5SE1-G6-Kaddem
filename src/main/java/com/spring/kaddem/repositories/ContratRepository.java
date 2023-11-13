package com.spring.kaddem.repositories;


import com.spring.kaddem.entities.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Date;




@Repository
public interface ContratRepository extends JpaRepository<Contrat, Integer> {


}
