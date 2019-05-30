package com.softwareproject.focus.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.softwareproject.focus.R;

/**
 * Created by M.Eslim on 07/04/2018.
 */

public class Dialog extends AppCompatDialogFragment {
    EditText id;
    private DialogListner listner;
    private DialogListner2 listner2;
    int ID;
    Button button;
    Bundle b;
    int restoredText;

    String idtoshow;


    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.layout_dialog, null);

        id = view.findViewById(R.id.editText7);

        button = view.findViewById(R.id.button2);
//          b = getActivity().getIntent().getExtras();

        //  idtoshow = b.getString("theidtochoose");
        SharedPreferences prefs = getActivity().getSharedPreferences("com.example.mohammed.mygps",0);
        restoredText = prefs.getInt("index", 0);

        // Toast.makeText(getContext(), restoredText, Toast.LENGTH_SHORT).show();

        id.setText(restoredText);
        builder.setView(view).setTitle("choose to delete or update ! ")
                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ID = restoredText;
                        listner.applTexts(ID);


                    }
                })

        ;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listner = (DialogListner) context;
            listner2 = (DialogListner2) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must empliment dialog lstener");
        }
    }



//
//    public void onAttach2(Context context) {
//
//        try {
//           // listner = (DialogListner) context;
//            listner2 = (DialogListner2) context;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(context.toString() + "must empliment dialog lstener");
//        }
//    }



    public interface DialogListner

    {
        void applTexts(int id);

    }
    public interface DialogListner2

    {
        void update(String id);

    }


}

