package com.softwareproject.focus.Activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.softwareproject.focus.Database.database;
import com.softwareproject.focus.Fragments.DatePickerFrafment;
import com.softwareproject.focus.Fragments.TimePickerFragment;
import com.softwareproject.focus.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Profile_attributes extends AppCompatActivity implements
        TimePickerDialog.OnTimeSetListener,DatePickerDialog.OnDateSetListener{

    EditText profile_name;
    TextView from_date,from_time,to_date,to_time;
    Spinner repeat_type;
    ListView list_select_app;
    Toolbar mToolbar;
    int t=0;
    int d=0;
    database db;
    List<String> app_name;
    String repeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_attributes);

        profile_name = (EditText)findViewById(R.id.name);
        from_date = (TextView)findViewById(R.id.from_date);
        from_time = (TextView)findViewById(R.id.from_time);
        to_date = (TextView)findViewById(R.id.To_date);
        to_time = (TextView)findViewById(R.id.To_time);
        repeat_type = (Spinner)findViewById(R.id.repeat_type);

        list_select_app = (ListView)findViewById(R.id.selected_list);
        app_name = new ArrayList<>();


        /////////////////////////////////////////////////////////////////////////////////////////////////////////


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.repeat_type,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        repeat_type.setAdapter(adapter);

        repeat= repeat_type.getSelectedItem().toString();

        Intent data = getIntent();
        profile_name.setText(data.getExtras().getString("name"));
        from_date.setText(data.getExtras().getString("from_date"));
        from_time.setText(data.getExtras().getString("from_time"));
        to_date.setText(data.getExtras().getString("to_date"));
        to_time.setText(data.getExtras().getString("to_time"));
        repeat_type.setSelection((int) data.getLongExtra("repeat_type",0));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        setTitle("New Profile");

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
//            db.Insert_profile(profile_name.getText().toString(),from_date.getText()+" "+from_time.getText()
  //                  ,to_date.getText()+" "+to_time.getText(),repeat,app_name);
            Intent intent = new Intent(Profile_attributes.this,MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void select(View view) {
        Intent intent = new Intent(Profile_attributes.this,Apps_list.class);
        intent.putExtra("name",profile_name.getText().toString());
        intent.putExtra("from_date",from_date.getText());
        intent.putExtra("from_time",from_time.getText());
        intent.putExtra("to_date",to_date.getText());
        intent.putExtra("to_time",to_time.getText());
        intent.putExtra("repeat_type",repeat_type.getSelectedItemId());
        startActivity(intent);
    }

    public void from_date(View view) {
        DatePickerFrafment datePickerFrafment = new DatePickerFrafment();
        datePickerFrafment.show(getSupportFragmentManager(),"date picker");
        d=1;
    }

    public void from_time(View view) {
         TimePickerFragment timepickerfragment = new TimePickerFragment();
         timepickerfragment.show(getSupportFragmentManager(),"time picker");
         t = 1;
    }

    public void To_date(View view) {
        DatePickerFrafment datePickerFrafment = new DatePickerFrafment();
        datePickerFrafment.show(getSupportFragmentManager(),"date picker");
        d=2;
    }

    public void To_time(View view) {
        TimePickerFragment timepickerfragment = new TimePickerFragment();
        timepickerfragment.show(getSupportFragmentManager(),"time picker");
        t=2;
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int h, int m) {
        if(t==1){
            from_time.setText(h+":"+m);
        }else if (t == 2){
            to_time.setText(h+":"+m);
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int y, int m, int dd) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,y);
        c.set(Calendar.MONTH,m);
        c.set(Calendar.DAY_OF_MONTH,dd);

        String current_date_format = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        if(d==1){
            from_date.setText(current_date_format);
        }else if (d == 2){
            to_date.setText(current_date_format);
        }
    }
}
