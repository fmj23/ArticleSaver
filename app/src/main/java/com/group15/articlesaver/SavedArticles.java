package com.group15.articlesaver;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SavedArticles extends AppCompatActivity {

    LinearLayout linearList;
    Button makeCat;
    TextView newFav;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        linearList = (LinearLayout) findViewById(R.id.linearView1);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_articles);

        // UI Button for going back to main activity
        Button backBtn2 = (Button) findViewById(R.id.backButton2);
        backBtn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.i("MyApp", "User pressed Back to main");
                // Launch Main Activity
                //startActivity(new Intent(SavedArticles.this, MainActivity.class));
                finish();
            }
        });

        // UI Button for going back to main activity
        makeCat = (Button) findViewById(R.id.makeNewCategory);
        makeCat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.i("MyApp", "User pressed Create new category");
                // Do work for card view
                addView();
            }
        });
    }

    public void addView() {
        Log.i("MyApp", "Adding new favorite");
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.linearView1);
        newFav = new TextView(this);
        newFav.setText("Favorite " + count);
        newFav.setPadding(8, 4, 8, 0);
        count++;

        LayoutInflater vi = (LayoutInflater) getLayoutInflater();
        View v = vi.inflate(R.layout.adder, null);

        ViewGroup insertPoint = (ViewGroup) findViewById(R.id.linearView1);
        insertPoint.addView(v, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        //mainLayout.addView(newFav);
    }
}