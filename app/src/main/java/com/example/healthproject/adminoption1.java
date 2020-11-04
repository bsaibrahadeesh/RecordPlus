package com.example.healthproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class adminoption1 extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{
    TextView rollno;
    EditText name,phno,parentno1,parentno2,email,roomno;
    Spinner hostel;
    Button modify,save,clear;
    SQLiteDatabase db;
    int flag,flag1;
    String temp,temp1;
    ArrayAdapter<String> adap2;
    AlertDialog.Builder al;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminoption1);

        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        rollno=findViewById(R.id.rollno2_);
        name=findViewById(R.id.Name2_);
        phno=findViewById(R.id.Phno2_);
        parentno1=findViewById(R.id.parent11_);
        parentno2=findViewById(R.id.parent12_);
        email=findViewById(R.id.Email2_);
        roomno=findViewById(R.id.roomno2_);
        modify=findViewById(R.id.Modify1_);
        save=findViewById(R.id.Save1_);
        clear=findViewById(R.id.Clear1_);
        hostel=(Spinner)findViewById(R.id.Hostel1_);
        modify.setOnClickListener(this);
        save.setOnClickListener(this);
        clear.setOnClickListener(this);
        Bundle b=getIntent().getExtras();
        temp=b.getString("rollno1");
        String hostelname[]={"Mythreyi Bhavanam","Gargi Bhavanam","Yagnavalkya Bhavanam","Yagnavalkya Bhavanam Annexe","Agasthya Bhavanam","Vasishta Bhavanam ","Nachiketas Bhavanam","Sri Vyasa Maharishi Bhavanam","Gauthama Bhavanam"};
        adap2=new ArrayAdapter<String>(this,R.layout.colour_spinner_layout,hostelname);
        adap2.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        hostel.setAdapter(adap2);
        hostel.setOnItemSelectedListener(this);
        flag=-1;
        flag1=0;
        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Student(rollno VARCHAR,name VARCHAR,password VARCHAR,emailid VARCHAR,phonenumber VARCHAR,dateofbirth VARCHAR,parentnumber1 VARCHAR,parentnumber2 VARCHAR,hostel VARCHAR,roomno VARCHAR);");
        Cursor c = db.rawQuery("SELECT * FROM Student WHERE rollno='" + temp + "'", null);
        c.moveToFirst();
        rollno.setText(c.getString(0));
        name.setText(c.getString(1));
        email.setText(c.getString(3));
        phno.setText(c.getString(4));
        parentno1.setText(c.getString(6));
        parentno2.setText(c.getString(7));
        int spinnerPosition = adap2.getPosition(c.getString(8));
        hostel.setSelection(spinnerPosition);
        roomno.setText(c.getString(9));
        name.setEnabled(false);
        phno.setEnabled(false);
        parentno1.setEnabled(false);
        parentno2.setEnabled(false);
        email.setEnabled(false);
        roomno.setEnabled(false);
        hostel.setEnabled(false);
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
        if(view==modify)
        {
            if(flag==-1)
            {
                name.setEnabled(true);
                phno.setEnabled(true);
                parentno1.setEnabled(true);
                parentno2.setEnabled(true);
                email.setEnabled(true);
                roomno.setEnabled(true);
                hostel.setEnabled(true);
                flag=0;
            }
            else
            {
                if(name.getText().toString().length()==0)
                {
                    Toast.makeText(this, "Enter Valid name", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(phno.getText().toString().length()!=10)
                    {
                        Toast.makeText(this, "Enter Valid Phone number", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        if(validateEmail(email.getText().toString())==false)
                        {
                            Toast.makeText(this, "Enter Valid email id", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            if(parentno1.getText().toString().length()!=10)
                            {
                                Toast.makeText(this, "Enter Valid Parent number 1", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                if(parentno2.getText().toString().length()!=10)
                                {
                                    Toast.makeText(this, "Enter Valid Parent number 2", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    if(temp1=="")
                                    {
                                        Toast.makeText(this, "Select hostel", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        if(roomno.getText().toString().length()==0)
                                        {
                                            Toast.makeText(this, "Enter Valid room no", Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            name.setEnabled(false);
                                            phno.setEnabled(false);
                                            parentno1.setEnabled(false);
                                            parentno2.setEnabled(false);
                                            email.setEnabled(false);
                                            roomno.setEnabled(false);
                                            hostel.setEnabled(false);
                                            flag=-1;
                                            flag1=0;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else if(view==save)
        {
           if(flag==-1)
           {
               if(flag1==0)
               {
                   al=new AlertDialog.Builder(this);
                   al.setTitle("Verify");
                   StringBuffer buffer = new StringBuffer();
                   buffer.append("Rollno: " + rollno.getText().toString() + "\n");
                   buffer.append("Name: " + name.getText().toString() + "\n");
                   buffer.append("Email id: " + email.getText().toString() + "\n");
                   buffer.append("Phone number: " + phno.getText().toString() + "\n");
                   buffer.append("Parent number1: " + parentno1.getText().toString() + "\n");
                   buffer.append("Parent number2: " + parentno2.getText().toString() + "\n");
                   buffer.append("Hostel: " + temp1 + "\n");
                   buffer.append("Room No: " + roomno.getText().toString() + "\n");
                   al.setMessage(buffer.toString());
                   al.show();
                   flag1=1;
               }
               else
               {
                   db.execSQL("UPDATE student SET name='" + name.getText() + "',emailid='"+email.getText()+"',phonenumber='"+phno.getText()+"',parentnumber1='"+parentno1.getText()+"',parentnumber2='"+parentno2.getText()+"',hostel='"+temp1+"',roomno='"+roomno.getText()+"' WHERE rollno='" + temp + "'");
                   Toast.makeText(this, "Successfully saved changes", Toast.LENGTH_SHORT).show();
               }
           }
           else
           {
               Toast.makeText(this, "Cannot save changes modify correctly", Toast.LENGTH_SHORT).show();
           }
        }
        else
        {
            cleared();
            flag1=0;
        }
    }
    public void cleared()
    {
        Cursor c = db.rawQuery("SELECT * FROM Student WHERE rollno='" + temp + "'", null);
        c.moveToFirst();
        rollno.setText(c.getString(0));
        name.setText(c.getString(1));
        email.setText(c.getString(3));
        phno.setText(c.getString(4));
        parentno1.setText(c.getString(6));
        parentno2.setText(c.getString(7));
        int spinnerPosition = adap2.getPosition(c.getString(8));
        hostel.setSelection(spinnerPosition);
        roomno.setText(c.getString(9));
        name.setEnabled(false);
        phno.setEnabled(false);
        parentno1.setEnabled(false);
        parentno2.setEnabled(false);
        email.setEnabled(false);
        roomno.setEnabled(false);
        hostel.setEnabled(false);
    }
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        TextView txt = (TextView) view;
        Log.d("spinner1",txt.getText().toString());
        temp1=txt.getText().toString();
    }
    public void onNothingSelected(AdapterView<?> adapterView)
    {
        temp1="";
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