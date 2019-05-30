package com.softwareproject.focus.Activities;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.softwareproject.focus.Adapter.ListAppAdapter_profile;

import com.softwareproject.focus.Model.app;
import com.softwareproject.focus.R;

import java.util.ArrayList;
import java.util.List;

public class Apps_list extends AppCompatActivity  {

    ListView myApp;
    String profile_name,from_date,from_time,to_date,to_time;
    long repeat_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_list);

        Intent data = getIntent();
        profile_name = data.getExtras().getString("name");
        from_date = data.getExtras().getString("from_date");
        from_time = data.getExtras().getString("from_time");
        to_date = data.getExtras().getString("to_date");
        to_time = data.getExtras().getString("to_time");
        repeat_type = data.getLongExtra("repeat_type",0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.selecta_apps);
        toolbar.setTitleTextColor(Color.WHITE);

        List<app> installedApps = getAllInstalledApps();
        ListAppAdapter_profile adapter = new ListAppAdapter_profile(Apps_list.this,installedApps);
        myApp = (ListView) findViewById(R.id.select_list);
        myApp.setAdapter(adapter);


        myApp.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

//                SharedPreferences.Editor sharedPreferences = getSharedPreferences("com.example.mohammed.mygps", 0).edit();
//                sharedPreferences.putString("index", idtoshow);
//
//
//                sharedPreferences.apply();
                //   Toast.makeText(BasicMydata.this, A[2] , Toast.LENGTH_SHORT).show();
                // Toast.makeText(Frag2_app.this, "حصلت مشكلة", Toast.LENGTH_SHORT).show();




//                return true;
                return true;
            }

        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.done_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.done) {
            Intent intent = new Intent(Apps_list.this,Profile_attributes.class);
            intent.putExtra("name",profile_name);
            intent.putExtra("from_date",from_date);
            intent.putExtra("from_time",from_time);
            intent.putExtra("to_date",to_date);
            intent.putExtra("to_time",to_time);
            intent.putExtra("repeat_type",repeat_type);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private List<app> getAllInstalledApps() {

        List<app> List = new ArrayList<app>();
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);

        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);

            if ((isSystemPackage(p) == false)) {
                String appName = p.applicationInfo.loadLabel(getPackageManager()).toString();
                Drawable icon = p.applicationInfo.loadIcon(getPackageManager());
                List.add(new app(appName, icon));
            }
        }
        return List;
    }

    private boolean isSystemPackage(PackageInfo pInfo) {
        return ((pInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true : false;
    }


}
