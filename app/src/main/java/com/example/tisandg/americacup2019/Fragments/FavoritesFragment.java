package com.example.tisandg.americacup2019.Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.tisandg.americacup2019.Database.DatabaseAmericaCupAccesor;
import com.example.tisandg.americacup2019.Entities.Team;
import com.example.tisandg.americacup2019.MainActivity;
import com.example.tisandg.americacup2019.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment {

    private String TAG = "FavoritiesFragment";
    private List<Team> favorites;
    private Context mContext;

    //Items Layout
    private TextView txtMsg;

    private GridView teamsGridView;
    private TeamAdapter teamAdapter;
    private int FAVORITE_TEAM = 1;
    View.OnClickListener mOnItemClickListener;

    public FavoritesFragment() {
        // Required empty public constructor
        favorites = new ArrayList<Team>();
    }

    public void setCallback(View.OnClickListener callback) {
        this.mOnItemClickListener = callback;
    }

    public void setContext(Context mainActivity) {
        this.mContext = (Context) mainActivity;
    }

    public interface favoriteToActivityInterface {
        void watchTeam(int id);
    }

    @Override
    public String toString() {
        return mContext.getString(R.string.title_fragment_favorities);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);
        txtMsg = view.findViewById(R.id.msg_favorities);
        teamsGridView = view.findViewById(R.id.gridview);

        teamAdapter = new TeamAdapter(favorites, getActivity());
        //teamAdapter.setOnItemClickListener(mOnItemClickListener);
        teamsGridView.setAdapter(teamAdapter);

        teamsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int idTeam = favorites.get(position).getTeam_id();
                int idGroup = favorites.get(position).getGroup_id();
                ((MainActivity) getActivity()).goToTeamDetail(idTeam, idGroup);
                //goToTeamDetail(position);
            }
        });

        getFavoritesTeam();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void getFavoritesTeam(){
        class GetFavorites extends AsyncTask<Void, Void, Boolean> {
            @Override
            protected Boolean doInBackground(Void... voids) {
                favorites = DatabaseAmericaCupAccesor.getInstance(getActivity().getApplication()).teamDAO().findFavorities(true);
                Log.d(TAG,"Numero teams en fragment: "+favorites.size());
                return true;
            }

            @Override
            protected void onPostExecute(Boolean response) {
                super.onPostExecute(response);
                if(favorites.size()>0){
                    Log.d(TAG,"Actualizando adapter");
                    txtMsg.setVisibility(View.INVISIBLE);
                }else{
                    txtMsg.setVisibility(View.VISIBLE);
                }
                teamAdapter.setFavorities(favorites);
                teamAdapter.notifyDataSetChanged();
            }
        }
        GetFavorites get = new GetFavorites();
        get.execute();
    }

    public void update( ){
        if(!isAdded()){
            return;
        }
        getFavoritesTeam();
    }

}
