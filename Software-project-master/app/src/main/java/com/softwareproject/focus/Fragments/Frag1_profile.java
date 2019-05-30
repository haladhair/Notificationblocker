package com.softwareproject.focus.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softwareproject.focus.Activities.Profile_attributes;
import com.softwareproject.focus.Adapter.ListProfileAdapter;
import com.softwareproject.focus.Database.database;
import com.softwareproject.focus.Model.profile;
import com.softwareproject.focus.R;

import java.util.List;

/**
 * Created by Amjad on 31/03/18.
 */

public class Frag1_profile extends Fragment {

    RecyclerView profile_list;
    RecyclerView.LayoutManager layoutManager;
    database db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag1_layout,container,false);

        db = new database(getContext());
        profile_list = (RecyclerView) view.findViewById(R.id.list_profile);
        List<profile> profiles = db.getAllProfile();
        ListProfileAdapter adapter = new ListProfileAdapter(getContext(),profiles);
        profile_list.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        profile_list.setLayoutManager(layoutManager);
        adapter.notifyDataSetChanged();
        profile_list.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),Profile_attributes.class);
                intent.putExtra("name","");
                intent.putExtra("from_date","Date");
                intent.putExtra("from_time","Time");
                intent.putExtra("to_date","Date");
                intent.putExtra("to_time","Time");
                intent.putExtra("repeat_type",String.valueOf(0));
                startActivity(intent);
            }
        });
        return view;
    }
}
