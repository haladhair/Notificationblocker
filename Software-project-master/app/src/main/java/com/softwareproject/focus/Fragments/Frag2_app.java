package com.softwareproject.focus.Fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.softwareproject.focus.Adapter.ListAppAdapter_app;
import com.softwareproject.focus.Adapter.ListAppAdapter_dialog;
import com.softwareproject.focus.Database.database;
import com.softwareproject.focus.Model.app;
import com.softwareproject.focus.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amjad on 31/03/18.
 */

public class Frag2_app extends Fragment implements Dialog.DialogListner, Dialog.DialogListner2{

    public static ListView list_app;
    database db;
    List<app> block_app;
    ListAppAdapter_app adapter;

    private ProgressDialog mProgressDialog;
    public static AlertDialog alertDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.frag2_layout,container,false);

        db = new database(getContext());

        mProgressDialog = new ProgressDialog(getContext());
        list_app = (ListView) view.findViewById(R.id.list_app);
      block_app = db.getAllApp();
         adapter = new ListAppAdapter_app(getContext(), block_app);
        list_app.setAdapter(adapter);

        list_app.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

//                SharedPreferences.Editor sharedPreferences = getSharedPreferences("com.example.mohammed.mygps", 0).edit();
//                sharedPreferences.putString("index", idtoshow);
//
//
//                sharedPreferences.apply();
                //   Toast.makeText(BasicMydata.this, A[2] , Toast.LENGTH_SHORT).show();
               // Toast.makeText(Frag2_app.this, "حصلت مشكلة", Toast.LENGTH_SHORT).show();

              //  openDialog();


//                return true;
                return true;
            }

        });

        /*

        list_app.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {

                Toast.makeText(view.getContext(),"Amjad",Toast.LENGTH_LONG).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Delete")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // db.del_app(list_app.);
                            }
                        });
                builder.create();
            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }
        });

        */
        mProgressDialog.setTitle("Loading all Applications");
        mProgressDialog.setMessage("please wait till we load all applications");

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {



                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                @SuppressLint("RestrictedApi") View view1 = getLayoutInflater(savedInstanceState).inflate(R.layout.activity_block_one_app,null);
                final ListView list = (ListView)view1.findViewById(R.id.block_one_app_list);

                List<app> installedApps = getAllInstalledApps();
                ListAppAdapter_dialog adapter = new ListAppAdapter_dialog(getContext(),installedApps);
                list.setAdapter(adapter);
                alert.setView(view1);
                alertDialog=alert.create();
                alertDialog.show();


            }
        });
        return view;
    }

    public   List<app> getAllInstalledApps() {
        mProgressDialog.show();

        List<app> List = new ArrayList<app>();
        List<PackageInfo> packs = getActivity().getPackageManager().getInstalledPackages(0);

        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);

            if ((isSystemPackage(p) == false)) {
                String appName = p.applicationInfo.loadLabel(getActivity().getPackageManager()).toString();
                Drawable icon = p.applicationInfo.loadIcon(getActivity().getPackageManager());
                List.add(new app(appName, icon));
            }
        }
        mProgressDialog.dismiss();
        return List;

    }

    private boolean isSystemPackage(PackageInfo pInfo) {
        return ((pInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true : false;
    }

    public void openDialog() {
        Dialog dialog = new Dialog();
        dialog.show(getFragmentManager(), "example dialog");

    }

    @Override
    public void update(String id) {

    }

    @Override
    public void applTexts(int id) {

        app item = block_app.get(id);
        block_app.remove(item);
        adapter = new ListAppAdapter_app(getContext(), block_app);
        list_app.setAdapter(adapter);




    }


}