package com.example.tisandg.americacup2019;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.tisandg.americacup2019.Database.DatabaseAmericaCupAccesor;
import com.example.tisandg.americacup2019.Entities.Match;

public class MatchActivity extends AppCompatActivity {

    String TAG = "MainActivity";
    private int idMatch;
    private Match matchCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        idMatch = getIntent().getIntExtra(getString(R.string.id_match),0);
        if(idMatch != 0){
            getMatch();
        }

        Log.d(TAG,"Creado activity");
        Toolbar toolbar = findViewById(R.id.match_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return false;
    }

    public void getMatch(){
        class SaveMatches extends AsyncTask<Void, Void, Match> {
            @Override
            protected Match doInBackground(Void... voids) {
                //adding to database
                Match match = DatabaseAmericaCupAccesor.getInstance(getApplication()).matchDAO().findById(idMatch);
                return match;
            }

            @Override
            protected void onPostExecute(Match match) {
                super.onPostExecute(match);
                matchCurrent = match;
                String title = match.getTeamA()+" Vs "+match.getTeamB();
                getSupportActionBar().setTitle(title);
            }
        }

        SaveMatches save = new SaveMatches();
        save.execute();
    }
}
