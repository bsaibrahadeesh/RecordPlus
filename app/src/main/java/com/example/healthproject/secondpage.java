package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class secondpage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondpage);
    }
    public void Registergo(View view)
    {
        Toast.makeText(this,"You clicked on the button", Toast.LENGTH_LONG).show();
        Log.i("MainActivity","Button clicked");
        Intent j = new Intent(this, Registerpage.class);
        startActivity(j);
    }
    public void Logingo(View view)
    {
        Toast.makeText(this,"You clicked on the button", Toast.LENGTH_LONG).show();
        Log.i("MainActivity","Button clicked");
        Intent k = new Intent(this, Loginpage.class);
        startActivity(k);
    }
}