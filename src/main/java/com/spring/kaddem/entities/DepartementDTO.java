package com.spring.kaddem.entities;

public class DepartementDTO {
    private  int departementId;
    private String nomDepart;
    private String name;



    public DepartementDTO(String nomDepart, String name) {
        this.nomDepart = nomDepart;
        this.name = name;
    }

    // Getters et setters
    public String getNomDepart() {
        return nomDepart;
    }

    public void setNomDepart(String nomDepart) {
        this.nomDepart = nomDepart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIDepartementID() {
        return departementId;
    }
}
