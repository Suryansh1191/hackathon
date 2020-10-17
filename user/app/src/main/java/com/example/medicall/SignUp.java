package com.example.medicall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextInputEditText signUpName,signUpEmail, password,confPassword;
    private Button createAccountBtn;
     private String email_entered,name_entered,password_entered,confPassword_entered;
     private String userRef;
     private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        signUpName = findViewById(R.id.signup_name);
        signUpEmail= findViewById(R.id.email_signup);
        password = findViewById(R.id.password_signup);
        confPassword= findViewById(R.id.confirm_password_signup);
        createAccountBtn = findViewById(R.id.createAccountBtn);

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 email_entered = signUpEmail.getText().toString();
                 name_entered = signUpName.getText().toString();
                 password_entered = password.getText().toString();
                 confPassword_entered = confPassword.getText().toString();
                if (email_entered.equals("")||password_entered.equals("") || name_entered.equals("") || confPassword_entered.equals("")){
                    Toast.makeText(SignUp.this, "Please Enter all the Fields", Toast.LENGTH_SHORT).show();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email_entered).matches())
                    Toast.makeText(SignUp.this, "please Enter Valid Email", Toast.LENGTH_SHORT).show();
                else if (password_entered.length() < 6)
                    Toast.makeText(SignUp.this, "password should be at lest 6 characters", Toast.LENGTH_SHORT).show();
                else if (!password_entered.equals(confPassword_entered))
                    Toast.makeText(SignUp.this, "passwords does not matches", Toast.LENGTH_SHORT).show();
                else
                    signUp();
            }
        });

    }

    private void signUp() {
        progressDialog.setTitle("Account Settings");
        progressDialog.setMessage("please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        mAuth.createUserWithEmailAndPassword(email_entered,password_entered)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            userRef = FirebaseAuth.getInstance().getCurrentUser().getUid();

                            final HashMap<String, Object> profileMap = new HashMap<>();
                            profileMap.put("Uid", userRef);
                            profileMap.put("name", name_entered);
                            profileMap.put("email", email_entered);
                            profileMap.put("password", password_entered);
                            rootRef.child("users").child(userRef).
                                    updateChildren(profileMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(SignUp.this, Home.class);
                                        startActivity(intent);
                                        finishAffinity();
                                        progressDialog.dismiss();
                                        Toast.makeText(SignUp.this, "Welcome", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        progressDialog.dismiss();
                                        Toast.makeText(SignUp.this, "Please Try again.", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                        }
                    }
                });

    }
}