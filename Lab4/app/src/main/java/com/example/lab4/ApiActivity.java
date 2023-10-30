package com.example.lab4;

import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.JsonObjectRequest;

        import org.json.JSONObject;

//Denna kod kommer från Alexanders föreläsning
public class ApiActivity {

    public void get(RequestQueue requestQueue, VolleyInterface callback, String url){ //metod för att göra en GET-förfrågan till vårt JSON-API
        JsonObjectRequest jsonObjectRequest;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) { //om svaret tas emot från APIet så anropas onSuccess-metoden i callback-objektet och skicka med JSON-svaret
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            //Om det uppstår ett fel vid förfrågan så hamnar vi här.
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailure(error);
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}