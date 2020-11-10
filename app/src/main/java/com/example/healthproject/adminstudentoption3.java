package com.example.healthproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link adminstudentoption3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class adminstudentoption3 extends Fragment {
    AlertDialog.Builder al;
    SQLiteDatabase db;
    String temp1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public adminstudentoption3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment adminstudentoption3.
     */
    // TODO: Rename and change types and number of parameters
    public static adminstudentoption3 newInstance(String param1, String param2) {
        adminstudentoption3 fragment = new adminstudentoption3();
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
        View view =inflater.inflate(R.layout.fragment_adminstudentoption3, container, false);
        Bundle arguments = getArguments();
        temp1 = arguments.getString("message");
        db = getActivity().openOrCreateDatabase("AdminDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS report(rollno VARCHAR,doctorname VARCHAR,startdate VARCHAR,enddate VARCHAR,time VARCHAR,description VARCHAR,medicine VARCHAR,test VARCHAR);");
        Cursor c = db.rawQuery("SELECT * FROM report WHERE rollno='" + temp1 + "'", null);
        al=new AlertDialog.Builder(getActivity());
        al.setTitle("Reports");
        if (c.getCount() == 0) {
            al.setMessage("No Record found");
        }
        else
        {
            StringBuffer buffer1 = new StringBuffer();
            int i=1;
            while (c.moveToNext())
            {
                buffer1.append("Report-" + i + " :\n");
                buffer1.append("Doctor Name: " + c.getString(1) + "\n");
                buffer1.append("Startdate: " + c.getString(2) + "\n");
                buffer1.append("End Date: " + c.getString(3) + "\n");
                buffer1.append("Time: " + c.getString(4) + "\n");
                buffer1.append("Description: " + c.getString(5) + "\n");
                buffer1.append("Medicine: " + c.getString(6) + "\n\n");
                i = i+1;
            }
            al.setMessage(buffer1.toString());
        }
        al.show();
        return view;
    }
}