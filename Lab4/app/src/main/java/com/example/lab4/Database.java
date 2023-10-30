package com.example.lab4;

import java.util.ArrayList;

//Denna kod kommer delvis från Alexanders föreläsning och så fick vi hjälp på handledning
public class Database {
private ArrayList <ProductClass> products;
    public static Database instance;

    public Database() {
        products = new ArrayList<ProductClass>(); //Skapar en ny ArrayList för produkter när en instans av klassen skapas
        if (instance == null) {
            instance = this;
        }
    }
    public void add(ProductClass pc){ //lägger till en produkt i databasen
        products.add(pc);
    }
    public ArrayList <ProductClass> getProducts(){ //hämtar produkter från databasen
        return products;
    }
    public String printProducts(){ //skapar en sträng med de varor som har lagts till i varukorgen
        String print = "";

        for(int i = 0; i < products.size(); i++){//lägger till produktens namn och pris i strängen med radbrytning
            print += products.get(i).getnamn() + " ";
            print += products.get(i).getPris() + " " + "\n";


        }

        return print;
    }

    public void setProducts(ArrayList<ProductClass> products) {
        this.products = products;
    }
}