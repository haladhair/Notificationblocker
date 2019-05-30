package com.softwareproject.focus.Activities;

import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.softwareproject.focus.Database.User;
import com.softwareproject.focus.Database.log_RegDatabase;
import com.softwareproject.focus.R;

import java.util.ArrayList;
import java.util.Objects;


public class loginActivity extends AppCompatActivity {
    private TextInputLayout mEmail;
    private TextInputLayout mpassword;
    private Button mlogbtn, mRegister;
    private ProgressDialog progressDialog;
    static log_RegDatabase db;
    ArrayList<String> listData;
    private  Toolbar mToolbar;
    //  ArrayList<String> Emaillist;
    // ArrayList<String> Passlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new log_RegDatabase(this);
        listData = db.getallRecord();
        mEmail = (TextInputLayout) findViewById(R.id.reg_email);
        mpassword = (TextInputLayout) findViewById(R.id.reg_password);
        mlogbtn = (Button) findViewById(R.id.reg_creat_but);
        progressDialog = new ProgressDialog(this);
        mToolbar = (findViewById(R.id.regester_toolbar));


//        for (int i = 0; i<listData.size();i++)
//        {
//            Emaillist.add(listData.get(i).getEmail());
//            Passlist.add(listData.get(i).getPassword());
//        }
        mToolbar.setTitle("Log In");
    }

    public void Register(View view) {
        startActivity(new Intent(loginActivity.this, RegisterActivity.class));

    }

    public void login(View view) {


        final String Email = mEmail.getEditText().getText().toString().toLowerCase();
        final String pass = mpassword.getEditText().getText().toString().toLowerCase();


        if (!Email.isEmpty() || !pass.isEmpty()) {
            progressDialog.setTitle("Logging in ");
            progressDialog.setMessage("please wait...  ");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            new Handler().postDelayed(new Runnable() {
                boolean amooot = false;

                @Override
                public void run() {
                    for (int i = 0; i < listData.size(); i++) {
                        if (listData.get(i).toLowerCase().contains(Email) && listData.get(i).toLowerCase().contains(pass)) {
                            amooot = true;
                        }


                    }
                    if (amooot) {
                        progressDialog.dismiss();
                        startActivity(new Intent(loginActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(loginActivity.this, "تحقق من البيانات المدخلة ", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }


                }
            }, 1000);

//                else
//                    {
//                        progressDialog.dismiss();
//                        Toast.makeText(loginActivity.this,"تحقق من البيانات المدخلة ",Toast.LENGTH_SHORT).show();
//                    }


        } else if (Email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(loginActivity.this, "جميع الحوقل مطلوبة ! ", Toast.LENGTH_SHORT).show();

        } else {
            progressDialog.dismiss();
            Toast.makeText(loginActivity.this, "تحقق من البيانات المدخلة ", Toast.LENGTH_SHORT).show();
        }


    }
}
