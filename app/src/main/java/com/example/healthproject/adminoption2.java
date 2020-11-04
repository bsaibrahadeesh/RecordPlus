package com.example.healthproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class adminoption2 extends AppCompatActivity {
    AlertDialog.Builder al;
    SQLiteDatabase db;
    String temp1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminoption2);

        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle b=getIntent().getExtras();
        temp1=b.getString("rollno1");
        db = openOrCreateDatabase("AdminDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS report(rollno VARCHAR,doctorname VARCHAR,startdate VARCHAR,enddate VARCHAR,time VARCHAR,description VARCHAR,medicine VARCHAR,test VARCHAR);");
        Cursor c = db.rawQuery("SELECT * FROM report WHERE rollno='" + temp1 + "'", null);
            al=new AlertDialog.Builder(this);
            al.setTitle("Report");
        if (c.getCount() == 0) {
            al.setMessage("No record found");
            return;
        }
        else
        {
            StringBuffer buffer1 = new StringBuffer();
            while (c.moveToNext())
            {
                buffer1.append("Doctor Name: " + c.getString(1) + "\n");
                buffer1.append("Startdate: " + c.getString(2) + "\n");
                buffer1.append("End Date: " + c.getString(3) + "\n");
                buffer1.append("Time: " + c.getString(4) + "\n");
                buffer1.append("Description: " + c.getString(5) + "\n");
                buffer1.append("Medicine: " + c.getString(6) + "\n");
            }
            al.setMessage(buffer1.toString());
        }
        al.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}