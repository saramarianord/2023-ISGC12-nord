package com.example.lab4;

import java.io.Serializable;

//Denna fick vi hjälp med på Alexnders handledning
public class ProductClass implements Serializable {
    private String namn;
    private String description;
    private String pris;

    private String company;

    // Konstruktor för att skapa en instans av ProductClass
    public ProductClass(String namn, String description, String pris, String company){
        this.namn = namn;
        this.description = description;
        this.pris = pris;
        this.company = company;
    }

    //hämtar produktnamn
    public String getnamn(){
        return this.namn;
    }

    //hämtar produktbeskrivning
    public String getDescription(){
        return this.description;
    }

    // hämtar produkten pris (som en sträng)
    public String getPris(){

        return this.pris;
    }

    //hämtar företag
    public String getCompany(){
        return this.company;
    }
}
