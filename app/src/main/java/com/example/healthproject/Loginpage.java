package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Loginpage extends AppCompatActivity implements View.OnClickListener {
    EditText username1, pass1, username2, pass2;
    Button login1, login2;
    SQLiteDatabase db, db1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        username1 = findViewById(R.id.username1);
        pass1 = findViewById(R.id.password1);
        login1 = findViewById(R.id.login1);
        username2 = findViewById(R.id.username2);
        pass2 = findViewById(R.id.password2);
        login2 = findViewById(R.id.login2);
        login1.setOnClickListener(this);
        login2.setOnClickListener(this);
        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Student(rollno VARCHAR,name VARCHAR,password VARCHAR,emailid VARCHAR,phonenumber VARCHAR,dateofbirth VARCHAR,parentnumber1 VARCHAR,parentnumber2 VARCHAR,hostel VARCHAR,roomno VARCHAR);");
        db1 = openOrCreateDatabase("AdminDB", Context.MODE_PRIVATE, null);
        db1.execSQL("CREATE TABLE IF NOT EXISTS Admin(username VARCHAR,name VARCHAR,password VARCHAR);");
    }

    @Override
    public void onClick(View view) {
        if (view == login1) {
            if (username1.getText().toString().length() == 0 || pass1.getText().toString().length() == 0) {
                Toast.makeText(this, "Enter Rollno/password", Toast.LENGTH_SHORT).show();
            }
            Cursor c = db.rawQuery("SELECT * FROM Student WHERE rollno='" + username1.getText() + "' and password='" + pass1.getText() + "'", null);
            if (c.moveToFirst()) {
                username1.setText("");
                pass1.setText("");
                Intent i = new Intent(getApplicationContext(), userbase.class);
                i.putExtra("rollno", c.getString(0));
                Toast.makeText(getApplicationContext(), "Successfully Logged In", Toast.LENGTH_LONG).show();
                startActivity(i);
            } else {
                Toast.makeText(this, "Invalid Rollno/password", Toast.LENGTH_SHORT).show();
            }
        } else {
            try {
                Cursor c = db1.rawQuery("SELECT * FROM Admin WHERE username='Admin'", null);
                if (!(c.moveToFirst())) {
                    db1.execSQL("INSERT INTO Admin VALUES('Admin','Admin','Admin');");
                }
            } catch (Exception e) {
                Log.i("Error", e.getMessage().toString());
            }
            if (username2.getText().toString().length() == 0 || pass2.getText().toString().length() == 0) {
                Toast.makeText(this, "Enter Username/password", Toast.LENGTH_SHORT).show();
            }
            Cursor c = db1.rawQuery("SELECT * FROM Admin WHERE username='" + username2.getText() + "' and password='" + pass2.getText() + "'", null);
            if (c.moveToFirst()) {
                username2.setText("");
                pass2.setText("");
                Intent i = new Intent(getApplicationContext(), adminbase.class);
                i.putExtra("username", c.getString(0));
                Toast.makeText(getApplicationContext(), "Successfully Logged In", Toast.LENGTH_LONG).show();
                startActivity(i);
            } else {
                Toast.makeText(this, "Invalid Username/password", Toast.LENGTH_SHORT).show();
            }
        }
    }
}