package com.example.tisandg.americacup2019;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

public class MatchActivity extends AppCompatActivity {

    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        Log.d(TAG,"Creado activity");
        Toolbar toolbar = findViewById(R.id.match_toolbar);
        toolbar.setTitle("Colombia vs Peru");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return false;
    }
}
