package com.example.tisandg.americacup2019;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tisandg.americacup2019.Database.DatabaseAmericaCupAccesor;
import com.example.tisandg.americacup2019.Entities.Match;
import com.example.tisandg.americacup2019.Fragments.FavoritesFragment;
import com.example.tisandg.americacup2019.Fragments.ListGroupsFragment;
import com.example.tisandg.americacup2019.Fragments.MatchesFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements MatchesFragment.ComunicationToActivity {

    //Defines the remove option for the context menu
    private static final int LECTURA_QR = Menu.FIRST;
    private boolean addingNew = true;
    private String TAG = "MainActivity";
    //Donde se van colocando almacenando las peticiones
    private RequestQueue cola;
    private ArrayList<Match> matches;

    private ViewPagerAdapter VPAdapter;
    private final int numberGroups = 3;
    private boolean agregadosA= false, agregadosB = false, agregadosC = false;

    //Fragments
    private MatchesFragment matchesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializar cola
        cola = Volley.newRequestQueue(this);

        matches = new ArrayList<Match>();

        FixturesGroup(getString(R.string.groupA), 1);
        FixturesGroup(getString(R.string.groupB), 2);
        FixturesGroup(getString(R.string.groupC), 3);

        //Fragments for tabs
        List<Fragment> fragments = new ArrayList<Fragment>();
        //if(savedInstanceState==null) {
        matchesFragment = new MatchesFragment();
        //Send the reference of this activity because this implement the interface
        matchesFragment.setCallback(this);
        ListGroupsFragment groups = new ListGroupsFragment();
        FavoritesFragment favorites = new FavoritesFragment();
        fragments.add(matchesFragment);
        fragments.add(groups);
        fragments.add(favorites);


        VPAdapter = new ViewPagerAdapter(fragments, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(VPAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        Log.d(TAG,"Fragment[0]: "+VPAdapter.getItem(0).toString());
        Log.d(TAG,"Fragment[1]: "+VPAdapter.getItem(1).toString());
    }

    //Prepare the menu before display
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        //int idx = myListView.getSelectedItemPosition();
        //If addingNew flag is set, user is adding a task so cancel option is displayed; otherwise, remove option is available
        String removeTitle = getString(addingNew ? R.string.cancel : R.string.action_qr_code);
        MenuItem camaraItem = menu.findItem(R.id.action_qr);
        camaraItem.setTitle(removeTitle);
        //Cancel option is displayed if user is adding a task and remove option is displayed if list has at least one element
        camaraItem.setVisible(addingNew);
        return true;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle(R.string.menu_title);
        menu.add(0, LECTURA_QR, Menu.NONE, R.string.action_qr_code);
    }

    //Create the options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    /**
     * Watch the MatchDAO which the user have choosed
     * @param idMatch
     */
    @Override
    public void watchMath(int idMatch) {
        Intent goToMatchActivty = new Intent(MainActivity.this, MatchActivity.class);
        goToMatchActivty.putExtra(getString(R.string.id_match),idMatch);
        startActivity(goToMatchActivty);
    }

    /**
     * To obtain fixtures of a group
     * League 962 for group A
     * League 961 for group B
     * League 960 for group C
     * @param league
     * @param idGroup
     */
    //Horario adelantado 6 horas
    public void FixturesGroup(String league,final int idGroup){
        String URLFixturesA = "https://livescore-api.com/api-client/fixtures/matches.json?key="+
                getString(R.string.api_key)+"&secret="+getString(R.string.api_secret)+"&league="+league;
        //Preparamos la peticion
        JsonObjectRequest peticion = new JsonObjectRequest(Request.Method.GET, URLFixturesA,null,  new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response){
                try {
                    ArrayList<Match> matchesGroup = new ArrayList<>();
                    JSONObject objData = response.getJSONObject("data");

                    //get Fixtures
                    JSONArray fixtures = objData.getJSONArray("fixtures");
                    int numFixtures = fixtures.length();
                    int i = 0;
                    for(i = 0; i<numFixtures; i++){
                        Log.d(TAG,"Respuesta fixture: "+fixtures.get(i).toString());
                        JSONObject fixture = (JSONObject) fixtures.get(i);

                        //Obtain data from fixture
                        int id = fixture.getInt("id");

                        String date = fixture.getString("date");
                        String time = fixture.getString("time");
                        //convert to current time zone
                        DateFormat formatDate;
                        Date dateConverted = new Date();
                        String dateConvertida = "";
                        try {
                            String dateTime = date+" "+time;
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
                            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                            Date dateAux = sdf.parse(dateTime);
                            sdf.setTimeZone(TimeZone.getTimeZone("GMT-6"));
                            dateConvertida = sdf.format(dateAux);

                            SimpleDateFormat formateador = new SimpleDateFormat("EEE, MMM dd");
                            date = formateador.parse()
                            //dateConverted = sdf.format(dateTime);
                        }catch (Exception e){
                            Log.d(TAG,"Excepcion al convertir date: "+e.getMessage());
                        }

                        String teamA = fixture.getString("home_name");
                        int teamAid = fixture.getInt("home_id");

                        String score="", status="";
                        try{
                            score = fixture.getString("score");
                            status = fixture.getString("status");
                        }catch (JSONException e){
                            Log.d(TAG,"Excepcion al obtener score y status");
                        }

                        String teamB = fixture.getString("away_name");
                        int teamBid = fixture.getInt("away_id");
                        String location = fixture.getString("location");
                        Match match = new Match(id, teamAid, teamA, teamBid, teamB, dateConvertida, time, location, idGroup);

                        match.setScore("");
                        if(status.equals("")) { match.setStatus(getString(R.string.NOT_STARTED)); }
                        else{  match.setStatus(status); }

                        matchesGroup.add(match);
                    }
                    switch (idGroup){
                        case 1:
                            agregadosA = true;
                            break;
                        case 2:
                            agregadosB = true;
                            break;
                        case 3:
                            agregadosC = true;
                            break;
                    }
                    //Register in BD
                    saveMatches(matchesGroup);
                    
                } catch (JSONException e) {
                    Log.d(TAG,"Excepcion: "+e.getMessage());
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Error
                Log. d(TAG, "Error al realizar peticion: "+error.getMessage());
            }
        });
        cola.add(peticion);
    }

    private void saveMatches(final ArrayList<Match> matchesGroup) {
        class SaveMatches extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                //adding to database
                DatabaseAmericaCupAccesor.getInstance(getApplication()).matchDAO().insert(matchesGroup);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Log.d(TAG, "Matches saved: "+matchesGroup.size());
                if(agregadosA && agregadosB && agregadosC){
                    VPAdapter.updateFragment(0);
                }

            }
        }
        SaveMatches save = new SaveMatches();
        save.execute();
    }

}