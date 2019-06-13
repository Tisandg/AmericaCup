package com.example.tisandg.americacup2019.Fragments;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tisandg.americacup2019.Database.DatabaseAmericaCupAccesor;
import com.example.tisandg.americacup2019.Entities.Match;
import com.example.tisandg.americacup2019.R;
import com.example.tisandg.americacup2019.Recycler.AdapterRecycler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchesFragment extends Fragment{

    AdapterRecycler adapter;
    RecyclerView mRecyclerView;
    List<Match> listData;
    ComunicationToActivity callback;
    private String TAG = "MatchesFragment";
    private int equipoSeleccionado;
    private Context context;

    public MatchesFragment() {
        // Required empty public constructor
        listData = new ArrayList<Match>();
        //0 Show all matches
        equipoSeleccionado = 0;
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
            callback.watchMath(thisItem.getMatch_id());
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
        adapter = new AdapterRecycler(getActivity(),listData);
        if(equipoSeleccionado == 0){
            adapter.setListener(true);
        }else{
            adapter.setListener(false);
        }
        //getMatchs();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter.setOnItemClickListener(onItemClickListener);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);
    }

    public void getMatchs() {
        class GetMatchs extends AsyncTask<Void, Void, List<Match>> {

            @Override
            protected List<Match> doInBackground(Void... voids) {
                List<Match> matches;
                if(equipoSeleccionado == 0){
                    matches = DatabaseAmericaCupAccesor.getInstance(context).matchDAO().loadAll();
                }else{
                    matches = DatabaseAmericaCupAccesor.getInstance(context).matchDAO().findByTeam(equipoSeleccionado);
                }
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getMatchs();
    }

    public void update(){
        if(!isAdded()){
            return;
        }
        Log.d(TAG,"Actualizando fragment");
        getMatchs();
    }

    public int getEquipoSeleccionado() {
        return equipoSeleccionado;
    }

    public void setEquipoSeleccionado(int equipoSeleccionado) {
        this.equipoSeleccionado = equipoSeleccionado;
    }

    @Nullable
    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}


