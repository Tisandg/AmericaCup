package com.example.tisandg.americacup2019.Fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tisandg.americacup2019.Entities.Team;
import com.example.tisandg.americacup2019.R;

import java.util.List;

public class TeamAdapter extends BaseAdapter {

    private final Context mContext;
    private List<Team> favorities;

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
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.item_team, null);
        }

        final TextView nameTeam = convertView.findViewById(R.id.name_team);

        nameTeam.setText(favorities.get(position).getTeam_name());

        return convertView;
    }

    public List<Team> getFavorities() {
        return favorities;
    }

    public void setFavorities(List<Team> favorities) {
        this.favorities = favorities;
    }
}
