package com.example.tisandg.americacup2019.Recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tisandg.americacup2019.Entities.Match;
import com.example.tisandg.americacup2019.R;

import java.util.List;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.ItemViewHolder> {

    private List<Match> listMatches;
    private Context context;

    private static View.OnClickListener mOnItemClickListener;

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    public AdapterRecycler(Context context, List<Match> listData) {
        this.context = context;
        this.listMatches = listData;
    }

    @NonNull
    @Override
    public AdapterRecycler.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_item_match,
                viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecycler.ItemViewHolder view, int i) {
        /*view.nameTeamB.setText(listMatches.get(i).getTeamA());
        view.nameTeamA.setText(listMatches.get(i).getTeamB());
        view.goalsA.setText(String.valueOf(listMatches.get(i).getGoalsA()));
        view.goalsB.setText(String.valueOf(listMatches.get(i).getGoalsB()) );*/
    }

    @Override
    public int getItemCount() {
        return listMatches.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView nameTeamA, nameTeamB, goalsA, goalsB;

        public ItemViewHolder(@NonNull View view) {
            super(view);
            nameTeamA = view.findViewById(R.id.nameA);
            nameTeamB = view.findViewById(R.id.nameB);
            goalsA = view.findViewById(R.id.goalsA);
            goalsB = view.findViewById(R.id.goalsB);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }
}

