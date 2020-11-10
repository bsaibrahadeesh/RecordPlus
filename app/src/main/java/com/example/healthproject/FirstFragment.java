package com.example.healthproject;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FirstFragment extends Fragment implements View.OnClickListener {

    View view;
    Button login1;
    EditText username1, pass1;
    SQLiteDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_first, container, false);

        username1 = view.findViewById(R.id.username1);
        pass1 = view.findViewById(R.id.password1);
        login1 = view.findViewById(R.id.login1);
        login1.setOnClickListener(this);
        db = getActivity().openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Student(rollno VARCHAR,name VARCHAR,password VARCHAR,emailid VARCHAR,phonenumber VARCHAR,dateofbirth VARCHAR,parentnumber1 VARCHAR,parentnumber2 VARCHAR,hostel VARCHAR,roomno VARCHAR);");
        return view;

    }

    @Override
    public void onClick(View view) {
            if (username1.getText().toString().length() == 0 || pass1.getText().toString().length() == 0) {
                Toast.makeText(getActivity(), "Enter Rollno/password", Toast.LENGTH_SHORT).show();
            }
            Cursor c = db.rawQuery("SELECT * FROM Student WHERE rollno='" + username1.getText() + "' and password='" + pass1.getText() + "'", null);
            if (c.moveToFirst()) {
                username1.setText("");
                pass1.setText("");
                Intent i = new Intent(getActivity(), userbase.class);
                i.putExtra("rollno", c.getString(0));
                Toast.makeText(getActivity(), "Successfully Logged In", Toast.LENGTH_LONG).show();
                startActivity(i);
            } else {
                Toast.makeText(getActivity(), "Invalid Rollno/Password", Toast.LENGTH_SHORT).show();
            }
    }

}
