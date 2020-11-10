package com.example.healthproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
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
import android.widget.CheckedTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registerpage extends AppCompatActivity implements View.OnClickListener,DatePickerDialog.OnDateSetListener,AdapterView.OnItemSelectedListener{
    EditText rollno,name,pass1,pass2,emailid,roomno,phno,dob,phno1,phno2;
    Spinner hostel;
    Button date,submit,clear;
     String temp;
    AlertDialog.Builder al;
    SQLiteDatabase db;
    int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerpage);

        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        rollno=findViewById(R.id.rollno_);
        name=findViewById(R.id.Name_);
        pass1=findViewById(R.id.Password_);
        pass2=findViewById(R.id.Password1_);
        emailid=findViewById(R.id.Email_);
        hostel=(Spinner)findViewById(R.id.Hostel_);
        roomno=findViewById(R.id.Roomno_);
        phno=findViewById(R.id.Phone_);
        dob=findViewById(R.id.Dob_);
        date=findViewById(R.id.Date_);
        submit=findViewById(R.id.Submit_);
        clear=findViewById(R.id.clear_);
        phno1=findViewById(R.id.Phone1_);
        phno2=findViewById(R.id.Phone2_);
        date.setOnClickListener(this);
        submit.setOnClickListener(this);
        clear.setOnClickListener(this);
        String hostelname[]={"Mythreyi Bhavanam","Gargi Bhavanam","Yagnavalkya Bhavanam","Yagnavalkya Bhavanam Annexe","Agasthya Bhavanam","Vasishta Bhavanam ","Nachiketas Bhavanam","Sri Vyasa Maharishi Bhavanam","Gauthama Bhavanam"};
        ArrayAdapter<String> adap2=new ArrayAdapter<String>(this,R.layout.colour_spinner_layout,hostelname);
        adap2.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        hostel.setAdapter(adap2);
        hostel.setOnItemSelectedListener(this);
        flag=0;
        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Student(rollno VARCHAR,name VARCHAR,password VARCHAR,emailid VARCHAR,phonenumber VARCHAR,dateofbirth VARCHAR,parentnumber1 VARCHAR,parentnumber2 VARCHAR,hostel VARCHAR,roomno VARCHAR);");
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
    public void onClick(View view)
    {
        if(view==date)
        {
            final Calendar c = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePickerDialog.show();
        }
        else if(view==clear)
        {
            cleared();
        }
        else
        {
            if(rollno.getText().toString().length()!=16)
            {
                Toast.makeText(this, "Enter valid Roll number", Toast.LENGTH_SHORT).show();
                flag=0;
            }
            else
            {
                if(name.getText().toString().length()==0)
                {
                    Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
                    flag=0;
                }
                else
                {
                    if(pass1.getText().toString().length()<6)
                    {
                        Toast.makeText(this, "Password length >= 6", Toast.LENGTH_SHORT).show();
                        flag=0;
                    }
                    else
                    {
                        if(pass2.getText().toString().length()==0)
                        {
                            Toast.makeText(this, "Enter Confirm password", Toast.LENGTH_SHORT).show();
                            flag=0;
                        }
                        else if(pass1.getText().toString().equals(pass2.getText().toString())==false)
                        {
                            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                            flag=0;
                        }
                        else
                        {
                            if(validateEmail(emailid.getText().toString())==false)
                            {
                                Toast.makeText(this, "Enter Valid email id", Toast.LENGTH_SHORT).show();
                                flag=0;
                            }
                            else
                            {
                                if(phno.getText().toString().length()!=10)
                                {
                                    Toast.makeText(this, "Enter valid phone number", Toast.LENGTH_SHORT).show();
                                    flag=0;
                                }
                                else
                                {
                                    if(dob.getText().toString().length()==0)
                                    {
                                        Toast.makeText(this, "Select D.O.B", Toast.LENGTH_SHORT).show();
                                        flag=0;
                                    }
                                    else
                                    {
                                        if(phno1.getText().toString().length()!=10)
                                        {
                                            Toast.makeText(this, "Enter valid Parent phone number 1", Toast.LENGTH_SHORT).show();
                                            flag=0;
                                        }
                                        else
                                        {
                                            if(phno2.getText().toString().length()!=10)
                                            {
                                                Toast.makeText(this, "Enter valid Parent phone number 2", Toast.LENGTH_SHORT).show();
                                                flag=0;
                                            }
                                            if(temp.length()==0)
                                            {
                                                Toast.makeText(getApplicationContext(), "Select a Hostel", Toast.LENGTH_SHORT).show();
                                                flag=0;
                                            }
                                            else
                                            {
                                                if(roomno.getText().toString().length()==0)
                                                {
                                                    Toast.makeText(getApplicationContext(), "Enter hostel Room number", Toast.LENGTH_SHORT).show();
                                                    flag=0;
                                                }
                                                else
                                                {
                                                    if(flag==0)
                                                    {
                                                        al=new AlertDialog.Builder(this);
                                                        al.setTitle("Verify and click save");
                                                        StringBuffer buffer = new StringBuffer();
                                                        buffer.append("Rollno: " + rollno.getText().toString() + "\n");
                                                        buffer.append("Name: " + name.getText().toString() + "\n");
                                                        buffer.append("Email id: " + emailid.getText().toString() + "\n");
                                                        buffer.append("Phone number: " + phno.getText().toString() + "\n");
                                                        buffer.append("Date of Birth: " + dob.getText().toString() + "\n");
                                                        buffer.append("Parent number1: " + phno1.getText().toString() + "\n");
                                                        buffer.append("Parent number2: " + phno2.getText().toString() + "\n");
                                                        buffer.append("Hostel: " + temp + "\n");
                                                        buffer.append("Room No: " + roomno.getText().toString() + "\n");
                                                        al.setMessage(buffer.toString());
                                                        al.show();
                                                        flag=1;
                                                    }
                                                    else
                                                    {
                                                        try{
                                                            Cursor c = db.rawQuery("SELECT * FROM Student WHERE rollno='" + rollno.getText() + "'", null);
                                                            if (c.moveToFirst())
                                                            {
                                                                Log.i("DB", "Exists");
                                                                Toast.makeText(getApplicationContext(), "User already Exists", Toast.LENGTH_LONG).show();
                                                            }
                                                            else{

                                                                db.execSQL("INSERT INTO Student VALUES('" + rollno.getText() +"','" + name.getText()+ "','" + pass1.getText() +
                                                                        "','" + emailid.getText() +"','" + phno.getText() +"','" + dob.getText() +"','" + phno1.getText() +"','" + phno2.getText() +"','" + temp +"','" + roomno.getText() + "');");
                                                                Log.i("DB", "Added");
                                                                Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_LONG).show();
                                                            }
                                                        }catch (Exception e){
                                                            Log.i("Error", e.getMessage().toString());
                                                        }
                                                        Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_SHORT).show();
                                                        cleared();
                                                        Intent i = new Intent(this, secondpage.class);
                                                        startActivity(i);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public void cleared()
    {
        emailid.setText("");
        phno.setText("");
        name.setText("");
        pass1.setText("");
        pass2.setText("");
        rollno.setText("");
        roomno.setText("");
        emailid.requestFocus();
        dob.setText("");
        hostel.setSelection(0);
        flag=0;
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
    public void onDateSet(DatePicker datePicker, int dayOfMonth, int monthOfYear, int year)
    {
        dob.setText(dayOfMonth + "-" + (monthOfYear+1)+ "-" + year);
    }
    public boolean validateEmail(String email) {

        Pattern pattern;
        Matcher matcher;
        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();

    }
}