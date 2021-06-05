package com.group15.articlesaver;

import android.app.Activity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class ScrapeVolley {
    private static final String TAG = "ScrapeVolley";
    public static void scrape(Activity main){
        // Instantiate the RequestQueue
        RequestQueue queue = Volley.newRequestQueue(main);
        String param = "Apple";
        String url = "http://flip1.engr.oregonstate.edu:7043/?query="+param;

        // Request a string response from the URL
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display first 500 characters of response
                        Log.d(TAG, "onResponse: " + response.substring(0, 500));
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Log.d(TAG, "onErrorResponse: No worky!");
            }
        });

        queue.add(stringRequest);
    }
}
