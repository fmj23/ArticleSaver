package com.group15.articlesaver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SavedArticles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_articles);

        // UI Button for going back to main activity
        Button backBtn2 = (Button) findViewById(R.id.backButton2);
        backBtn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.i("MyApp", "User pressed Back to main");
                // Launch Links activity
                //startActivity(new Intent(SavedArticles.this, MainActivity.class));
                finish();
            }
        });

        // Get the Sample Card View
        CardView sample = (CardView) findViewById(R.id.cardview1);
        sample.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  startActivity(new Intent(SavedArticles.this, SampleArticleList.class));
              }
        });
    }
}