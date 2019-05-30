package com.softwareproject.focus.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import com.softwareproject.focus.R;

public class Dialog extends AppCompatDialogFragment {
    private DialogListner listner;

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view).setTitle(" Are You sure You want to exit !  ")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getDialog().dismiss();
                    }
                })
                .setPositiveButton("Yes Exit !", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listner.applTexts();
                    }
                });


          return builder.create();


    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listner = (DialogListner) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must empliment dialog lstener");
        }
    }
    public interface DialogListner

    {
        void applTexts();

    }
}
