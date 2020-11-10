package com.example.healthproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link adminbaseoption2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class adminbaseoption2 extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener{
    EditText roomno;
    Button finddetails;
    Spinner hostel;
    SQLiteDatabase db;
    String temp;
    AlertDialog.Builder al;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public adminbaseoption2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment adminbaseoption2.
     */
    // TODO: Rename and change types and number of parameters
    public static adminbaseoption2 newInstance(String param1, String param2) {
        adminbaseoption2 fragment = new adminbaseoption2();
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
        View view =inflater.inflate(R.layout.fragment_adminbaseoption2, container, false);
        hostel=view.findViewById(R.id.Hostel1_);
        roomno=view.findViewById(R.id.roomno1_);
        finddetails=view.findViewById(R.id.button2);
        finddetails.setOnClickListener(this);
        db = getActivity().openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Student(rollno VARCHAR,name VARCHAR,password VARCHAR,emailid VARCHAR,phonenumber VARCHAR,dateofbirth VARCHAR,parentnumber1 VARCHAR,parentnumber2 VARCHAR,hostel VARCHAR,roomno VARCHAR);");
        String hostelname[]={"Mythreyi Bhavanam","Gargi Bhavanam","Yagnavalkya Bhavanam","Yagnavalkya Bhavanam Annexe","Agasthya Bhavanam","Vasishta Bhavanam ","Nachiketas Bhavanam","Sri Vyasa Maharishi Bhavanam","Gauthama Bhavanam"};
        ArrayAdapter<String> adap2=new ArrayAdapter<String>(getActivity(),R.layout.colour_spinner_layout,hostelname);
        adap2.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        hostel.setAdapter(adap2);
        hostel.setOnItemSelectedListener(this);
        return view;
    }
    @Override
    public void onClick(View view) {
        if(temp=="" || roomno.getText().toString().length()==0)
        {
            Toast.makeText(getActivity(), "Select Hostelname & Room number", Toast.LENGTH_LONG).show();
        }
        else {
            Cursor c = db.rawQuery("SELECT * FROM Student WHERE hostel='" + temp + "' and roomno='" + roomno.getText() + "'", null);
            al = new AlertDialog.Builder(getActivity());
            al.setTitle("Roommates");
            if (c.getCount() == 0) {
                al.setMessage("No Record found");
                return;
            } else {
                StringBuffer buffer = new StringBuffer();
                while (c.moveToNext()) {
                    buffer.append("Name: " + c.getString(1) + "\n");
                    buffer.append("Roll No: " + c.getString(0) + "\n");
                    buffer.append("Mobile No: " + c.getString(4) + "\n\n");
                }
                al.setMessage(buffer.toString());
            }
            al.show();
        }
    }
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        TextView txt = (TextView) view;
        Log.d("spinner1",txt.getText().toString());
        temp=txt.getText().toString();
    }

    public void onNothingSelected(AdapterView<?> adapterView)
    {
        temp="";
    }

}