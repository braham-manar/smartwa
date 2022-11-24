package com.example.smartway;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {
    private Button h;
    EditText etLoginEmail;
    EditText EtLoginpassword;
    FirebaseAuth mAuth;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        h = (Button) findViewById(R.id.button5);
        etLoginEmail = findViewById(R.id.Name10);
        EtLoginpassword = findViewById(R.id.Password);
        mAuth = FirebaseAuth.getInstance();
       // h.setOnClickListener(view -> {
            //loginUser();
        //});


        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open();
                loginUser();
            }
        });
    }


    private void loginUser(){
        String email = etLoginEmail.getText().toString();
        String password = EtLoginpassword.getText().toString();
        if (TextUtils.isEmpty(email)){
            etLoginEmail.setError("email est erronné");
            etLoginEmail.requestFocus();
        }
        if (TextUtils.isEmpty(password)){
            EtLoginpassword.setError("le mot de passe est incorrecte");
            EtLoginpassword.requestFocus();

        }

        else
        {mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity2.this,"La connexion est parfaite",
                                Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity2.this,MainActivity3.class));
                    }
                    else {
                        Toast.makeText(MainActivity2.this, "connexion a échoué"
                                + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }}

    public void open() {
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);}
}