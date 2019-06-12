package com.example.tisandg.americacup2019;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tisandg.americacup2019.Database.DatabaseAmericaCupAccesor;
import com.example.tisandg.americacup2019.Entities.Match;
import com.example.tisandg.americacup2019.Entities.Team;
import com.example.tisandg.americacup2019.Entities.TeamGroup;
import com.example.tisandg.americacup2019.Fragments.FavoritesFragment;
import com.example.tisandg.americacup2019.Fragments.ListGroupsFragment;
import com.example.tisandg.americacup2019.Fragments.MatchesFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements MatchesFragment.ComunicationToActivity {

    //Defines the remove option for the context menu
    private String TAG = "MainActivity";
    //Donde se van colocando almacenando las peticiones
    private RequestQueue cola;
    private ArrayList<Match> matches;

    private ImageView img;

    private ViewPagerAdapter VPAdapter;
    private boolean agregadosA= false, agregadosB = false, agregadosC = false;
    private boolean groupA= false, groupB = false, groupC = false;

    private static final int CODIGO_QR = 1;
    private final int MY_PERMISSIONS_REQUEST_CAMERA = 2;

    private static int idGroupA = 1;
    private static int idGroupB = 2;
    private static int idGroupC = 3;

    private Map<Integer, String> teams;

    //Fragments
    private MatchesFragment matchesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializar cola
        cola = Volley.newRequestQueue(this);

        matches = new ArrayList<Match>();

        img = findViewById(R.id.icon_qrcode);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readQRCode();
            }
        });
        //CHECK INTERNET CONNECTION

        //Get information about fixtures of all groups
        getFixturesGroup(getString(R.string.groupA), idGroupA);
        getFixturesGroup(getString(R.string.groupB), idGroupB);
        getFixturesGroup(getString(R.string.groupC), idGroupC);

        //Get Standings
        getGroupInformation(getString(R.string.groupA), idGroupA);
        getGroupInformation(getString(R.string.groupB), idGroupB);
        getGroupInformation(getString(R.string.groupC), idGroupC);


        //Fragments for tabs
        List<Fragment> fragments = new ArrayList<Fragment>();
        //if(savedInstanceState==null) {
        matchesFragment = new MatchesFragment();
        //Send the reference of this activity because this implement the interface
        matchesFragment.setCallback(this);
        ListGroupsFragment groups = new ListGroupsFragment();
        groups.setGrupoSeleccionado(0);
        FavoritesFragment favorites = new FavoritesFragment();
        fragments.add(matchesFragment);
        fragments.add(groups);
        fragments.add(favorites);

        comprobarPermisoCamara();

        VPAdapter = new ViewPagerAdapter(fragments, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(VPAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    private void readQRCode() {
        Intent goReader = new Intent(MainActivity.this, LectorQRActivity.class);
        startActivityForResult(goReader, CODIGO_QR);
    }


    public void comprobarPermisoCamara() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            //Permisos concedidos
            //leerTag();
        }else{
            Log.d(TAG,"Solicitando permisos de acceso a la camara");
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_CAMERA);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissiones[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //leerTag();
                    Toast.makeText(this, "Permisos otorgados", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "No se puede ejecutar actividad", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Permiso de acceso a la camara ha sido denegada");
                }
                break;
        }
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
     * @param idGroupServer
     * @param idGroup
     */
    //Horario adelantado 6 horas
    public void getFixturesGroup(String idGroupServer, final int idGroup){
        String URLFixturesA = "https://livescore-api.com/api-client/fixtures/matches.json?key="+
                getString(R.string.api_key)+"&secret="+getString(R.string.api_secret)+"&league="+idGroupServer;
        //Preparamos la peticion
        JsonObjectRequest peticion = new JsonObjectRequest(Request.Method.GET, URLFixturesA,null,  new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response){
                try {
                    ArrayList<Match> matchesGroup = new ArrayList<>();
                    JSONObject objData = response.getJSONObject("data");
                    boolean success = response.getBoolean("success");
                    if(success){
                        //get Fixtures
                        teams = new HashMap<Integer, String>();

                        JSONArray fixtures = objData.getJSONArray("fixtures");
                        int numFixtures = fixtures.length();
                        int i = 0;
                        for(i = 0; i<numFixtures; i++){
                            JSONObject fixture = (JSONObject) fixtures.get(i);

                            //Obtain data from fixture
                            int id = fixture.getInt("id");
                            String teamA = fixture.getString("home_name");
                            int teamAid = fixture.getInt("home_id");
                            String teamB = fixture.getString("away_name");
                            int teamBid = fixture.getInt("away_id");

                            Log.d(TAG,"Fixture["+i+"]: "+teamA+" VS "+teamB);

                            if(!teams.containsKey(teamAid)){
                                teams.put(teamAid, teamA);
                            }
                            if(!teams.containsKey(teamBid)){
                                teams.put(teamBid, teamB);
                            }

                            String location = fixture.getString("location");

                            String date = fixture.getString("date");
                            String time = fixture.getString("time");

                            //convert to current time zone
                            String dateConverted = "";
                            String timeConverter = "";
                            Date dateFinal = new Date();

                            try {
                                String dateTime = date+" "+time;
                                SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
                                parser.setTimeZone(TimeZone.getTimeZone("UTC"));

                                SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", new Locale("es"));
                                //SimpleDateFormat formatterDate = new SimpleDateFormat("EEEE, dd MMM", new Locale("es"));
                                formatterDate.setTimeZone(TimeZone.getTimeZone("GMT-5"));
                                Date dateParsed = parser.parse(dateTime);
                                dateConverted = formatterDate.format(dateParsed);

                                SimpleDateFormat formatterTime = new SimpleDateFormat("h:mm aa", new Locale("es"));
                                formatterTime.setTimeZone(TimeZone.getTimeZone("GMT-5"));
                                timeConverter = formatterTime.format(dateParsed);

                            }catch (Exception e){
                                Log.d(TAG,"Excepcion al convertir date: "+e.getMessage());
                            }

                            String score="", status="";
                            try{
                                score = fixture.getString("score");
                                status = fixture.getString("status");
                            }catch (JSONException e){
                                Log.d(TAG,"Exception gettin score and status");
                            }

                            Match match = new Match(id, teamAid, teamA, teamBid, teamB, dateConverted, timeConverter, location, idGroup);

                            match.setScore("");
                            if(status.equals("")) { match.setStatus(getString(R.string.NOT_STARTED)); }
                            else{  match.setStatus(status); }

                            matchesGroup.add(match);
                        }

                        switch (idGroup){
                            case 1:     agregadosA = true;  break;
                            case 2:     agregadosB = true;  break;
                            case 3:     agregadosC = true;  break;
                        }

                        if(!isTeamsSaved()){
                            //Registers team in Local database
                            ArrayList<Team> teamsForRegister = new ArrayList<Team>();
                            for (Map.Entry<Integer, String> entry : teams.entrySet()) {
                                Team team = new Team(entry.getKey(), entry.getValue(), idGroup);
                                teamsForRegister.add(team);
                            }
                            saveTeams(teamsForRegister);
                        }

                        //Register fixtures in BD
                        saveMatches(matchesGroup);
                    }else{
                        Log.d(TAG,"No fixtures found");
                        Toast.makeText(MainActivity.this, "No fixtures found", Toast.LENGTH_SHORT).show();
                    }
                    
                } catch (JSONException e) {
                    Log.d(TAG,"Excepcion: "+e.getMessage());
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Error
                Log. d(TAG, "Error with request for to obtain information about fixtures: "+error.getMessage());
            }
        });
        cola.add(peticion);
    }

    /**
     * Saves true if the team are saved in the local DB. Otherwise put false
     */
    public void teamsSaved(){
        SharedPreferences preferencias = getSharedPreferences(getString(R.string.shared_preferences_teams), 0);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putBoolean(getString(R.string.shared_preferences_teams_saved), true);
        editor.commit();
    }

    /**
     * Check if teams are saved in the local DB
     * @return
     */
    public boolean isTeamsSaved(){
        SharedPreferences preferences = getSharedPreferences(getString(R.string.shared_preferences_teams), 0);
        return preferences.getBoolean(getString(R.string.shared_preferences_teams_saved), false);
    }

    public void getGroupInformation(String idGroupServer,final int idGroupLocal){
        String URLStanding = "https://livescore-api.com/api-client/leagues/table.json?key="+
                getString(R.string.api_key)+"&secret="+getString(R.string.api_secret)+"&season=2&league="+idGroupServer;
        //Prepare the request
        Log.d(TAG,URLStanding);
        JsonObjectRequest peticion = new JsonObjectRequest(Request.Method.GET, URLStanding,null,  new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response){
                try {
                    Log.d(TAG,"Response group information");
                    Boolean success = response.getBoolean("success");
                    if(success) {
                        JSONObject objData = response.getJSONObject("data");
                        ArrayList<TeamGroup> teamGroupArray = new ArrayList<>();
                        //get Fixtures
                        JSONArray teams = objData.getJSONArray("table");
                        int numTeams = teams.length();
                        int i = 0;
                        for (i = 0; i < numTeams; i++) {
                            //Log.d(TAG, "Respuesta standing: " + teams.get(i).toString());
                            JSONObject teamInfo = (JSONObject) teams.get(i);

                            //Obtain data of team
                            String name = teamInfo.getString("name");
                            int rank = teamInfo.getInt("rank");
                            int points = teamInfo.getInt("points");
                            int matches = teamInfo.getInt("matches");
                            int lost = teamInfo.getInt("lost");
                            int drawn = teamInfo.getInt("drawn");
                            int won = teamInfo.getInt("won");

                            TeamGroup teamGroup = new TeamGroup(idGroupLocal,name, rank, matches, won, drawn, lost, points);
                            Log.d(TAG,"GroupInfo: "+name+" in "+rank);
                            teamGroupArray.add(teamGroup);
                        }
                        switch (idGroupLocal){
                            case 1:     groupA = true;  break;
                            case 2:     groupB = true;  break;
                            case 3:     groupC = true;  break;
                        }
                        //Register in BD
                        saveStanding(teamGroupArray);
                    }else{
                        Log.d(TAG,"There are not information about groups");
                        //fillStandingDefault(idGroupLocal);
                        Toast.makeText(MainActivity.this, "There are not information about group standings", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Log.d(TAG,"Excepcion: "+e.getMessage());
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Error
                Log. d(TAG, "Error with request for to obtain information about groups: "+error.getMessage());
            }
        });
        cola.add(peticion);

    }

    /**
     * Saves the teams in Local Database
     * @param teams
     */
    private void saveTeams(final ArrayList<Team> teams) {
        class SaveTeams extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                //adding to database
                DatabaseAmericaCupAccesor.getInstance(getApplication()).teamDAO().insert(teams);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                //Save in preferences
                teamsSaved();
                Log.d(TAG, "Teams ["+teams.size()+"] saved ");
            }
        }
        SaveTeams save = new SaveTeams();
        save.execute();
    }

    /**
     * Saves the matches in Local database
     * @param matchesGroup
     */
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
                Log.d(TAG,"Matches agregados");
                for(Match match: matchesGroup){
                    Log.d(TAG,"Match "+match.getTeamA()+" VS "+match.getTeamB());
                }
                if(agregadosA && agregadosB && agregadosC){
                    VPAdapter.updateFragment(0);
                    VPAdapter.updateFragment(1);
                    agregadosA = agregadosB = agregadosC = false;
                }

            }
        }
        SaveMatches save = new SaveMatches();
        save.execute();
    }

    //
    private void saveStanding(final ArrayList<TeamGroup> group) {
        class SaveStanding extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                //adding to database
                DatabaseAmericaCupAccesor.getInstance(getApplication()).teamGroupDAO().insert(group);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Log.d(TAG,"Teams standing agregados");
                for(TeamGroup teamGroup: group){
                    Log.d(TAG,"Team "+teamGroup.getTeamName()+" group: "+teamGroup.getTeamGroup_id());
                }
                if(groupA && groupB && groupC){
                    VPAdapter.updateFragment(1);
                    groupA = groupB = groupC = false;
                }

            }
        }
        SaveStanding save = new SaveStanding();
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

}