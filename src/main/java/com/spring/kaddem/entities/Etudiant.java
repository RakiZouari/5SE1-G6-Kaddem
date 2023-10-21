package com.spring.kaddem.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Etudiant  implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEtudiant;
    private String prenomE;
    private String nomE;
    @Enumerated(EnumType.STRING)
    private  Option op;

    @Override
    public String toString() {
        return "Etudiant{" +
                "idEtudiant=" + idEtudiant +
                ", prenomE='" + prenomE + '\'' +
                ", nomE='" + nomE + '\'' +
                ", op=" + op +
                ", departement=" + departement +
                ", equipes=" + equipes +
                ", contrats=" + contrats +
                '}';
    }

    @ManyToOne
    @JsonIgnore
    private Departement departement;
    @ManyToMany
    @JsonIgnore
    private List<Equipe> equipes;
    @OneToMany(mappedBy = "etudiant")
    @JsonIgnore
    private List<Contrat> contrats;


}
