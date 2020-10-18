package com.example.medicall;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class book_Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Button date;
    private Button time;
    private Button continu;
    private TextView num2,num1,num3;
    private EditText name,number,age,sex,dob,address,syms;
    private String EnterDate,EnterName,EnterSex,EnterPhn,EnterAge,Enterdob,EnterAddress,EnterSyms,EnterDoctor;
    private LinearLayout layout;
    private Button continu2;
    String qrImgLink="linksoon";
    Spinner spinner;
    Member member;
    private ImageView QrView;
    private DatabaseReference rootRef;
    private FirebaseAuth mAuth;
    private LinearLayout layout_two;
    private RelativeLayout layout_3;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_);

        progressBar = new ProgressDialog(this);

        spinner = findViewById(R.id.book_doctor);
        name = findViewById(R.id.book_name);
        number = findViewById(R.id.book_number);
        age = findViewById(R.id.book_age);
        sex = findViewById(R.id.book_sex);
        dob = findViewById(R.id.book_dob);
        address = findViewById(R.id.book_address);
        syms = findViewById(R.id.book_syms);
        QrView = findViewById(R.id.qr_view);

        date = findViewById(R.id.seclectDate);
        continu = findViewById(R.id.contiu);
        layout = findViewById(R.id.linerlayout1);
        num2 = findViewById(R.id.S2);
        num1 = findViewById(R.id.S1);
        num3 = findViewById(R.id.S3);
        continu2 = findViewById(R.id.contiu2);
        layout_two = findViewById(R.id.linerlayout2);
        layout_3 = findViewById(R.id.relativeLayout3);

        rootRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
    /*===================================list======================*/

        List<String> categories = new ArrayList<>();
        categories.add(0, "Dr Rahul Kesharwani");
        categories.add("Dr Suryansh Bisen");
        categories.add("Dr Hansaj Sharma");
        categories.add("Dr Bhupesh Sinha");
        /*----------------------list end----------------------*/


        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("choose Doctor")) {

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
                    num1.setBackgroundResource(R.drawable.whitw_rounded_bg);
                    num2.setBackgroundResource(R.drawable.rectangle_aktif);
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
                    progressBar.setTitle("Booking Appointment");
                    progressBar.setMessage("Get Well Soon");
                    progressBar.setCanceledOnTouchOutside(false);
                    progressBar.show();

                    layout_two.setVisibility(View.GONE);
                    continu2.setVisibility(View.GONE);
                    layout_3.setVisibility(View.VISIBLE);
                    num2.setBackgroundResource(R.drawable.whitw_rounded_bg);
                    num3.setBackgroundResource(R.drawable.rectangle_aktif);
                    addData();
                }

            }
        });
    }

    private void addData() {
        final String userId= FirebaseAuth.getInstance().getUid();
        final HashMap<String, Object> profileMap = new HashMap<>();
        profileMap.put("name", EnterName);
        profileMap.put("dob",Enterdob);
        profileMap.put("age", EnterAge);
        profileMap.put("sex",EnterSex);
        profileMap.put("date",EnterDate);
        profileMap.put("doctor",EnterDoctor);
        profileMap.put("symptoms",EnterSyms);
        profileMap.put("phone", EnterPhn);
        profileMap.put("address", EnterAddress);


        rootRef.child("hospital").child(EnterDate).child(EnterDoctor).child(userId)          //for Hospital record
                .updateChildren(profileMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    genrateQr(userId);
                    profileMap.put("qrLink",qrImgLink);
                    rootRef.child("users").child(userId).child("currentBooking").updateChildren(profileMap)  // for user record
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.dismiss();
                                    Toast.makeText(book_Activity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                else {
                    Toast.makeText(book_Activity.this, "failed", Toast.LENGTH_SHORT).show();
                    QrView.setImageResource(R.drawable.whitw_rounded_bg);
                    progressBar.dismiss();
                }

            }
        });
    }

    private void genrateQr(String userId) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(userId, BarcodeFormat.QR_CODE, 200, 200);
            Bitmap bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.RGB_565);
            for (int x = 0; x<200; x++){
                for (int y=0; y<200; y++){
                    bitmap.setPixel(x,y,bitMatrix.get(x,y)? Color.BLACK : Color.WHITE);
                }
            }
            QrView.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
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