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
import com.example.tisandg.americacup2019.Recycler.AdapterRecycler;

public class MatchActivity extends AppCompatActivity implements View.OnClickListener{

    String TAG = "Match";
    private int idMatch;
    private Match matchCurrent;

    //Items of Layout
    private TextView nameA,nameB, score, status;
    private ImageView imgShieldA, imgShieldB;
    private ImageView shieldA2, shieldB2;
    private ImageView imgQR;

    private static final int CODIGO_QR = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        Log.d(TAG,"OnCreate");
        nameA = findViewById(R.id.nameA);
        nameB = findViewById(R.id.nameB);
        score = findViewById(R.id.score);
        status = findViewById(R.id.status);
        imgShieldA = findViewById(R.id.img_shield_team_a);
        imgShieldB = findViewById(R.id.img_shield_team_b);

        shieldA2 = findViewById(R.id.img_shield_team_a_2);
        shieldB2 = findViewById(R.id.img_shield_team_b_2);

        //imgQR = findViewById(R.id.icon_qrcode);

        imgShieldA.setOnClickListener(this);
        imgShieldB.setOnClickListener(this);
        //imgQR.setOnClickListener(this);

        idMatch = getIntent().getIntExtra(getString(R.string.id_match),0);


        Log.d(TAG,"Creado activity");
        Toolbar toolbar = findViewById(R.id.match_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"OnResume");
        if(idMatch != 0){
            getMatch();
        }
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
        class getMatch extends AsyncTask<Void, Void, Boolean> {
            @Override
            protected Boolean doInBackground(Void... voids) {
                //adding to database
                matchCurrent = DatabaseAmericaCupAccesor.getInstance(getApplication()).matchDAO().findById(idMatch);
                Log.d(TAG,"Obtienendo match con id: "+idMatch);
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

                int drawableTeamA = AdapterRecycler.drawable(matchCurrent.getTeamA());
                int drawableTeamB = AdapterRecycler.drawable(matchCurrent.getTeamB());
                imgShieldA.setBackground(getDrawable(drawableTeamA));
                imgShieldB.setBackground(getDrawable(drawableTeamB));
                shieldA2.setBackground(getDrawable(drawableTeamA));
                shieldB2.setBackground(getDrawable(drawableTeamB));
                Log.d(TAG,"Valores colocados");
            }
        }

        getMatch get = new getMatch();
        get.execute();
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
        //String name;
        if(team == 0){
            id = matchCurrent.getMatch_id_teamA();
            Log.d(TAG,"Ver equipo["+matchCurrent.getTeamA()+"]");
            //name = matchCurrent.getTeamA();
        }else{
            id = matchCurrent.getMatch_id_teamB();
            Log.d(TAG,"Ver equipo["+matchCurrent.getTeamB()+"]");
            //name = matchCurrent.getTeamB();
        }
        Intent intent = new Intent(MatchActivity.this, TeamDeatilActivity.class);
        intent.putExtra(getString(R.string.field_id_team), id);
        intent.putExtra(getString(R.string.field_id_group), matchCurrent.getGroup_id());
        //intent.putExtra(getString(R.string.field_name_team), name);
        startActivity(intent);
    }
}
