package com.softwareproject.focus.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.softwareproject.focus.Database.database;
import com.softwareproject.focus.Fragments.Frag2_app;
import com.softwareproject.focus.Model.app;
import com.softwareproject.focus.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amjad on 02/04/18.
 */

class ViewHolder {
    TextView app_name;
    ImageView app_image;
    Switch app_switch;
}

public class ListAppAdapter_dialog extends BaseAdapter{

    private LayoutInflater layoutInflater;
    private List<app> appInList;
    database db;

    public ListAppAdapter_dialog(Context context, List<app> customizedListView) {
        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.appInList = customizedListView;
    }
    @Override
    public int getCount() {
        return appInList.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder listViewHolder;
        if(convertView == null){
            listViewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.app_layout, parent, false);

            listViewHolder.app_name = (TextView)convertView.findViewById(R.id.app_name);
            listViewHolder.app_image = (ImageView)convertView.findViewById(R.id.app_image);
            listViewHolder.app_switch = (Switch)convertView.findViewById(R.id.app_switch);
            convertView.setTag(listViewHolder);

            db = new database(convertView.getContext());

            final View finalConvertView = convertView;
            listViewHolder.app_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (listViewHolder.app_switch.isChecked()){
                        List<app> apps = db.getAllApp();

                        List<String> app_name = new ArrayList<String>();


                        for (int i =0 ;i < apps.size();i++) {
                            app_name.add(apps.get(i).getName());
                        }

                        if (!app_name.contains(listViewHolder.app_name.getText().toString())){
                            db.Insert_app(listViewHolder.app_name.getText().toString(), "Activate");
                            apps = db.getAllApp();
                            ListAppAdapter_dialog adapter = new ListAppAdapter_dialog(finalConvertView.getContext(), apps);
                            Frag2_app.list_app.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(finalConvertView.getContext(), "Added to block list", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(finalConvertView.getContext(), "It's already exist", Toast.LENGTH_SHORT).show();
                        }
                        Frag2_app.alertDialog.dismiss();
                    }
                }
            });
        }
        else{
            listViewHolder = (ViewHolder)convertView.getTag();
        }
        listViewHolder.app_name.setText(appInList.get(position).getName());
        listViewHolder.app_image.setImageDrawable(appInList.get(position).getIcon());

        return convertView;
    }
}