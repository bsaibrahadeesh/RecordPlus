package com.example.healthproject;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondFragment extends Fragment implements View.OnClickListener {

    View view;
    EditText username2, pass2;
    Button login2;
    SQLiteDatabase db1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_second, container, false);
        username2 = view.findViewById(R.id.username2);
        pass2 = view.findViewById(R.id.password2);
        login2 = view.findViewById(R.id.login2);
        login2.setOnClickListener(this);
        db1 = getActivity().openOrCreateDatabase("AdminDB", Context.MODE_PRIVATE, null);
        db1.execSQL("CREATE TABLE IF NOT EXISTS Admin(username VARCHAR,name VARCHAR,password VARCHAR);");
        return view;
    }

    @Override
    public void onClick(View view) {
            try {
                Cursor c = db1.rawQuery("SELECT * FROM Admin WHERE username='Admin'", null);
                if (!(c.moveToFirst())) {
                    db1.execSQL("INSERT INTO Admin VALUES('Admin','Admin','Admin');");
                }
            } catch (Exception e) {
                Log.i("Error", e.getMessage().toString());
            }
            if (username2.getText().toString().length() == 0 || pass2.getText().toString().length() == 0) {
                Toast.makeText(getActivity(), "Enter Username/password", Toast.LENGTH_SHORT).show();
            }
            Cursor c = db1.rawQuery("SELECT * FROM Admin WHERE username='" + username2.getText() + "' and password='" + pass2.getText() + "'", null);
            if (c.moveToFirst()) {
                username2.setText("");
                pass2.setText("");
                Intent i = new Intent(getActivity(), adminbase.class);
                i.putExtra("username", c.getString(0));
                Toast.makeText(getActivity(), "Successfully Logged In", Toast.LENGTH_LONG).show();
                startActivity(i);
            } else {
                Toast.makeText(getActivity(), "Invalid Username/password", Toast.LENGTH_SHORT).show();
            }
        }
    }
