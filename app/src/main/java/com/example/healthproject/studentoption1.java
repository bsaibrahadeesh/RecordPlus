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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link studentoption1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class studentoption1 extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    TextView rollno;
    EditText name, phno, parentno1, parentno2, email, roomno, pass1, pass2;
    Spinner hostel;
    Button modify, save, clear;
    SQLiteDatabase db;
    int flag, flag1;
    String temp, temp1;
    ArrayAdapter<String> adap2;
    AlertDialog.Builder al;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public studentoption1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment studentoption1.
     */
    // TODO: Rename and change types and number of parameters
    public static studentoption1 newInstance(String param1, String param2) {
        studentoption1 fragment = new studentoption1();
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
        View view = inflater.inflate(R.layout.fragment_studentoption1, container, false);
        Bundle arguments = getArguments();
        temp = arguments.getString("message");
        rollno = view.findViewById(R.id.rollno2_);
        name = view.findViewById(R.id.Name2_);
        phno = view.findViewById(R.id.Phno2_);
        parentno1 = view.findViewById(R.id.parent11_);
        parentno2 = view.findViewById(R.id.parent12_);
        email = view.findViewById(R.id.Email2_);
        roomno = view.findViewById(R.id.roomno2_);
        modify = view.findViewById(R.id.Modify1_);
        save = view.findViewById(R.id.Save1_);
        clear = view.findViewById(R.id.Clear1_);
        hostel = (Spinner) view.findViewById(R.id.Hostel1_);
        pass1 = view.findViewById(R.id.pwd1_);
        pass2 = view.findViewById(R.id.pwd2_);
        modify.setOnClickListener(this);
        save.setOnClickListener(this);
        clear.setOnClickListener(this);
        String hostelname[] = {"Mythreyi Bhavanam", "Gargi Bhavanam", "Yagnavalkya Bhavanam", "Yagnavalkya Bhavanam Annexe", "Agasthya Bhavanam", "Vasishta Bhavanam ", "Nachiketas Bhavanam", "Sri Vyasa Maharishi Bhavanam", "Gauthama Bhavanam"};
        adap2 = new ArrayAdapter<String>(getActivity(), R.layout.colour_spinner_layout, hostelname);
        adap2.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        hostel.setAdapter(adap2);
        hostel.setOnItemSelectedListener(this);
        flag = -1;
        flag1 = -1;
        db = getActivity().openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Student(rollno VARCHAR,name VARCHAR,password VARCHAR,emailid VARCHAR,phonenumber VARCHAR,dateofbirth VARCHAR,parentnumber1 VARCHAR,parentnumber2 VARCHAR,hostel VARCHAR,roomno VARCHAR);");
        Cursor c = db.rawQuery("SELECT * FROM Student WHERE rollno='" + temp + "'", null);
        c.moveToFirst();
        rollno.setText(c.getString(0));
        name.setText(c.getString(1));
        email.setText(c.getString(3));
        phno.setText(c.getString(4));
        parentno1.setText(c.getString(6));
        parentno2.setText(c.getString(7));
        int spinnerPosition = adap2.getPosition(c.getString(8));
        hostel.setSelection(spinnerPosition);
        roomno.setText(c.getString(9));
        name.setEnabled(false);
        phno.setEnabled(false);
        parentno1.setEnabled(false);
        parentno2.setEnabled(false);
        email.setEnabled(false);
        roomno.setEnabled(false);
        hostel.setEnabled(false);
        pass1.setEnabled(false);
        pass2.setEnabled(false);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == modify) {
            if (flag == -1) {
                name.setEnabled(true);
                phno.setEnabled(true);
                parentno1.setEnabled(true);
                parentno2.setEnabled(true);
                email.setEnabled(true);
                roomno.setEnabled(true);
                hostel.setEnabled(true);
                pass1.setEnabled(true);
                pass2.setEnabled(true);
                flag = 0;
                Toast.makeText(getActivity(), "Modification Enabled", Toast.LENGTH_SHORT).show();
            } else {
                if (name.getText().toString().length() == 0) {
                    Toast.makeText(getActivity(), "Enter Valid name", Toast.LENGTH_SHORT).show();
                } else {
                    if (phno.getText().toString().length() != 10) {
                        Toast.makeText(getActivity(), "Enter Valid Phone number", Toast.LENGTH_SHORT).show();
                    } else {
                        if (validateEmail(email.getText().toString()) == false) {
                            Toast.makeText(getActivity(), "Enter Valid Email ID", Toast.LENGTH_SHORT).show();
                        } else {
                            if (parentno1.getText().toString().length() != 10) {
                                Toast.makeText(getActivity(), "Enter Valid Parent Phone number 1", Toast.LENGTH_SHORT).show();
                            } else {
                                if (parentno2.getText().toString().length() != 10) {
                                    Toast.makeText(getActivity(), "Enter Valid Parent Phone number 2", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (temp1 == "") {
                                        Toast.makeText(getActivity(), "Select Hostel Name", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if (roomno.getText().toString().length() == 0) {
                                            Toast.makeText(getActivity(), "Enter Valid Room Number", Toast.LENGTH_SHORT).show();
                                        }
                                        else if(pass1.getText().toString().length()>0)
                                        {
                                            if(pass1.getText().toString().length() < 6)
                                            {
                                                Toast.makeText(getActivity(), "Password length >= 6", Toast.LENGTH_SHORT).show();
                                                flag = 0;
                                            }
                                            else if(pass2.getText().toString().length() == 0)
                                            {
                                                Toast.makeText(getActivity(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                                                flag = 0;
                                            }
                                            else if(pass1.getText().toString().equals(pass2.getText().toString()) == false)
                                            {
                                                Toast.makeText(getActivity(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                                                flag = 0;
                                            }
                                            else
                                            {
                                                name.setEnabled(false);
                                                phno.setEnabled(false);
                                                parentno1.setEnabled(false);
                                                parentno2.setEnabled(false);
                                                email.setEnabled(false);
                                                roomno.setEnabled(false);
                                                hostel.setEnabled(false);
                                                pass1.setEnabled(false);
                                                pass2.setEnabled(false);
                                                flag = -1;
                                                flag1 = 0;
                                                Toast.makeText(getActivity(), "Modified details, please save them", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        else
                                        {
                                            name.setEnabled(false);
                                            phno.setEnabled(false);
                                            parentno1.setEnabled(false);
                                            parentno2.setEnabled(false);
                                            email.setEnabled(false);
                                            roomno.setEnabled(false);
                                            hostel.setEnabled(false);
                                            pass1.setEnabled(false);
                                            pass2.setEnabled(false);
                                            flag = -1;
                                            flag1 = 0;
                                            Toast.makeText(getActivity(), "Modified details, please save them", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else if (view == save) {
                if (flag == -1) {
                    if (flag1 == 0) {
                        al = new AlertDialog.Builder(getActivity());
                        al.setTitle("Verify and click save");
                        StringBuffer buffer = new StringBuffer();
                        buffer.append("Rollno: " + rollno.getText().toString() + "\n");
                        buffer.append("Name: " + name.getText().toString() + "\n");
                        buffer.append("Email id: " + email.getText().toString() + "\n");
                        buffer.append("Phone number: " + phno.getText().toString() + "\n");
                        buffer.append("Parent number1: " + parentno1.getText().toString() + "\n");
                        buffer.append("Parent number2: " + parentno2.getText().toString() + "\n");
                        buffer.append("Hostel: " + temp1 + "\n");
                        buffer.append("Room No: " + roomno.getText().toString() + "\n");
                        al.setMessage(buffer.toString());
                        al.show();
                        flag1 = 1;
                    } else if (flag1 == 1) {
                        db.execSQL("UPDATE student SET name='" + name.getText() + "',emailid='" + email.getText() + "',phonenumber='" + phno.getText() + "',parentnumber1='" + parentno1.getText() + "',parentnumber2='" + parentno2.getText() + "',hostel='" + temp1 + "',roomno='" + roomno.getText() + "' WHERE rollno='" + temp + "'");
                        Toast.makeText(getActivity(), "Successfully saved changes", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Cannot save changes, modify correctly", Toast.LENGTH_SHORT).show();
                }
            } else {
                cleared();
                flag1 = 0;
            }
    }

    public void cleared() {
        Cursor c = db.rawQuery("SELECT * FROM Student WHERE rollno='" + temp + "'", null);
        c.moveToFirst();
        rollno.setText(c.getString(0));
        name.setText(c.getString(1));
        email.setText(c.getString(3));
        phno.setText(c.getString(4));
        parentno1.setText(c.getString(6));
        parentno2.setText(c.getString(7));
        int spinnerPosition = adap2.getPosition(c.getString(8));
        hostel.setSelection(spinnerPosition);
        roomno.setText(c.getString(9));
        pass1.setText("");
        pass2.setText("");
        name.setEnabled(false);
        phno.setEnabled(false);
        parentno1.setEnabled(false);
        parentno2.setEnabled(false);
        email.setEnabled(false);
        roomno.setEnabled(false);
        hostel.setEnabled(false);
        pass1.setEnabled(false);
        pass2.setEnabled(false);
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView txt = (TextView) view;
        Log.d("spinner1", txt.getText().toString());
        temp1 = txt.getText().toString();
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
        temp1 = "";
    }

    public boolean validateEmail(String email) {

        Pattern pattern;
        Matcher matcher;
        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();

    }
}
