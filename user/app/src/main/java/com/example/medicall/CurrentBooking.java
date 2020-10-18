package com.example.medicall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CurrentBooking extends AppCompatActivity {

    DatabaseReference userRef;
    FirebaseAuth firebaseAuth;
    LinearLayout linearLayout;
    ProgressBar load;
    String uid;
    TextView name,add,age,date,dob,phone,sex,symptoms,doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_booking);

        linearLayout =findViewById(R.id.currentUser);
        load = findViewById(R.id.progressBarCurrentUser);
        name = findViewById(R.id.display_name);
        phone = findViewById(R.id.display_contact);
        age = findViewById(R.id.display_age);
        date = findViewById(R.id.display_date);
        dob = findViewById(R.id.display_dob);
        doctor = findViewById(R.id.display_doc);
        sex = findViewById(R.id.display_sex);
        symptoms = findViewById(R.id.display_symptoms);


        userRef = FirebaseDatabase.getInstance().getReference().child("users");
        firebaseAuth  = FirebaseAuth.getInstance();

        uid = firebaseAuth.getCurrentUser().getUid();

        userRef.child(uid).child("currentBooking").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name.setText(snapshot.child("name").getValue().toString());
                phone.setText(snapshot.child("phone").getValue().toString());
                age.setText(snapshot.child("age").getValue().toString());
                date.setText(snapshot.child("date").getValue().toString());
                dob.setText(snapshot.child("dob").getValue().toString());
                sex.setText(snapshot.child("sex").getValue().toString());
                symptoms.setText(snapshot.child("symptoms").getValue().toString());
                doctor.setText(snapshot.child("doctor").getValue().toString());

                load.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}