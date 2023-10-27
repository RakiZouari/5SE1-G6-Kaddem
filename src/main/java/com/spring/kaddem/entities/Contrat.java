package com.spring.kaddem.entities;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contrat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idContrat;
    @Temporal(TemporalType.DATE)
    private String dateDebutContrat;
    @Temporal(TemporalType.DATE)
    private String dateFinContrat;
    @Enumerated(EnumType.STRING)
    private Specialite specialite;
    private Boolean archived;
    private Integer montantContrat;
    @ManyToOne
    // @JsonIgnore
    private  Etudiant etudiant;
    //Palestine

}

