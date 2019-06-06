package com.example.tisandg.americacup2019.Fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tisandg.americacup2019.Database.DatabaseAmericaCupAccesor;
import com.example.tisandg.americacup2019.Entities.Match;
import com.example.tisandg.americacup2019.R;
import com.example.tisandg.americacup2019.Recycler.AdapterRecycler;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MatchesFragment extends Fragment implements View.OnClickListener{

    AdapterRecycler adaptador;
    RecyclerView mRecyclerView;
    List<Match> listData;
    ComunicationToActivity callback;

    public MatchesFragment() {
        // Required empty public constructor
        listData = new ArrayList<Match>();
    }

    public ComunicationToActivity getCallback() {
        return callback;
    }

    public void setCallback(ComunicationToActivity callback) {
        this.callback = callback;
    }

    public interface ComunicationToActivity {
        void watchMath(int id);
    }

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Step 4 of 4: Finally call getTag() on the view.
            // This viewHolder will have all required values.
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            Match thisItem = listData.get(position);
            callback.watchMath(position);
        }
    };

    @Override
    public String toString() {
        return "Matches";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_partidos, container, false);

        //Para el RecyclerView
        mRecyclerView = view.findViewById(R.id.recyclerView_matches);

        //listData = fillMatches();
        adaptador = new AdapterRecycler(getActivity(),listData);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adaptador.setOnItemClickListener(onItemClickListener);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adaptador);
    }

    private void getMatchs() {
        class GetMatchs extends AsyncTask<Void, Void, List<Match>> {

            @Override
            protected List<Match> doInBackground(Void... voids) {
                List<Match> matches = DatabaseAmericaCupAccesor
                        .getInstance(getActivity().getApplication()).matchDAO().loadAll();
                return matches;
            }

            @Override
            protected void onPostExecute(List<Match> matches) {
                super.onPostExecute(matches);
                listData.clear();
                for (int i = 0; i < matches.size(); i++) {
                    listData.add(matches.get(i));
                }
                adaptador.notifyDataSetChanged();
            }
        }
        GetMatchs getMatchs = new GetMatchs();
        getMatchs.execute();

    }

    @Override
    public void onClick(View v) {
        //Intent goTo
    }
}
