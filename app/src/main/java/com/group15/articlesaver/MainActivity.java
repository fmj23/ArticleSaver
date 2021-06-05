package com.group15.articlesaver;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    
    private EditText articleArg;
    private TextView articleText;
    private TextView articleTitle;
    private Boolean inArticleArg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Edit Text Functionality -----------------------------------------------------------------
        // Article arg = what the user enters in the edit text widget
        articleArg = (EditText) findViewById(R.id.editTextArticle);
        // Search button = the button clicked to search
        Button searchButton = (Button) findViewById(R.id.searchButton);
        // The article text block for the output
        articleText = (TextView) findViewById(R.id.articleText);
        articleText.setText("");
        articleText.setMovementMethod(new ScrollingMovementMethod());
        
        // Set the on click listener for the search button
        View.OnClickListener searchOnClickListener = v -> {
            String result = articleArg.getText().toString();
            result = result + "\n";
            //articleText.append(result);
            Log.i("MyApp","Searched for " + result);
            //articleArg.clearFocus();
            Manager.hideKeyboard(this);
            articleArg.clearFocus();
            articleArg.setText("");
            //Scraper.grabArticle();
            Log.d(TAG, "onCreate: starting Asynctask");

            // Function to execute search (was duplicate code)
            executeSearch(result);

            //DownloadData getArticle = new DownloadData();
            //getArticle.execute(result);
            Log.d(TAG, "onCreate: done");
        };
        // Search button 'onClick' sets focus to true for the associated 'editText'
        searchButton.setOnClickListener(searchOnClickListener);

        articleArg.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    Log.i("MyApp", "Focus Logic");
                    inArticleArg = true;
                }
            }
        });

        // Listener for enter key?
        articleArg.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch(keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            Log.i("MyApp","User did enter key");
                            String result = articleArg.getText().toString();
                            //result = result + "\n";
                            //articleText.append(result);
                            Log.i("MyApp","Searched for " + result);
                            Log.d(TAG, "onCreate: starting Asynctask");

                            // New function for duplicate code
                            executeSearch(result);

                            //DownloadData getArticle = new DownloadData();
                            //getArticle.execute(result);
                            Log.d(TAG, "onCreate: done");
                            //articleArg.clearFocus();
                            Manager.hideKeyboard(MainActivity.this);
                            articleArg.setText("");
                            articleArg.clearFocus();
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        // -----------------------------------------------------------------------------------------
        // UI Button for generating a random article
        Button articleBtn = (Button) findViewById(R.id.newArticle);
        articleBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.i("MyApp", "User pressed for New Article");
                //Toast.makeText(MainActivity.this, "Clicked for new article (appears here)", Toast.LENGTH_LONG).show();
                String returnedArticle = "";
                returnedArticle = RandomArticle.RandomArticleName();

                // Execute the article search (Third instance of duplicate code)
                executeSearch(returnedArticle);
            }
        });

        // UI Button for saving articles
        Button saveArticleBtn = (Button) findViewById(R.id.savedArticleBtn);
        saveArticleBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.i("MyApp", "User pressed for Save Article");
                // Launch Links activity
                startActivity(new Intent(MainActivity.this, SavedArticles.class));
            }
        });

        // UI Button for saved links page
        Button saveLinkBtn = (Button) findViewById(R.id.savedLinksBtn);
        saveLinkBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.i("MyApp", "User pressed for Save Link");
                // Launch Links activity
                startActivity(new Intent(MainActivity.this, Links.class));
            }
        });

        // Interaction for floating save button
        FloatingActionButton saveArticle = (FloatingActionButton) findViewById(R.id.saveButton);
        // Connect floating save button to create category activity
        saveArticle.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               // Launch the categories activity
               startActivity(new Intent(MainActivity.this, CreateCategory.class));
           }
       });

        // https://stackoverflow.com/questions/2115758/how-do-i-display-an-alert-dialog-on-android
        // Implement a dialog to give new users a brief introduction
        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
        builder1.setMessage("Start by searching for a topic in the search bar at the top, or click 'new article' below to see another article.");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

        // show The Image in a ImageView

    }

    private class DownloadData extends AsyncTask<String, Void, ArrayList<String>>{
        @Override
        protected void onPostExecute(ArrayList<String> s) {
            //super.onPostExecute(s);
            super.onPostExecute(s);
            Log.d(TAG, "onPostExecute: parameter is " + s.get(1));

            // Filter out title
            String tempTitle = s.get(0);
            String tempArticle = s.get(1);

            articleText = (TextView) findViewById(R.id.articleText);
            articleText.setText("");
            //articleText.append(s);
            articleText.append(tempArticle);

            articleTitle = (TextView) findViewById(R.id.textViewTitle);
            articleTitle.setText("");
            articleTitle.append(tempTitle);
        }

        @Override
        protected ArrayList<String> doInBackground(String... strings) {
            Log.d(TAG, "doInBackground: starts with " + strings[0]);
            // Do in background logic

            //String articleResultText = strings[0];
            ArrayList<String> allResultText = new ArrayList<String>();
            String articleResultText = "";
            Log.d(TAG, "doInBackground: --SCRAPE--");
            //articleResultText = ScrapeArticle.Scrape(strings[0]);
            allResultText = ScrapeArticle.Scrape(strings[0]);
            articleResultText = allResultText.get(1);

            //articleResultText += "_result";

            //return articleResultText;
            return allResultText;
        }
    }
    public void executeSearch(String articleName)
    {
        DownloadData getArticle = new DownloadData();
        getArticle.execute(articleName);
    }
}