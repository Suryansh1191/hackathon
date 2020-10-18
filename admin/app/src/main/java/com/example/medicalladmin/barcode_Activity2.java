package com.example.medicalladmin;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
public class barcode_Activity2 extends AppCompatActivity {

    private Button barcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_2);
        barcode = findViewById(R.id.barcode_button);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);

        barcode.setOnClickListener(view -> startActivity(new Intent(barcode_Activity2.this,ScannerActivity.class)));


        }
    }
