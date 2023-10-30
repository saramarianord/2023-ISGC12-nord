package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

import org.json.JSONArray;
import org.json.JSONObject;

//Största delen av koden kommer från Alexanders föreläsning
public class MainActivity extends AppCompatActivity implements VolleyInterface{
    private static int PAGE = 1;

    //https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY + "&mode=json

    private ApiActivity apiCall;
    private String API_KEY;
    private RequestQueue requestQueue;
    private String url;
    private LinearLayout lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = findViewById(R.id.produktLista);
        new Database();

        //// Konfigurera en RequestQueue med cache och nätverk för att hantera nätverksförfrågningar
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();

        API_KEY = "h2s269nsMn012NASi2537bsA9dBSa2";// // Anger API-nyckel och skapar URL för begäran
        int PAGE = 1;
        url = "https://informatik-webbkurser.hotell.kau.se/WebAPI/v1/products?limit=10&page="+ PAGE + "&apikey=" + API_KEY+ "&mode=json";

        /// Skapa en instans av ApiActivity för att hantera nätverksbegäran
        apiCall = new ApiActivity();
        apiCall.get(requestQueue, this, url);

    }

    @Override
            public void onSuccess(JSONObject object) {
        try {
            Log.e("TAG", object.toString());
            // Hämtar JSON-arrayen "products" från API-svaret
            JSONArray produkter = object.getJSONArray("products");
            Button btn = findViewById(R.id.knappVisa);

            if (produkter.length() > 0) {

                for (int i = 0; i < produkter.length(); i++) {
                    JSONObject produkt = produkter.getJSONObject(i);
                    String name = produkt.getString("name");
                    String price = produkt.getString("price");
                    String description = produkt.getString("description");
                    String company = produkt.getString("company");
                    View productView = getLayoutInflater().inflate(R.layout.produktitem, null);
                    TextView NameText = productView.findViewById(R.id.textNamn);

                    NameText.setText(name);
                    lista.addView(productView);
                    ProductClass productClass = new ProductClass(name, description,price,company);
                    Button b = productView.findViewById(R.id.knappVisa);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.e("error", name);
                            Intent intent = new Intent(MainActivity.this, ProduktActivity.class);
                            intent.putExtra("produkt", productClass);
                            startActivity(intent);
                        }
                    });



                }
                PAGE++;
            } else {
                Toast.makeText(this, "No categories found", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Exception e) {
        Log.e("MAIN ACTIVITY!", "onFailure: " + e.toString());
    }

    public void laddaMer(View view) { // Metod för att ladda mer data från API:et och uppdatera sidan med fler produkter
        url = "https://informatik-webbkurser.hotell.kau.se/WebAPI/v1/products?limit=10&page="+ PAGE + "&apikey=" + API_KEY+ "&mode=json";
        apiCall.get(requestQueue, this, url);
    }

    public void viewCart(View view) {//// Metod för att visa kundvagnen
        Intent intent = new Intent(this, CartActivity.class);
        //intent.putExtra("produkt", productClass);
        startActivity(intent);
    }
}