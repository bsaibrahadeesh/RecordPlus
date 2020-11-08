package com.example.healthproject;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class adminbaseoption1 extends Fragment implements View.OnClickListener{
    EditText rollno;
    Button enter;
    SQLiteDatabase db;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public adminbaseoption1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment adminbaseoption1.
     */
    // TODO: Rename and change types and number of parameters
    public static adminbaseoption1 newInstance(String param1, String param2) {
        adminbaseoption1 fragment = new adminbaseoption1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_adminbaseoption1, container, false);
        rollno=view.findViewById(R.id.rollno1_);

        enter=view.findViewById(R.id.button1);
        enter.setOnClickListener(this);


        db = getActivity().openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Student(rollno VARCHAR,name VARCHAR,password VARCHAR,emailid VARCHAR,phonenumber VARCHAR,dateofbirth VARCHAR,parentnumber1 VARCHAR,parentnumber2 VARCHAR,hostel VARCHAR,roomno VARCHAR);");
        return view;
    }
    @Override
    public void onClick(View view) {
        int flag=0;
        if(rollno.getText().toString().length()==0)
        {
            Toast.makeText(getActivity(), "Enter Student Rollno", Toast.LENGTH_LONG).show();
            flag=-1;
        }
        Cursor c = db.rawQuery("SELECT * FROM Student WHERE rollno='" + rollno.getText() + "'", null);
        if (c.moveToFirst()) {
            rollno.setText("");
            Intent i = new Intent(getActivity(), choicepage.class);
            i.putExtra("rollno", c.getString(0));
            Toast.makeText(getActivity(), "Successfully Logged In", Toast.LENGTH_LONG).show();
            startActivity(i);
        }
        else if(flag==0) {
            Toast.makeText(getActivity(), "No Such roll no Exist", Toast.LENGTH_SHORT).show();
        }
    }
}