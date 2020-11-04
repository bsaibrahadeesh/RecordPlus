package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class secondpage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondpage);

        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
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