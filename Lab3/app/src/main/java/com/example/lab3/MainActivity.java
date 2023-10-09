package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;
import java.net.URL;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

//Vi har utgått från Alexanders föreläsning
public class MainActivity extends AppCompatActivity {
    private final String API_KEY = "API-Key";
    private EditText artistText;
    private TextView artistList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        artistText = findViewById(R.id.artistText);
        artistList = findViewById(R.id.artistList);

    }

    public void getData(View view) {

        URL url;
        String apiString = "https://ws.audioscrobbler.com/2.0/?method=artist.getsimilar&artist=" + artistText.getText().toString() + "&api_key=" + API_KEY + "&limit=10";

        try {
            url = new URL(apiString);

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();
            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            String artistNames = "";

            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                if (parserEvent == XmlPullParser.START_TAG) {
                    String tagName = parser.getName();

                    if (tagName.contains("name")) {
                        String name = parser.nextText();
                        artistNames += name + "\n";
                        artistNames += "\n";

                        artistList.setText(artistNames);
                        artistText.onEditorAction(EditorInfo.IME_ACTION_DONE); //Gömmer tangentbordet https://www.youtube.com/watch?v=V54largrb7E
                    }

                }
                parserEvent = parser.next();

            }


        } catch (Exception e) {
            Toast toast = Toast.makeText(MainActivity.this, "Ups! något gick fel!" + e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }


    }
}
