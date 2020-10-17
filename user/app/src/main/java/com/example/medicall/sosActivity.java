package com.example.medicall;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class sosActivity extends AppCompatActivity {

    private Button ambulance;
    private Button AIIMS;
    private Button covid;
    private Button roadAcc;
    private static final int REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);

        ambulance = findViewById(R.id.ambulence);
        AIIMS = findViewById(R.id.aiims);
        covid = findViewById(R.id.covid);
        roadAcc = findViewById(R.id.roadAcc);

        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(sosActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(sosActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                }
                else {
                    String call = "102".toString();
                    String no = "tel:" + call;
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    Toast.makeText(sosActivity.this, "Calling helpline", Toast.LENGTH_SHORT).show();
                    intent.setData(Uri.parse(no));
                    startActivity(intent);
                }
            }
        });
        roadAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(sosActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(sosActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                }else {
                    String call = "1073".toString();
                    Toast.makeText(sosActivity.this, "Calling helpline", Toast.LENGTH_SHORT).show();
                    String no = "tel:" + call;
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(no));
                    startActivity(intent);
                }
            }
        });
        covid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(sosActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(sosActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                }else {
                    Toast.makeText(sosActivity.this, "Calling helpline", Toast.LENGTH_SHORT).show();
                    String call = "1075".toString();
                    String no = "tel:" + call;
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(no));
                    startActivity(intent);
                }
            }
        });
        AIIMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(sosActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(sosActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                }else {
                    Toast.makeText(sosActivity.this, "Calling helpline", Toast.LENGTH_SHORT).show();
                    String call = "011-26593677".toString();
                    String no = "tel:" + call;
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(no));
                    startActivity(intent);
                }
            }
        });

   }
}