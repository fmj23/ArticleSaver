package com.group15.articlesaver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SampleArticleList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_article_list);

        // In the future, all cards in list can be accessed and then perform the same click action with their parameters
        // Get the Sub-Sample Card View
        CardView sample1 = (CardView) findViewById(R.id.subSample1);
        sample1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SampleArticleList.this, MainActivity.class));
            }
        });

        // UI Button for going back to main activity
        Button backBtnSAL = (Button) findViewById(R.id.backButtonSAL);
        backBtnSAL.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.i("MyApp", "User pressed Back to main");
                // Launch Links activity
                //startActivity(new Intent(SavedArticles.this, MainActivity.class));
                finish();
            }
        });

        // Get the Sub-Sample Card View
        CardView sample2 = (CardView) findViewById(R.id.subSample2);
        sample2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SampleArticleList.this, MainActivity.class));
            }
        });
    }
}