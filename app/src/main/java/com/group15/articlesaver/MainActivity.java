package com.group15.articlesaver;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI Button for generating a random article
        Button articleBtn = (Button) findViewById(R.id.newArticle);
        articleBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.i("MyApp", "User pressed for New Article");
                Toast.makeText(MainActivity.this, "Clicked for new article (appears here)", Toast.LENGTH_LONG).show();
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

    }
}