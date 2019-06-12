package com.example.tisandg.americacup2019.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.example.tisandg.americacup2019.Database.DatabaseAmericaCupAccesor;
import com.example.tisandg.americacup2019.Entities.Team;
import com.example.tisandg.americacup2019.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment {

    private String TAG = "FavoritiesFragment";
    private List<Team> favorites;

    //Items Layout
    private TextView txtMsg;

    private GridView teamsGridView;
    private TeamAdapter teamAdapter;

    public FavoritesFragment() {
        // Required empty public constructor
        favorites = new ArrayList<Team>();
    }

    @Override
    public String toString() {
        return "Favorities";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);
        txtMsg = view.findViewById(R.id.msg_favorities);
        teamsGridView = view.findViewById(R.id.gridview);

        teamAdapter = new TeamAdapter(favorites, getActivity());
        teamsGridView.setAdapter(teamAdapter);

        getFavoritesTeam();

        return view;
    }

    public void getFavoritesTeam(){
        class GetFavorites extends AsyncTask<Void, Void, Boolean> {
            @Override
            protected Boolean doInBackground(Void... voids) {
                favorites = DatabaseAmericaCupAccesor.getInstance(getActivity().getApplication()).teamDAO().findFavorities(true);
                Log.d(TAG,"Numero matches en fragment: "+favorites.size());
                return true;
            }

            @Override
            protected void onPostExecute(Boolean response) {
                super.onPostExecute(response);
                if(favorites.size()>0){
                    Log.d(TAG,"Actualizando adapter");
                    txtMsg.setVisibility(View.INVISIBLE);
                    teamAdapter.setFavorities(favorites);
                    teamAdapter.notifyDataSetChanged();
                }else{
                    txtMsg.setVisibility(View.VISIBLE);
                }
            }
        }
        GetFavorites get = new GetFavorites();
        get.execute();
    }

    public void update(){}

}
