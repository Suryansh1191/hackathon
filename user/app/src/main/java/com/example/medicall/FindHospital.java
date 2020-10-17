package com.example.medicall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicall.model.DoctorModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FindHospital extends AppCompatActivity {

    private DatabaseReference rootRef;
    EditText pinCode ;
    Button check;
    RecyclerView recyclerView;
    String str ="";
    String name;
    List<DoctorModel> hospitalDetailList;
    ArrayList<String>  cityList;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_hospital);

        recyclerView = findViewById(R.id.find_hospital_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        check = findViewById(R.id.find_hospital_check_pin);
        pinCode = findViewById(R.id.find_hospital_city);
        rootRef = FirebaseDatabase.getInstance().getReference().child("hospitalDetail");




    }


    @Override
    protected void onStart() {
        super.onStart();
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String city = pinCode.getText().toString();

                FirebaseRecyclerOptions<DoctorModel> options = null;
                if (str.equals("")) {
                    options = new FirebaseRecyclerOptions.Builder<DoctorModel>().setQuery(rootRef.child(city), DoctorModel.class).build();
                } else {
                    options = new FirebaseRecyclerOptions.Builder<DoctorModel>().setQuery(rootRef.child(city).
                            startAt(str).endAt(str = "\uf8ff"), DoctorModel.class).build();
                }

                FirebaseRecyclerAdapter<DoctorModel,HospitalViewHolder> firebaseRecyclerAdapter=
                        new FirebaseRecyclerAdapter<DoctorModel, HospitalViewHolder>(options) {
                            @Override
                            protected void onBindViewHolder(@NonNull final HospitalViewHolder hospitalViewHolder, int i, @NonNull DoctorModel hospitalDetailModel) {

                                Toast.makeText(FindHospital.this, "aya", Toast.LENGTH_SHORT).show();

                                rootRef.child(city).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(final DataSnapshot dataSnapshot) {

                                        if (dataSnapshot.exists())
                                        {
//                            profileImage= dataSnapshot.child("image").getValue().toString();

                                            name= dataSnapshot.child("hosName").getValue().toString();
                                            hospitalViewHolder.hospitalName.setText(name);
//                            Picasso.get().load(profileImage).into(holder.profileImageView);
                                        }
                                        else Toast.makeText(FindHospital.this, "could not found", Toast.LENGTH_SHORT).show();


                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                            }

                            @NonNull
                            @Override
                            public HospitalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_1, parent, false);
                                HospitalViewHolder  viewHolder = new HospitalViewHolder(view);
                                return viewHolder;
                            }
                        };

                recyclerView.setAdapter(firebaseRecyclerAdapter);
                firebaseRecyclerAdapter.startListening();

            }
        });

    }


    public static class HospitalViewHolder extends RecyclerView.ViewHolder {
        TextView hospitalName;
        Button moreInfo, viewDr;
        CardView cardView;

        public HospitalViewHolder(@NonNull View itemView) {
            super(itemView);
            hospitalName= itemView.findViewById(R.id.cv1_text1);
            cardView= itemView.findViewById(R.id.card_view_1);

        }
    }

    public void getData(){
        rootRef.child("495001").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot it:snapshot.getChildren()){
                    Toast.makeText(FindHospital.this, it.child("hosName").getValue().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}