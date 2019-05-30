package com.softwareproject.focus.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.softwareproject.focus.Model.app;
import com.softwareproject.focus.R;

import java.util.List;

/**
 * Created by Amjad on 07/04/18.
 */

class ViewHolder_select {
    TextView app_name;
    ImageView app_image;
    Switch app_switch;
}

public class ListAppAdapter_select extends BaseAdapter{
    private LayoutInflater layoutInflater;
    private List<app> appInList;

    public ListAppAdapter_select(Context context, List<app> appInList) {
        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.appInList = appInList;
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
    public View getView(int i, View view, ViewGroup viewGroup) {

        final ViewHolder_select listViewHolder;
        if(view == null){
            listViewHolder = new ViewHolder_select();
            view = layoutInflater.inflate(R.layout.app_layout, viewGroup, false);

            listViewHolder.app_name = (TextView)view.findViewById(R.id.app_name);
            listViewHolder.app_image = (ImageView)view.findViewById(R.id.app_image);
            listViewHolder.app_switch = (Switch)view.findViewById(R.id.app_switch);
            view.setTag(listViewHolder);
        }
        else{
            listViewHolder = (ViewHolder_select) view.getTag();
        }
        listViewHolder.app_name.setText(appInList.get(i).getName());
        listViewHolder.app_image.setImageDrawable(appInList.get(i).getIcon());

        return view;
    }
}
