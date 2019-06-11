package com.example.tisandg.americacup2019;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tisandg.americacup2019.Database.DatabaseAmericaCupAccesor;
import com.example.tisandg.americacup2019.Entities.Match;

public class MatchActivity extends AppCompatActivity {

    String TAG = "MainActivity";
    private int idMatch;
    private Match matchCurrent;

    //Items of Layout
    private TextView nameA,nameB, score, status;
    private ImageView imgShieldA, imgShieldB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        nameA = findViewById(R.id.nameA);
        nameB = findViewById(R.id.nameB);
        score = findViewById(R.id.score);
        status = findViewById(R.id.status);
        imgShieldA = findViewById(R.id.shield_team_a);
        imgShieldB = findViewById(R.id.shield_team_b);

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
        class SaveMatches extends AsyncTask<Void, Void, Boolean> {
            @Override
            protected Boolean doInBackground(Void... voids) {
                //adding to database
                matchCurrent = DatabaseAmericaCupAccesor.getInstance(getApplication()).matchDAO().findById(idMatch);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean bol) {
                super.onPostExecute(bol);
                String title = matchCurrent.getTeamA()+" Vs "+matchCurrent.getTeamB();
                getSupportActionBar().setTitle(title);
                nameA.setText(matchCurrent.getTeamA());
                nameB.setText(matchCurrent.getTeamB());
                status.setText(matchCurrent.getStatus());
            }
        }

        SaveMatches save = new SaveMatches();
        save.execute();
    }
}
