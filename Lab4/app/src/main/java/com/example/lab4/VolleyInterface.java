package com.example.lab4;


import org.json.JSONObject;

public interface VolleyInterface <T>{
    public void onSuccess(JSONObject object);
    public void onFailure(Exception e);
}
