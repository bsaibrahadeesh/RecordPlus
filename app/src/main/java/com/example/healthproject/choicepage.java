package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class choicepage extends AppCompatActivity implements View.OnClickListener{
    Button btn1,btn2,btn3,btn4;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choicepage);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        Bundle b=getIntent().getExtras();
        name=b.getString("rollno");
    }

    @Override
    public void onClick(View view) {
        if(view==btn1)
        {
            Intent i = new Intent(getApplicationContext(), adminoption1.class);
            i.putExtra("rollno1",name);
            startActivity(i);
        }
        else if(view==btn2)
        {
            Intent i = new Intent(getApplicationContext(), adminoption2.class);
            i.putExtra("rollno1",name);
            startActivity(i);
        }
        else if(view==btn3)
        {
            Intent i = new Intent(getApplicationContext(), adminoption3.class);
            i.putExtra("rollno1",name);
            startActivity(i);
        }
        else
        {
            Intent i = new Intent(getApplicationContext(), adminoption4.class);
            i.putExtra("rollno1",name);
            startActivity(i);
        }
    }
}