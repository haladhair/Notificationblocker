package com.softwareproject.focus.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import com.softwareproject.focus.Adapter.ListAppAdapter_profile;
import com.softwareproject.focus.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Apps_list extends AppCompatActivity implements SearchView.OnQueryTextListener{

    RecyclerView myApp;
    String profile_name,from_date,from_time,to_date,to_time,process;
    private SearchView searchView;
    long repeat_type;
    private PackageManager manager;
    private SharedPreferences mPreferences;
    private List<ApplicationInfo> applications;
    private ListAppAdapter_profile adapter;


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
        process = data.getExtras().getString("process");
        repeat_type = data.getLongExtra("repeat_type",0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.selecta_apps);
        toolbar.setTitleTextColor(Color.WHITE);

        manager = getPackageManager();
        applications = manager.getInstalledApplications(0);

        searchView = (SearchView) findViewById(R.id.search);
        if (searchView != null) {
            searchView.setOnQueryTextListener(this);
        }

        myApp = (RecyclerView) findViewById(R.id.select_list);
        if (myApp != null) {
            myApp.setHasFixedSize(true);
            myApp.setLayoutManager(new LinearLayoutManager(Apps_list.this));
        }

        mPreferences = PreferenceManager.getDefaultSharedPreferences(Apps_list.this);

        applications = manager.getInstalledApplications(0);

        Collections.sort(applications, new Comparator<ApplicationInfo>() {
            @Override
            public int compare(ApplicationInfo app1, ApplicationInfo app2) {
                String label1 = app1.loadLabel(manager).toString();
                String label2 = app2.loadLabel(manager).toString();

                return label1.compareToIgnoreCase(label2);
            }
        });

        adapter = new ListAppAdapter_profile(Apps_list.this,applications);
        myApp.setAdapter(adapter);
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
            intent.putExtra("process",process);
            intent.putExtra("repeat_type",repeat_type);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if (TextUtils.isEmpty(s)) {
            adapter.setApps(applications);

        } else {
            String query = s.toLowerCase();
            ArrayList<ApplicationInfo> filtered = new ArrayList<>();
            for (ApplicationInfo app : applications) {
                String label = app.loadLabel(manager).toString().toLowerCase();
                if (label.contains(query)) {
                    filtered.add(app);
                }
            }
            adapter.setApps(filtered);
        }
        return true;
    }

/*
    @Override
    public void onCheckboxAppChecked(int position, boolean isChecked) {
        String pkg = adapter.getIem(position).packageName;

        if (mPreferences.contains(Utils.PREF_PACKAGES_BLOCKED)) {
            HashSet<String> pkgs = new HashSet<>(Arrays.asList(mPreferences.getString(Utils.PREF_PACKAGES_BLOCKED, "").split(";")));

            if (isChecked) {
                pkgs.add(pkg);
            } else {
                pkgs.remove(pkg);
            }

            mPreferences.edit().putString(Utils.PREF_PACKAGES_BLOCKED, TextUtils.join(";", pkgs)).apply();

        } else {
            mPreferences.edit().putString(Utils.PREF_PACKAGES_BLOCKED, pkg).apply();
        }
    }
    */
}