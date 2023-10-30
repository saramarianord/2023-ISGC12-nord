package com.example.lab4;
import android.content.Context;
import android.util.Log;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

//Denna kod kommer från Alexanders föreläsning
public class DataManager {
    public static DataManager instance;
    private final String FILENAME = "produkter.txt"; // varukorgen sparas till textfilen med namn "produkter.txt"

    public DataManager(){
        if(instance == null){
            instance = this;
        }
    }


    public void writeToFile(Context context, ArrayList<ProductClass> list){ //skriver en lista med produkterna till textfilen
        try{
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
            fos.close();
        }catch(Exception e){
            Log.e("DataManager", "writeToFile: " + e.toString());
        }
    }

    public ArrayList<ProductClass> readFromFile(Context context){//Läser listan med produkter från textfilen
        ArrayList<ProductClass> list = new ArrayList<ProductClass>();

        try{
            FileInputStream fis = context.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);

            list = (ArrayList<ProductClass>) ois.readObject();
            ois.close();
            fis.close();
        }catch(Exception e){
            Log.e("DataManager", "readFromFile: " + e.toString());

        }

        return list;
    }

}