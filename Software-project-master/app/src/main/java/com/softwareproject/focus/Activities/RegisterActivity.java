package com.softwareproject.focus.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.softwareproject.focus.Database.log_RegDatabase;
import com.softwareproject.focus.R;

public class RegisterActivity extends AppCompatActivity {
    private TextInputLayout mDispalyName;

    private TextInputLayout mEmail;
    private TextInputLayout mpassword;
    private Button mCreatebtn;
    private ProgressDialog progressDialog;

    static log_RegDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mDispalyName = (TextInputLayout) findViewById(R.id.reg_display_name);
        mEmail = (TextInputLayout) findViewById(R.id.reg_email);
        mpassword = (TextInputLayout) findViewById(R.id.reg_password);
        mCreatebtn = (Button) findViewById(R.id.reg_creat_but);
        progressDialog = new ProgressDialog(this);

        db = new log_RegDatabase(this);
    }




    public void register(View view) {

        String despaly_name = mDispalyName.getEditText().getText().toString();
        String email = mEmail.getEditText().getText().toString();
        String password = mpassword.getEditText().getText().toString();


        if (despaly_name.isEmpty() || email.isEmpty() || password.isEmpty()) {

                 Toast.makeText(RegisterActivity.this, "كل الحقول مطلوبة !  ", Toast.LENGTH_SHORT).show();
        } else {
            boolean result = db.insertData(despaly_name, email, password);
            if (result == true) {

                Toast.makeText(this, "تم التسجيل بنجاح", Toast.LENGTH_SHORT).show();
                //   MyData.showData();

            progressDialog.setTitle("Registering User ");
            progressDialog.setMessage("please wait while we create your account ! ");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
          //  regesteruser(despaly_name, email, password);

            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                progressDialog.dismiss();
        }


    }
}}

