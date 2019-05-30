package com.softwareproject.focus.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.softwareproject.focus.Model.profile;
import com.softwareproject.focus.R;

import java.util.List;

/**
 * Created by Amjad on 04/04/18.
 */

class ListProfileViewHolder extends RecyclerView.ViewHolder{

    TextView profile_name,profile_duration_from,profile_duration_to,profile_repeate;
    Switch profile_switch;

    public ListProfileViewHolder(View itemView) {
        super(itemView);
        profile_name = (TextView)itemView.findViewById(R.id.profile_name);
        profile_switch = (Switch)itemView.findViewById(R.id.profile_switch);
        profile_duration_from = (TextView)itemView.findViewById(R.id.profile_duration_from);
        profile_duration_to = (TextView)itemView.findViewById(R.id.profile_duration_to);
        profile_repeate = (TextView)itemView.findViewById(R.id.profile_repeat);
    }
}

public class ListProfileAdapter extends RecyclerView.Adapter<ListProfileViewHolder>{

    private Context context;
    private List<profile> profiles;

    public ListProfileAdapter(Context context, List<profile> profiles) {
        this.context = context;
        this.profiles = profiles;
    }

    @Override
    public ListProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.profile_layout,parent,false);
        return new ListProfileViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListProfileViewHolder holder, int position) {
        holder.profile_name.setText(profiles.get(position).getName());
        holder.profile_duration_from.setText(profiles.get(position).getDuration_from().toString());
        holder.profile_duration_to.setText(profiles.get(position).getDuration_to().toString());
        holder.profile_repeate.setText(profiles.get(position).getRepeat_type().toString());
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }
}
