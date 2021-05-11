package com.group15.articlesaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Links extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_links);

        // UI Button for going back to main activity
        Button backBtn = (Button) findViewById(R.id.backbutton);
        backBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.i("MyApp", "User pressed back to main");
                // Launch Links activity
                startActivity(new Intent(Links.this, MainActivity.class));
                finish();
            }
        });

        // Clickable link in card view
        TextView link1 = (TextView) findViewById(R.id.link1);
        link1.setMovementMethod(LinkMovementMethod.getInstance());
    }
}