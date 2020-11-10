package com.example.healthproject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link adminstudentoption2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class adminstudentoption2 extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, AdapterView.OnItemSelectedListener{
    EditText startdate, enddate, time, description, medicine, test;
    Spinner docname;
    Button create, clear, date1, date2,timebutton;
    ImageButton share;
    int flag;
    String temp,temp1;
    SQLiteDatabase db;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public adminstudentoption2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment adminstudentoption2.
     */
    // TODO: Rename and change types and number of parameters
    public static adminstudentoption2 newInstance(String param1, String param2) {
        adminstudentoption2 fragment = new adminstudentoption2();
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
        View view =inflater.inflate(R.layout.fragment_adminstudentoption2, container, false);
        Bundle arguments = getArguments();
        temp1 = arguments.getString("message");
        startdate = view.findViewById(R.id.startdate);
        enddate = view.findViewById(R.id.enddate);
        time = view.findViewById(R.id.time);
        description = view.findViewById(R.id.description);
        medicine = view.findViewById(R.id.medicine);
        test = view.findViewById(R.id.test);
        create = view.findViewById(R.id.create);
        clear = view.findViewById(R.id.clear3);
        share = view.findViewById(R.id.shared);
        date1 = view.findViewById(R.id.date1_);
        date2 = view.findViewById(R.id.date2_);
        docname=view.findViewById(R.id.docname);
        timebutton=view.findViewById(R.id.timebutton);
        create.setOnClickListener(this);
        clear.setOnClickListener(this);
        share.setOnClickListener(this);
        date1.setOnClickListener(this);
        date2.setOnClickListener(this);
        timebutton.setOnClickListener(this);
        String Doctorname[]={"Doctor 1","Doctor 2","Doctor 3","Doctor 4"};
        ArrayAdapter<String> adap2=new ArrayAdapter<String>(getActivity(),R.layout.colour_spinner_layout,Doctorname);
        adap2.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        docname.setAdapter(adap2);
        docname.setOnItemSelectedListener(this);
        db = getActivity().openOrCreateDatabase("AdminDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS report(rollno VARCHAR,doctorname VARCHAR,startdate VARCHAR,enddate VARCHAR,time VARCHAR,description VARCHAR,medicine VARCHAR,test VARCHAR);");
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == date1) {
            flag=0;
            final Calendar c1 = Calendar.getInstance();
            DatePickerDialog datePickerDialog1 = new DatePickerDialog(getActivity(), this, c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DAY_OF_MONTH));
            datePickerDialog1.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePickerDialog1.show();
        }
        if (view == date2) {
            flag=1;
            final Calendar c = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePickerDialog.show();
        }
        if(view==timebutton)
        {
            final Calendar c = Calendar.getInstance();
            int mHour = c.get(Calendar.HOUR_OF_DAY);
            int mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                    this, mHour, mMinute, true);
            timePickerDialog.show();

        }
        if (view == create) {
            if(temp=="")
            {
                Toast.makeText(getActivity(), "select doctor name", Toast.LENGTH_SHORT).show();
            }
            else
            {
                if(startdate.getText().toString().length()==0)
                {
                    Toast.makeText(getActivity(), "Enter Patient arrival date", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(enddate.getText().toString().length()==0)
                    {
                        Toast.makeText(getActivity(), "Enter Patient discharge date", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        if(description.getText().toString().length()==0)
                        {
                            Toast.makeText(getActivity(), "Enter description", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            if(medicine.getText().toString().length()==0)
                            {
                                Toast.makeText(getActivity(), "Enter medicine details", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                if(test.getText().toString().length()==0)
                                {
                                    Toast.makeText(getActivity(), "Enter test taken", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    try{
                                        db.execSQL("INSERT INTO report VALUES('" + temp1 +"','" +temp+ "','" + startdate.getText() +
                                                "','" + enddate.getText() +"','" + time.getText() +"','" + description.getText() +"','" + medicine.getText() +"','" + test.getText() +"');");
                                        Log.i("DB", "Added");
                                        Toast.makeText(getActivity(), "Report created sucessfully", Toast.LENGTH_LONG).show();
                                    }
                                    catch (Exception e){
                                        Log.i("Error", e.getMessage().toString());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else if (view == clear)
        {
            cleared();
        }
        else if(view == share){
            Toast.makeText(getActivity(), "Report sent to their parents sucessfully", Toast.LENGTH_LONG).show();
        }
    }
    public void onDateSet(DatePicker datePicker, int dayOfMonth, int monthOfYear, int year)
    {
        if(flag==0)
        {
            startdate.setText(dayOfMonth + "-" + (monthOfYear+1)+ "-" + year);
        }
        else
        {
            enddate.setText(dayOfMonth + "-" + (monthOfYear+1)+ "-" + year);
        }
    }
    public void cleared()
    {
        docname.setSelection(0);
        startdate.setText("");
        enddate.setText("");
        time.setText("");
        description.setText("");
        medicine.setText("");;
        test.setText("");
    }
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        time.setText(hourOfDay + ":" + minute);
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