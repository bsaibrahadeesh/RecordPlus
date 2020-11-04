package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class adminoption3 extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, AdapterView.OnItemSelectedListener {
    EditText startdate, enddate, time, description, medicine, test;
    Spinner docname;
    Button create, clear, date1, date2,timebutton;
    ImageButton share;
    int flag,flag1;
    String temp,temp1;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminoption3);

        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        startdate = findViewById(R.id.startdate);
        enddate = findViewById(R.id.enddate);
        time = findViewById(R.id.time);
        description = findViewById(R.id.description);
        medicine = findViewById(R.id.medicine);
        test = findViewById(R.id.test);
        create = findViewById(R.id.create);
        clear = findViewById(R.id.clear3);
        share = findViewById(R.id.shared);
        date1 = findViewById(R.id.date1_);
        date2 = findViewById(R.id.date2_);
        docname=findViewById(R.id.docname);
        timebutton=findViewById(R.id.timebutton);
        create.setOnClickListener(this);
        clear.setOnClickListener(this);
        share.setOnClickListener(this);
        date1.setOnClickListener(this);
        date2.setOnClickListener(this);
        timebutton.setOnClickListener(this);
        String Doctorname[]={"Doctor 1","Doctor 2","Doctor 3","Doctor 4"};
        ArrayAdapter<String> adap2=new ArrayAdapter<String>(this,R.layout.colour_spinner_layout,Doctorname);
        adap2.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        docname.setAdapter(adap2);
        docname.setOnItemSelectedListener(this);
        Bundle b=getIntent().getExtras();
        temp1=b.getString("rollno1");
        db = openOrCreateDatabase("AdminDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS report(rollno VARCHAR,doctorname VARCHAR,startdate VARCHAR,enddate VARCHAR,time VARCHAR,description VARCHAR,medicine VARCHAR,test VARCHAR);");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view == date1) {
            flag=0;
            final Calendar c1 = Calendar.getInstance();
            DatePickerDialog datePickerDialog1 = new DatePickerDialog(this, this, c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DAY_OF_MONTH));
            datePickerDialog1.show();
        }
        if (view == date2) {
            flag=1;
            final Calendar c = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        }
        if(view==timebutton)
        {
            final Calendar c = Calendar.getInstance();
            int mHour = c.get(Calendar.HOUR_OF_DAY);
            int mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    this, mHour, mMinute, true);
            timePickerDialog.show();

        }
        if (view == create) {
            if(temp=="")
            {
                Toast.makeText(getApplicationContext(), "select doctor name", Toast.LENGTH_SHORT).show();
            }
            else
            {
                if(startdate.getText().toString().length()==0)
                {
                    Toast.makeText(getApplicationContext(), "Enter Patient arrival date", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(enddate.getText().toString().length()==0)
                    {
                        Toast.makeText(getApplicationContext(), "Enter Patient depart date", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        if(description.getText().toString().length()==0)
                        {
                            Toast.makeText(getApplicationContext(), "Enter description", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            if(medicine.getText().toString().length()==0)
                            {
                                Toast.makeText(getApplicationContext(), "Enter medicines details", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                if(test.getText().toString().length()==0)
                                {
                                    Toast.makeText(getApplicationContext(), "Enter test to be taken", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    try{
                                            db.execSQL("INSERT INTO report VALUES('" + temp1 +"','" +temp+ "','" + startdate.getText() +
                                                    "','" + enddate.getText() +"','" + time.getText() +"','" + description.getText() +"','" + medicine.getText() +"','" + test.getText() +"');");
                                            Log.i("DB", "Added");
                                            Toast.makeText(getApplicationContext(), "Report created sucessfully", Toast.LENGTH_LONG).show();
                                        }
                                    catch (Exception e){
                                        Log.i("Error", e.getMessage().toString());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else if (view == clear)
        {
            cleared();
        }
        else{
            Toast.makeText(getApplicationContext(), "Report send to their parents sucessfully", Toast.LENGTH_LONG).show();
        }
    }
    public void onDateSet(DatePicker datePicker, int dayOfMonth, int monthOfYear, int year)
    {
      if(flag==0)
      {
          startdate.setText(dayOfMonth + "-" + (monthOfYear+1)+ "-" + year);
      }
      else
      {
          enddate.setText(dayOfMonth + "-" + (monthOfYear+1)+ "-" + year);
      }
    }
    public void cleared()
    {
        docname.setSelection(0);
        startdate.setText("");
        enddate.setText("");
        time.setText("");
        description.setText("");
        medicine.setText("");;
        test.setText("");
    }
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        time.setText(hourOfDay + ":" + minute);
    }
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        TextView txt = (TextView) view;
        Log.d("spinner1",txt.getText().toString());
        temp=txt.getText().toString();
    }
    public void onNothingSelected(AdapterView<?> adapterView)
    {
        temp="";
    }

}
