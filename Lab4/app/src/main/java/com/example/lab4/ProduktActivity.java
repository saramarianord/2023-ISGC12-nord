package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ProduktActivity extends AppCompatActivity {
private ProductClass product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produkt);
        new Database();
        new DataManager();

        // hämtar data om produkten från Intent
        Intent intent = getIntent();
         product = (ProductClass) intent.getSerializableExtra("produkt");

         //uppdaterar TextViews för produktnamn, beskrivning, företag och pris
        TextView produktNamn = findViewById(R.id.produktNamn);
        produktNamn.setText(product.getnamn());

        TextView produktInfo = findViewById(R.id.produktInfo);
        produktInfo.setText(product.getDescription());

        TextView foretagTv = findViewById(R.id.foretagTv);
        foretagTv.setText(product.getCompany());

        TextView produktPris = findViewById(R.id.produktPris);
        produktPris.setText(product.getPris());
    }

    public void addToCart(View view) {//metod för att lägga till produkten i varukorgen
       Database.instance.add(product);
       DataManager.instance.writeToFile(ProduktActivity.this, Database.instance.getProducts());// sparar ändringar i databasen till en fil med hjälp av DataManager

        //visar ett meddelande om att produkten har lagts till i varukorgen
        CharSequence text = "Din vara är tillagd i varukorgen";
        int length = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(ProduktActivity.this, text, length);
        toast.show();
    }


    public void showCart(View view) {// metod för att visa varukorgen
        Log.e("TAG", "showCart: "+ Database.instance.getProducts().size() );

        // aktiviteten för att visa varukorgen
        Intent intent = new Intent(this, CartActivity.class);
        //intent.putExtra("produkt", productClass);
        startActivity(intent);

    }

}