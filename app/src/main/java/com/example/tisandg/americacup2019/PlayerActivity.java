package com.example.tisandg.americacup2019;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.widget.TextView;

import com.example.tisandg.americacup2019.Entities.Player;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {

    TextView txtName, txtPostion, txtBirthday, txtNacionality;
    private String URLPlayer;
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        //Obtain URL PLayer
        URLPlayer = getIntent().getStringExtra("url");

        player = new Player();

        //Layout Elements
        txtName = findViewById(R.id.player_name);
        txtPostion = findViewById(R.id.player_position);
        txtBirthday = findViewById(R.id.player_birthday);
        txtNacionality = findViewById(R.id.player_nacionality);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPlayer();
    }

    private void loadPlayer() {

        class LoadTasks extends AsyncTask<Void, Void, Void> {

            private static final String TAG = "ToDo";

            @Override
            protected Void doInBackground(Void... voids) {

                ArrayList<String> result = new ArrayList<>(0);
                try {
                    URL url = new URL(URLPlayer);
                    // Create a new HTTP URL connection
                    URLConnection connection = url.openConnection();
                    HttpURLConnection httpConnection = (HttpURLConnection) connection;
                    int responseCode = httpConnection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        InputStream in = httpConnection.getInputStream();
                        parseJSON(in);
                    }
                    httpConnection.disconnect();
                } catch (MalformedURLException e) {
                    Log.e(TAG, "Malformed URL Exception.", e);
                } catch (IOException e) {
                    Log.e(TAG, "IO Exception.", e);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                //Update textview
                txtName.setText(player.getPlayer_name());
                txtBirthday.setText(player.getPlayer_birthdate());
                txtNacionality.setText(player.getPlayer_nacionality());
                txtPostion.setText(player.getPlayer_position());
                Log.d(TAG,"Data updated!");
            }

            //Method to parse the tasks.json file available in th server
            private void parseJSON(InputStream in) throws IOException {
                // Create a new Json Reader to parse the input.
                JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
                try {
                    Player playerAux = new Player();
                    //Parse a specific object inside the array
                    reader.beginObject();
                    while (reader.hasNext()) {

                        String value = reader.nextName();
                        //It gets the property value and store it on the correct property of ToDoItem object
                        switch (value) {
                            case "name":
                                playerAux.setPlayer_name(reader.nextString());
                                break;
                            case "lastName":
                                playerAux.setPlayer_lastname(reader.nextString());
                                break;
                            case "dateOfBirth":
                                playerAux.setPlayer_birthdate(reader.nextString());
                                break;
                            case "nationality":
                                playerAux.setPlayer_nacionality(reader.nextString());
                                break;
                            case "position":
                                playerAux.setPlayer_position(reader.nextString());
                                break;
                            default:
                                reader.skipValue();
                                break;
                        }
                    }
                    reader.endObject();
                    player = playerAux;
                } finally {
                    reader.close();
                }
            }

        }
        LoadTasks loadTasks = new LoadTasks();
        loadTasks.execute();
    }



}
