package com.spring.kaddem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.spring.kaddem.entities.*;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtudiantDto {
  
    private Integer idEtudiant;
    private String prenomE;
    private String nomE;
    @JsonIgnore
    private  Option op;
    @JsonIgnore
    private Departement departement;
    @JsonIgnore
    private List<Equipe> equipes;
    @JsonIgnore
    private List<Contrat> contrats;


    public static Etudiant toEntity(EtudiantDto etudiantDto) {
        if (etudiantDto == null) {
            //TODO EXCEPTION ERRROR
            return null;
        }
        Departement departement1 = etudiantDto.getDepartement();
        List<Equipe>  equipesList = etudiantDto.getEquipes();
        List<Contrat> contratList = etudiantDto.getContrats();
        return Etudiant.builder()
                .idEtudiant(etudiantDto.getIdEtudiant())
                .prenomE(etudiantDto.getPrenomE())
                .nomE(etudiantDto.getNomE())
                .op(etudiantDto.getOp())
                .departement(departement1)
                .equipes(equipesList)
                .contrats(contratList)
                .build();
    }
  
    public static EtudiantDto toDto(Etudiant etudiant){
        if(etudiant==null){
            //TODO EXCEPTION ERRROR
            return null;
        }
        Departement departementDtos = etudiant.getDepartement();
        List<Equipe>  equipesListDtos = etudiant.getEquipes();
        List<Contrat> contratListDtos = etudiant.getContrats();
        return  EtudiantDto.builder()
                .idEtudiant(etudiant.getIdEtudiant())
                .prenomE(etudiant.getPrenomE())
                .nomE(etudiant.getNomE())
                .op(etudiant.getOp())
                .departement(departementDtos)
                .equipes(equipesListDtos)
                .contrats(contratListDtos)
                .build();
    }
}
