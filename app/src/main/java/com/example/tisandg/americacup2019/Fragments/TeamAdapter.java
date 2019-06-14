package com.example.tisandg.americacup2019.Fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tisandg.americacup2019.Entities.Team;
import com.example.tisandg.americacup2019.R;
import com.example.tisandg.americacup2019.Recycler.AdapterRecycler;

import java.util.List;


public class TeamAdapter extends BaseAdapter {

    private final Context mContext;
    private List<Team> favorities;
    private static View.OnClickListener mOnItemClickListener;

    public TeamAdapter(List<Team> favorities, Context context) {
        this.favorities = favorities;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return favorities.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.item_team, null);
        }

        TextView nameTeam = view.findViewById(R.id.name_team);
        ImageView shield = view.findViewById(R.id.shiel_team);

        String name = favorities.get(position).getTeam_name();
        nameTeam.setText(favorities.get(position).getTeam_name());
        shield.setBackground(mContext.getDrawable(AdapterRecycler.drawable(name)) );
        return view;
    }

    public List<Team> getFavorities() {
        return favorities;
    }

    public void setFavorities(List<Team> favorities) {
        this.favorities = favorities;
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
