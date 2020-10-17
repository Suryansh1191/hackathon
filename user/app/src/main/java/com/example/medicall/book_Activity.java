package com.example.medicall;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class book_Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Button date;
    private Button time;
    private Button continu;
    private TextView num2;
    private EditText name,number,age,sex,dob,address,syms;
    private String EnterDate,EnterName,EnterSex,EnterPhn,EnterAge,Enterdob,EnterAddress,EnterSyms,EnterDoctor;
    private LinearLayout layout;
    private Button continu2;
    Spinner spinner;
    Member member;
    private DatabaseReference rootRef;
    private FirebaseAuth mAuth;
    private LinearLayout layout_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_);

        spinner = findViewById(R.id.book_doctor);
        name = findViewById(R.id.book_name);
        number = findViewById(R.id.book_number);
        age = findViewById(R.id.book_age);
        sex = findViewById(R.id.book_sex);
        dob = findViewById(R.id.book_dob);
        address = findViewById(R.id.book_address);
        syms = findViewById(R.id.book_syms);

        date = findViewById(R.id.seclectDate);
        continu = findViewById(R.id.contiu);
        layout = findViewById(R.id.linerlayout1);
        num2 = findViewById(R.id.num2);
        continu2 = findViewById(R.id.contiu2);
        layout_two = findViewById(R.id.linerlayout2);

        rootRef = FirebaseDatabase.getInstance().getReference().child("hospital");
        mAuth = FirebaseAuth.getInstance();
    /*===================================list======================*/

        List<String> categories = new ArrayList<>();
        categories.add(0, "choose Doctor");
        categories.add("Dr Mohan Lal");
        categories.add("Dr Meenal Singh");
        categories.add("Dr Anupam Khair");
        categories.add("Dr Dara Singh");
        
        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("choose event")) {

                } else {
                    String item = adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(book_Activity.this, item, Toast.LENGTH_SHORT).show();
                    EnterDoctor = item;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
        continu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*=======================initialsing===============*/
                EnterName  = name.getText().toString();
                EnterSex = sex.getText().toString();
                EnterPhn  = number.getText().toString();
                EnterAge  = age.getText().toString();



                if (EnterName.equals("") || EnterPhn.equals("") || EnterAge.equals("") || EnterSex.equals(""))
                {
                    Toast.makeText(book_Activity.this, "please enter all ", Toast.LENGTH_SHORT).show();
                }
                else {
                    layout.setVisibility(View.INVISIBLE);
                    continu.setVisibility(View.INVISIBLE);
                    layout_two.setVisibility(View.VISIBLE);
                    continu2.setVisibility(View.VISIBLE);
                }

            }
        });
        continu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Enterdob  = dob.getText().toString();
                EnterAddress  = address.getText().toString();
                EnterSyms  = syms.getText().toString();
                if (EnterAddress.equals("")  || Enterdob.equals("") || EnterSyms.equals(""))
                {
                    Toast.makeText(book_Activity.this, "please enter all details first", Toast.LENGTH_SHORT).show();
                }
                else {
                    layout_two.setVisibility(View.INVISIBLE);
                    continu2.setVisibility(View.INVISIBLE);
                    addData();
                }

            }
        });
    }

    private void addData() {
        String userId= FirebaseAuth.getInstance().getUid();
        HashMap<String, Object> profileMap = new HashMap<>();
        profileMap.put("", userId);
        profileMap.put("name", EnterName);
        profileMap.put("status", EnterPhn);
        profileMap.put("image", EnterSex);
        rootRef.child(EnterDate).child(EnterDoctor).child(userId)
                .updateChildren(profileMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(book_Activity.this, "Booked....", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        String dat = i + "/" + (i1+1) + "/" + i2;
        date.setText(dat);
        EnterDate = i + " " + (i1+1) + " " + i2;

    }

}