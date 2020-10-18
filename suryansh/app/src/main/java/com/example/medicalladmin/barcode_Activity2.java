package com.example.medicalladmin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class barcode_Activity2 extends AppCompatActivity {

    private Button barcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_2);
        barcode = findViewById(R.id.barcode_button);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);

        barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(barcode_Activity2.this);
                intentIntegrator.initiateScan();
            }
            
            protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
                IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
                String barcode_num;
                if (intentResult != null){
                    if (intentResult.getContents() == null) {
                        if (intentResult.getContents() == null) {
                            Toast.makeText(barcode_Activity2.this, "Not Found", Toast.LENGTH_SHORT).show();
                        }
                        } else {
                            barcode_num = intentResult.getContents();
                            Toast.makeText(barcode_Activity2.this, barcode_num, Toast.LENGTH_SHORT).show();

                        }
                }
                barcode_Activity2.super.onActivityResult(requestCode, resultCode, data);



            }
        });


        }
    }
