package com.example.lab2;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView; // Importera TextView-klassen
import java.util.Random;

public class Activity2 extends AppCompatActivity {
    String ord[] = {"banan", "hejhejhejhej", "teater"};
    Random random = new Random();
    int forsok = 7;
    String slumpatOrd;
    StringBuilder doltOrd;
    TextView slumpadTextView; // Deklarera en TextView-variabel

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        slumpadTextView = findViewById(R.id.slumpad);
        slumpatOrd = ord[random.nextInt(ord.length)];

        doltOrd = new StringBuilder();
        for (int i = 0; i < slumpatOrd.length(); i++) {
            doltOrd.append("_");
        }

        slumpadTextView.setText(doltOrd.toString());
    }
}