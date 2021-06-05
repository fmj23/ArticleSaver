package com.group15.articlesaver;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Scraper {
    private static final String TAG = "Scraper";
    public static String grabArticle(String article) throws IOException {
        // Variable for article title (expected to change with user input)
        //String article = "Banana";

        // GET Request from scraper service-----------------------------------------------------------------------------
        // Path to the HTML Scraper Service
        Log.d(TAG, "grabArticle: --START--");
        String scraperPath = "http://flip1.engr.oregonstate.edu:7043/";
        String charset = "UTF-8";
        HttpURLConnection connection = (HttpURLConnection) new URL(scraperPath + "?query=" + article).openConnection();

        connection.setRequestProperty("Accept-Charset", charset);
        InputStream response = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(response);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        //connection.disconnect();
        // Status code from HTML Scraper Service

        //Log.d(TAG, "grabArticle: " + response.toString());

        // Now the POST to JSON transformer-----------------------------------------------------------------------------

        // Path to the JSON Transformer Service
        Log.d(TAG, "grabArticle: --START JSON--");
        String transformerPath = "https://cs361-microservice.wl.r.appspot.com/";
        HttpURLConnection postConnection = (HttpURLConnection) new URL(transformerPath).openConnection();
        postConnection.setRequestMethod("POST");
        postConnection.setRequestProperty("Content-Type", "text/html");
        postConnection.setRequestProperty("Accept","application/json");
        postConnection.setDoOutput(true);
        Log.d(TAG, "grabArticle: OUTPUT");
        try(OutputStream os = postConnection.getOutputStream()){
            //byte[] input = response.readAllBytes();
            //os.write(input, 0, input.length);
            byte bytes[] = new byte[(int) response.available()];

            DataInputStream dis = new DataInputStream(response);
            dis.readFully(bytes);
            os.write(bytes, 0, bytes.length);
        }
        Log.d(TAG, "grabArticle: STRING BUILDER");
        StringBuilder response2 = new StringBuilder();
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(postConnection.getInputStream(),"utf-8")
        )){
            //StringBuilder response2 = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null){
                response2.append(responseLine.trim());
            }
            //System.out.println(response2.toString());
        }
        connection.disconnect();
        postConnection.disconnect();
        Log.d(TAG, "grabArticle: " + response2.toString());

        String test = "";
        return test;
    }
}
