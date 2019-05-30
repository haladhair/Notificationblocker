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
import com.softwareproject.focus.Model.app;
import com.softwareproject.focus.R;

import java.util.List;

/**
 * Created by Amjad on 07/04/18.
 */
class ViewHolder_app {
    TextView app_name;
    ImageView app_image;
    Switch app_switch;
}

public class ListAppAdapter_app extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<app> appInList;
    database db;

    public ListAppAdapter_app(Context context, List<app> appInList) {
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

        final ViewHolder_app listViewHolder;
        if(view == null){
            listViewHolder = new ViewHolder_app();
            view = layoutInflater.inflate(R.layout.app_layout, viewGroup, false);

            listViewHolder.app_name = (TextView)view.findViewById(R.id.app_name);
            listViewHolder.app_image = (ImageView)view.findViewById(R.id.app_image);
            listViewHolder.app_switch = (Switch)view.findViewById(R.id.app_switch);
            view.setTag(listViewHolder);
            db = new database(view.getContext());
            List<app> apps = db.getAllApp();

            /*
            List<String> name = new ArrayList<>();
            for (int j = 0;j < apps.size();j++){
                if (apps.get(i).getaSwitch().equals("Activate")){
                    name.add(apps.get(i).getName());
                }
            }
            for (int k = 0 ;k<name.size();k++){
                if (listViewHolder.app_name.equals(name.get(k))){
                    listViewHolder.app_switch.setChecked(true);
                }
            }
*/

            final View finalView = view;
            listViewHolder.app_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (listViewHolder.app_switch.isChecked()){
                        Toast.makeText(finalView.getContext(),"Activate",Toast.LENGTH_LONG).show();
                    }else {
                        db.update_app(listViewHolder.app_name.getText().toString(),"Deactivate");
                        Toast.makeText(finalView.getContext(),"Deactivate",Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
        else{
            listViewHolder = (ViewHolder_app) view.getTag();
        }
        listViewHolder.app_name.setText(appInList.get(i).getName());
        listViewHolder.app_image.setImageDrawable(appInList.get(i).getIcon());

        return view;
    }
}