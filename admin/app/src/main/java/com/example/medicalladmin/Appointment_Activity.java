package com.example.medicalladmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Appointment_Activity extends AppCompatActivity {

    TextView t1,t2,t3,t4;
    Button b1,b2,b3,b4;
    String doc="";
    RecyclerView recyclerView;
    LinearLayout doclist;


    private String str="";
    private DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_);

        recyclerView =findViewById(R.id.recycleView);
        doclist = findViewById(R.id.docList);
        userRef= FirebaseDatabase.getInstance().getReference().child("hospital").child("2020 10 18");
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        t1 = findViewById(R.id.dr1);
        b1 = findViewById(R.id.b2);
        t2 = findViewById(R.id.dr2);
        b2 = findViewById(R.id.b3);
        t3 = findViewById(R.id.dr3);
        b3 = findViewById(R.id.b4);
        t4 = findViewById(R.id.dr4);
        b4 = findViewById(R.id.b5);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doc = t1.getText().toString();
                doclist.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                setData(doc);

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doc = t2.getText().toString();
                doclist.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                setData(doc);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doc = t3.getText().toString();
                doclist.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                setData(doc);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doc = t4.getText().toString();
                doclist.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                setData(doc);
            }
        });

    }

    private void setData(String doc) {

        FirebaseRecyclerOptions<listModel> options = null;
        if (str.equals("")) {
            options = new FirebaseRecyclerOptions.Builder<listModel>().setQuery(userRef.child(doc), listModel.class).build();
        } else {
            options = new FirebaseRecyclerOptions.Builder<listModel>().setQuery(userRef.
                    startAt(str).endAt(str = "\uf8ff"), listModel.class).build();
        }


        FirebaseRecyclerAdapter<listModel, viewRequestsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<listModel, viewRequestsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull viewRequestsViewHolder holder, int i, @NonNull listModel model) {
                holder.nameView.setText(model.getName());
            }

            @NonNull
            @Override
            public viewRequestsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
                viewRequestsViewHolder viewHolder = new viewRequestsViewHolder(view);
                return viewHolder;

            }
        };



        recyclerView.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();

    }

    public static class viewRequestsViewHolder extends RecyclerView.ViewHolder
    {
        TextView nameView;
        CardView cardView;

        public viewRequestsViewHolder(@NonNull View itemView){
            super(itemView);
            nameView = itemView.findViewById(R.id.cv1);
            cardView= itemView.findViewById(R.id.card_view);

        }
    }
}