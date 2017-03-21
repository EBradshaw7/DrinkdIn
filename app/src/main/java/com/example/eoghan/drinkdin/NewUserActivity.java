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

        btnRegister = (Button) findViewById(R.id.btnDeg);
        etEmail = (EditText) findViewById(R.id.emailET);
        etpword = (EditText) findViewById(R.id.pwET);
        btnLogin = (Button) findViewById(R.id.lgBT);
        pbar = new ProgressDialog(this);

        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        if (firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), UserAreaActivity.class));
        }
    }



    private void registerUser(){
        String email= etEmail.getText().toString().trim();
        String password = etpword.getText().toString().trim();
        //String password = "1234";
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "enter email", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "enter password", Toast.LENGTH_LONG).show();
            return;
        }

        pbar.setMessage("waiting");
        pbar.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(new Intent(getApplicationContext(),UserAreaActivity.class)));
                            //Toast.makeText(MainActivity.this, "well ddone", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(NewUserActivity.this, "fail, email already in use", Toast.LENGTH_LONG).show();
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

