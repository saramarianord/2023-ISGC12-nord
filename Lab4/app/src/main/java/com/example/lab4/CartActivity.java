package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
//Denna kod fick vi hjälp med på Alexander handledning
public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        TextView textNamn = findViewById(R.id.textNamn);
        textNamn.setText(Database.instance.printProducts());// // Anropa printProducts-metoden i Database.instance och sätter texten i TextView


        //Skapar nya instanser
        new DataManager();
        new Database();
        Database.instance.setProducts(DataManager.instance.readFromFile(this));//// Läser data från en fil med hjälp av DataManager och sätter produkterna
        textNamn.setText(Database.instance.printProducts());//// Uppdatera texten i TextView till den uppdaterade produktlistan
    }
}