package com.example.healthproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class adminoption4 extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText rollno,roomno;
    Button enter,finddetails;
    Spinner hostel;
    SQLiteDatabase db;
    String temp;
    AlertDialog.Builder al;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminbase);

        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        rollno=findViewById(R.id.rollno1_);
        hostel=findViewById(R.id.Hostel1_);
        roomno=findViewById(R.id.roomno1_);
        enter=findViewById(R.id.button1);
        finddetails=findViewById(R.id.button2);
        enter.setOnClickListener(this);
        finddetails.setOnClickListener(this);
        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Student(rollno VARCHAR,name VARCHAR,password VARCHAR,emailid VARCHAR,phonenumber VARCHAR,dateofbirth VARCHAR,parentnumber1 VARCHAR,parentnumber2 VARCHAR,hostel VARCHAR,roomno VARCHAR);");
        String hostelname[]={"Mythreyi Bhavanam","Gargi Bhavanam","Yagnavalkya Bhavanam","Yagnavalkya Bhavanam Annexe","Agasthya Bhavanam","Vasishta Bhavanam ","Nachiketas Bhavanam","Sri Vyasa Maharishi Bhavanam","Gauthama Bhavanam"};
        ArrayAdapter<String> adap2=new ArrayAdapter<String>(this,R.layout.colour_spinner_layout,hostelname);
        adap2.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        hostel.setAdapter(adap2);
        hostel.setOnItemSelectedListener(this);
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
        if(view==enter)
        {
            int flag=0;
            if(rollno.getText().toString().length()==0)
            {
                Toast.makeText(getApplicationContext(), "Enter Student Rollno", Toast.LENGTH_LONG).show();
                flag=-1;
            }
            Cursor c = db.rawQuery("SELECT * FROM Student WHERE rollno='" + rollno.getText() + "'", null);
            if (c.moveToFirst()) {
                rollno.setText("");
                Intent i = new Intent(getApplicationContext(), choicepage.class);
                i.putExtra("rollno", c.getString(0));
                Toast.makeText(getApplicationContext(), "Successfully Logged In", Toast.LENGTH_LONG).show();
                startActivity(i);
            }
            else if(flag==0) {
                Toast.makeText(this, "No Such roll no Exist", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            if(temp=="" || roomno.getText().toString().length()==0)
            {
                Toast.makeText(getApplicationContext(), "Select Hostelname/Room no", Toast.LENGTH_LONG).show();
            }
            else
            {
                Cursor c = db.rawQuery("SELECT * FROM Student WHERE hostel='" + temp + "' and roomno='" +roomno.getText()+ "'", null);
                al=new AlertDialog.Builder(this);
                al.setTitle("Roommates");
                if (c.getCount() == 0) {
                    al.setMessage("No record found");
                    return;
                }
                else
                {
                    StringBuffer buffer = new StringBuffer();
                    while (c.moveToNext())
                    {
                        buffer.append("Name: " + c.getString(1) + "\n");
                        buffer.append("Roll No: " + c.getString(0) + "\n");
                        buffer.append("Mobile No: " + c.getString(4) + "\n\n");
                    }
                    al.setMessage(buffer.toString());
                }
                al.show();
            }
        }
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