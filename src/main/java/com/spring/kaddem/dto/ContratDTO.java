package com.spring.kaddem.dto;

import com.spring.kaddem.entities.Specialite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContratDTO {
    @Lob
    private Date dateDebutContrat;
    private Date dateFinContrat;
    private Specialite specialite;
    private Boolean archived;
    private Integer montantContrat;

}
