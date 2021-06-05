// https://blog.codavel.com/how-to-integrate-httpurlconnection
// https://www.baeldung.com/java-org-json

package com.group15.articlesaver;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class ScrapeArticle {

    private static final String TAG = "ScrapeArticle";

    public static ArrayList<String> Scrape(String articleTitle){
        HttpURLConnection urlConnection = null;
        //String arg = "Apple";
        String returnJSON = "";
        String returnTitle = "";
        String result = "";
        String returnString = "Not Found";
        try{
            URL url = new URL("http://flip1.engr.oregonstate.edu:7043/?query="+articleTitle);
            urlConnection = (HttpURLConnection) url.openConnection();

            int code = urlConnection.getResponseCode();
            if(code != 200)
            {
                throw new IOException("Invalid response from server: " + code);
            }

            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()
            ));
            String line;
            while((line = rd.readLine()) != null)
            {
                //Log.d(TAG, "Scrape: " + line);
                result += (line+'\n');
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(urlConnection != null){
                urlConnection.disconnect();
                Log.d(TAG, "Scrape: Disconnected URL");
            }
        }
        //Log.d(TAG, "Scrape: " + result);
        Log.d(TAG, "Scrape: HTML Done");
        // POST now to send the above response to the JSON transformer service
        HttpURLConnection postConnection = null;
        String jsonResponse = "";
        try{
            JSONObject postData = new JSONObject();

            URL postUrl = new URL("https://cs361-microservice.wl.r.appspot.com/");
            postConnection = (HttpsURLConnection) postUrl.openConnection();
            postConnection.setRequestProperty("Content-Type", "text/html");
            postConnection.setRequestProperty("Accept", "application/json");
            postConnection.setRequestMethod("POST");
            postConnection.setDoOutput(true);
            postConnection.setDoInput(true);
            postConnection.setChunkedStreamingMode(0);

            OutputStream out = new BufferedOutputStream(postConnection.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    out, "UTF-8"
            ));
            writer.write(result);
            writer.flush();

            int code = postConnection.getResponseCode();
            if(code != 201)
            {
                throw new IOException("Invalid response from server: " + code);
            }

            BufferedReader rd2 = new BufferedReader(new InputStreamReader(
                    postConnection.getInputStream()
            ));

            String line2;
            while((line2 = rd2.readLine()) != null){
                Log.d(TAG, "Scrape: " + line2);
                jsonResponse += line2;
            }
            JSONObject finalResult = new JSONObject(jsonResponse);

            returnTitle = finalResult.get("title").toString();
            returnJSON = finalResult.get("text").toString();

            Log.d(TAG, "Scrape: JSON: "+finalResult.toString(4));
            returnString = finalResult.toString(4);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (postConnection != null){
                postConnection.disconnect();
                Log.d(TAG, "Scrape: Disconnected POST");
            }
        }

        Log.d(TAG, "Scrape: POST done");
        ArrayList<String> returnList = new ArrayList<String>();

        returnList.add(returnTitle);
        returnList.add(returnJSON);

        //return returnJSON;
        return returnList;
    }

}
