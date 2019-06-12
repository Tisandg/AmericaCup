package com.example.tisandg.americacup2019;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tisandg.americacup2019.Database.DatabaseAmericaCupAccesor;
import com.example.tisandg.americacup2019.Entities.Team;
import com.example.tisandg.americacup2019.Fragments.ListGroupsFragment;
import com.example.tisandg.americacup2019.Fragments.MatchesFragment;

import java.util.ArrayList;
import java.util.List;

public class TeamDeatilActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton btnBack, btnFavorite;
    private ImageView image;
    private TextView txtName;

    //Fragments
    private MatchesFragment matchesFragment;
    private ListGroupsFragment groupFragment;
    private ViewPagerAdapter VPAdapter;

    private String TAG = "TeamDetail";
    private Team currentTeam;

    //Intent data
    private int idTeam, idGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_deatil);

        Toolbar toolbar = findViewById(R.id.team_toolbar);
        setSupportActionBar(toolbar);

        getTeam();

        idTeam = getIntent().getExtras().getInt(getString(R.string.field_id_team));
        idGroup = getIntent().getExtras().getInt(getString(R.string.field_id_group));

        Log.d(TAG,"id of current team: "+idTeam);

        btnFavorite = findViewById(R.id.icon_favorite);
        btnBack = findViewById(R.id.icon_back);
        image = findViewById(R.id.img_shield_team);
        txtName = findViewById(R.id.name_team);

        btnBack.setOnClickListener(this);
        btnFavorite.setOnClickListener(this);

        List<Fragment> fragments = new ArrayList<Fragment>();
        matchesFragment = new MatchesFragment();
        matchesFragment.setEquipoSeleccionado(idTeam);

        groupFragment = new ListGroupsFragment();
        groupFragment.setGrupoSeleccionado(idGroup);

        fragments.add(matchesFragment);
        fragments.add(groupFragment);

        VPAdapter = new ViewPagerAdapter(fragments, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager_team_detail);
        viewPager.setAdapter(VPAdapter);

        TabLayout tabs = findViewById(R.id.tabs_teamDetail);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icon_back:
                onBackPressed();
                break;

            case R.id.icon_favorite:
                cambiarFavorito();
                break;
        }
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
