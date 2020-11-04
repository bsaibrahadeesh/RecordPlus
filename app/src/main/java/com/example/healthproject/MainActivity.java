package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        b=findViewById(R.id.started);
        b.setOnClickListener(this);
    }
    public void onClick(View view)
    {
        Toast.makeText(this,"You clicked on the button", Toast.LENGTH_LONG).show();
        Log.i("MainActivity","Button clicked");
        Intent i = new Intent(this, secondpage.class);
        startActivity(i);
    }
}