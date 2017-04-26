package com.example.eoghan.drinkdin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class NewUserActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnRegister;
    private Button btnLogin;
    private EditText etEmail;
    private EditText etpword;
    private TextView tvreg;

    private ProgressDialog pbar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        firebaseAuth = FirebaseAuth.getInstance();

        btnRegister = (Button) findViewById(R.id.btnCreate);
        etEmail = (EditText) findViewById(R.id.emET);
        etpword = (EditText) findViewById(R.id.passwordET);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        pbar = new ProgressDialog(this);

        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        //if there is a user go to user area
        if (firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), UserAreaActivity.class));
        }
    }



    private void registerUser(){
        String email = etEmail.getText().toString().trim();
        final String password = etpword.getText().toString().trim();
        //String password = "1234";
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Can not leave email field blank", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Can not leave password field blank", Toast.LENGTH_LONG).show();
            return;
        }

        pbar.setMessage("processing");
        pbar.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(new Intent(getApplicationContext(),UserAreaActivity.class)));
                        }
                        else if (task.getException() instanceof FirebaseAuthUserCollisionException) {

                            Toast.makeText(NewUserActivity.this, "Fail, email already in use", Toast.LENGTH_LONG).show();

                        }else if (password.length() < 6){

                            Toast.makeText(NewUserActivity.this, "Fail, password must be atleast 6 characters", Toast.LENGTH_LONG).show();

                        }
                        pbar.dismiss();
                    }
                });

    }



  /*  private void signin() {
    }*/

    @Override
    public void onClick(View v) {
        if (v == btnRegister) {
            registerUser();

        }

        if (v == btnLogin){
            if (firebaseAuth.getCurrentUser() != null){
                //finish();
                Toast.makeText(NewUserActivity.this, "fail, already logged in", Toast.LENGTH_LONG).show();
            }else {
                startActivity(new Intent(this, LoginActivity.class));
            }
        }
    }



}

