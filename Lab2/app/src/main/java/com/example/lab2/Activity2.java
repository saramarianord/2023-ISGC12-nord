package com.example.lab2;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView; // Importera TextView-klassen
import android.widget.Toast;

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

    public void guessLetter(View view) {
        EditText guessEditText = findViewById(R.id.guess);
        String input = guessEditText.getText().toString(); // Gör inmatningen till små bokstäver

        for (int i = 0; i < slumpatOrd.length(); i++) {
            if (slumpatOrd.charAt(i) == input.charAt(0)) {
                doltOrd.setCharAt(i, input.charAt(0));
            }
        }

        slumpadTextView.setText(doltOrd.toString());
        guessEditText.setText("");

        if(!slumpadTextView.getText().toString().contains("_")){ // https://stackoverflow.com/questions/9915203/how-to-check-if-a-textview-contains-a-certain-string

            Toast.makeText(Activity2.this, "GRATTIS BIIIITCH", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }
    }

}