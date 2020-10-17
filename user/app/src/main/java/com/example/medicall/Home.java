package com.example.medicall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    DrawerLayout drawer;
    NavigationView nav_view;
    FirebaseAuth firebaseAuth;
    Button findHospital,book,history,sos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        nav_view = findViewById(R.id.nav_view);
        findHospital = findViewById(R.id.findHospitalBtn);
        book = findViewById(R.id.bookBtn);
        history = findViewById(R.id.viewHistoryBtn);
        sos = findViewById(R.id.sosBtn);

        firebaseAuth = FirebaseAuth.getInstance();

        /*==================Tool Bar=====*/
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        /*======All About Tool Bar menus***/
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.nav_logout:
                        firebaseAuth.signOut();
                        startActivity(new Intent(Home.this, Login.class));
                        finish();
                        break;

                }

                return true;
            }
        });



        /*====================BUTTONS On Click=============*/
        findHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home.this, "Coming soon", Toast.LENGTH_SHORT).show();
            }
        });

    }

}