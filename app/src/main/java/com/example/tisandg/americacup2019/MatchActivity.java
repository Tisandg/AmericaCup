package com.example.tisandg.americacup2019;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tisandg.americacup2019.Database.DatabaseAmericaCupAccesor;
import com.example.tisandg.americacup2019.Entities.Match;

public class MatchActivity extends AppCompatActivity implements View.OnClickListener{

    String TAG = "MainActivity";
    private int idMatch;
    private Match matchCurrent;

    //Items of Layout
    private TextView nameA,nameB, score, status;
    private ImageView imgShieldA, imgShieldB;
    private ImageView imgQR;

    private static final int CODIGO_QR = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        nameA = findViewById(R.id.nameA);
        nameB = findViewById(R.id.nameB);
        score = findViewById(R.id.score);
        status = findViewById(R.id.status);
        imgShieldA = findViewById(R.id.img_shield_team_a);
        imgShieldB = findViewById(R.id.img_shield_team_b);
        imgQR = findViewById(R.id.icon_qrcode);
        imgShieldA.setOnClickListener(this);
        imgShieldB.setOnClickListener(this);
        imgQR.setOnClickListener(this);

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

    private void readQRCode() {
        Intent goReader = new Intent(MatchActivity.this, LectorQRActivity.class);
        startActivityForResult(goReader, CODIGO_QR);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        //Revisamos que actividad dio resultado
        switch (requestCode){
            case CODIGO_QR:
                if(resultCode == RESULT_OK){
                    String codigoQR = data.getStringExtra("Data");
                    Toast.makeText(this, "Codigo TAG leido :"+codigoQR, Toast.LENGTH_SHORT).show();
                }else{
                    Log.d(TAG, "No se recibio codigo qr de la actividad");
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icon_qrcode:
                readQRCode();
                break;
            case R.id.img_shield_team_a:
                goTeamDeatil(0);
                break;
            case R.id.img_shield_team_b:
                goTeamDeatil(1);
                break;
        }
    }

    public void goTeamDeatil(int team){
        int id = 0;
        if(team == 0){
            id = matchCurrent.getMatch_id_teamA();
        }else{
            id = matchCurrent.getMatch_id_teamB();
        }
        Intent intent = new Intent(MatchActivity.this, TeamDeatilActivity.class);
        intent.putExtra(getString(R.string.field_id_team), id);
        startActivity(intent);
    }
}
