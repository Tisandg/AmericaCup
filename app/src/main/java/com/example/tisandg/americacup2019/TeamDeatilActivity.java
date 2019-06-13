package com.example.tisandg.americacup2019;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tisandg.americacup2019.Database.DatabaseAmericaCupAccesor;
import com.example.tisandg.americacup2019.Entities.Match;
import com.example.tisandg.americacup2019.Entities.Team;
import com.example.tisandg.americacup2019.Recycler.AdapterRecycler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class TeamDeatilActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton btnBack, btnFavorite;
    private ImageView image;
    private TextView txtName;
    private LinearLayout container;

    List<Match> listData;
    AdapterRecycler adapter;
    RecyclerView mRecyclerView;

    //Fragments
    //private MatchesFragment matchesFragment;

    private String TAG = "TeamDetail";
    private Team currentTeam;

    //Intent data
    private int idTeam, idGroup;

    private boolean cambioFavorito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_deatil);

        Toolbar toolbar = findViewById(R.id.team_toolbar);
        setSupportActionBar(toolbar);

        cambioFavorito = false;

        idTeam = getIntent().getExtras().getInt(getString(R.string.field_id_team));
        idGroup = getIntent().getExtras().getInt(getString(R.string.field_id_group));

        getTeam();

        Log.d(TAG,"id of current team: "+idTeam);

        btnFavorite = findViewById(R.id.icon_favorite);
        btnBack = findViewById(R.id.icon_back);
        image = findViewById(R.id.img_shield_team);
        txtName = findViewById(R.id.name_team);
        container = findViewById(R.id.contenedorTeamDetail);

        btnBack.setOnClickListener(this);
        btnFavorite.setOnClickListener(this);

        listData = new ArrayList<Match>();

        //List<Fragment> fragments = new ArrayList<Fragment>();
        /*matchesFragment = new MatchesFragment();
        matchesFragment.setEquipoSeleccionado(idTeam);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.contenedorTeamDetail, matchesFragment);
        ft.commit();*/

        //Para el RecyclerView
        mRecyclerView = findViewById(R.id.recyclerView_matches_team);

        //listData = fillMatches();
        adapter = new AdapterRecycler(this,listData);
        adapter.setListener(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);

        getMatchs();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //VPAdapter.updateFragment(1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icon_back:
                Intent regresoMain = new Intent(this, MainActivity.class);
                if(cambioFavorito) {
                    setResult(RESULT_OK, regresoMain);
                }else{
                    setResult(RESULT_CANCELED, regresoMain);
                }
                onBackPressed();
                break;

            case R.id.icon_favorite:
                cambiarFavorito();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d("check", "Actividad teamDeatil eliminada");
        finish();
    }

    public void getMatchs() {
        class GetMatchs extends AsyncTask<Void, Void, List<Match>> {

            @Override
            protected List<Match> doInBackground(Void... voids) {
                List<Match> matches;
                matches = DatabaseAmericaCupAccesor.getInstance(TeamDeatilActivity.this).matchDAO().findByTeam(idTeam);
                Log.d(TAG,"Numero matches en fragment: "+matches.size());
                return matches;
            }

            @Override
            protected void onPostExecute(List<Match> matches) {
                super.onPostExecute(matches);
                Log.d(TAG,"Actualizando adapter");
                listData = new ArrayList<Match>();
                listData = matches;
                ordenarPorFecha();
                adapter.setListMatches(listData);
                adapter.notifyDataSetChanged();
            }
        }
        GetMatchs getMatchs = new GetMatchs();
        getMatchs.execute();
    }

    public void ordenarPorFecha(){
        List<Match> datos = new ArrayList<Match>();
        List<Date> fechasSinOrdenar = new ArrayList<Date>();

        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", new Locale("es"));
        parser.setTimeZone(TimeZone.getTimeZone("GMT-5"));
        int i = 0, j;
        for (i = 0 ; i<listData.size();i++){
            Match m = listData.get(i);
            String fecha = m.getMatch_date()+" "+m.getMatch_hour();
            try {
                Date date = parser.parse(fecha);
                fechasSinOrdenar.add(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        List<Date> fechasOrdenadas = fechasSinOrdenar;
        Log.d(TAG,"Fechas sin ordenar :"+fechasSinOrdenar.size());

        //Order dates
        Collections.sort(fechasOrdenadas);

        SimpleDateFormat formatterDate = new SimpleDateFormat("EEEE, dd MMM", new Locale("es"));
        formatterDate.setTimeZone(TimeZone.getTimeZone("GMT-5"));

        ArrayList<Integer> listSelected = new ArrayList<Integer>();

        for(i = 0 ; i<fechasOrdenadas.size() ; i++){
            for(j = 0 ; j<fechasSinOrdenar.size() ; j++){
                if(fechasOrdenadas.get(i).compareTo(fechasSinOrdenar.get(j)) == 0){
                    if(!listSelected.contains(j)){
                        String date = formatterDate.format(fechasSinOrdenar.get(j));
                        listData.get(j).setMatch_date(date);
                        datos.add(listData.get(j));
                        listSelected.add(j);
                        break;
                    }
                }
            }
        }
        listData = datos;
        Log.d(TAG,"Lista datos: "+listData.size());
    }

    public void cambiarFavorito(){
        class updateTeam extends AsyncTask<Void, Void, Boolean> {
            @Override
            protected Boolean doInBackground(Void... voids) {
                //adding to database
                if(currentTeam.isFavorite()){
                    currentTeam.setFavorite(false);
                }else{
                    currentTeam.setFavorite(true);
                }
                cambioFavorito = true;
                DatabaseAmericaCupAccesor.getInstance(getApplication()).teamDAO().insert(currentTeam);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean bol) {
                super.onPostExecute(bol);
                Log.d(TAG,"Ahora es un equipo favorito");
                String message;
                if(currentTeam.isFavorite()) {
                    message = getString(R.string.msg_favorite_added);
                }else{
                    message = getString(R.string.msg_favorite_deleted);
                }
                Toast.makeText(TeamDeatilActivity.this, message, Toast.LENGTH_SHORT).show();
                changeStar();
            }

        }
        updateTeam update = new updateTeam();
        update.execute();
    }

    public void getTeam(){
        class GetTeam extends AsyncTask<Void, Void, Boolean> {
            @Override
            protected Boolean doInBackground(Void... voids) {
                //adding to database
                currentTeam = DatabaseAmericaCupAccesor.getInstance(getApplication()).teamDAO().findById(idTeam);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean bol) {
                super.onPostExecute(bol);
                txtName.setText(currentTeam.getTeam_name());
                changeStar();
            }
        }
        GetTeam get = new GetTeam();
        get.execute();
    }

    public void changeStar(){
        if(currentTeam.isFavorite()){
            btnFavorite.setBackground(getDrawable(R.drawable.ic_action_with_star));
        }else{
            btnFavorite.setBackground(getDrawable(R.drawable.ic_action_without_star));
        }
    }



}
