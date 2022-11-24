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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class PSActivity extends AppCompatActivity {
    private Button btnRegister;
    EditText Name;
    EditText Name2;
    EditText Name3;
    EditText phone;
    EditText adresse;
    FirebaseAuth mAuth;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psactivity);
        //setContentView(R.layout.activity_main2);
        Name =findViewById(R.id.Name);
        Name2 =findViewById(R.id.Name2);
        Name3 =findViewById(R.id.Name3);
        phone =findViewById(R.id.Phone);
        adresse =findViewById(R.id.adresse);


        btnRegister = (Button) findViewById(R.id.button3);

        mAuth = FirebaseAuth.getInstance();
        //btnRegister.setOnClickListener(view ->{
            //createUser();
       // });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivit();
                createUser();
            };
        private void createUser(){
        String email = Name3.getText().toString();
        String password = Name2.getText().toString();
        String nom_et_prenom = Name.getText().toString();
        String phone1 = phone.getText().toString();
        String adresse1 = adresse.getText().toString();


        if (TextUtils.isEmpty(email)){
            Name3.setError("Email n'est pas convenable");

        }

        if (TextUtils.isEmpty(nom_et_prenom)){
            Name.setError("nom et prenom n'est pas convenable");
            Name.requestFocus();
            }
        if (TextUtils.isEmpty(phone1)){
            phone.setError("num de telephone n'est pas convenable");
            phone.requestFocus();
                 }
        if (TextUtils.isEmpty(password)){
            Name2.setError("mot de passe n'est pas convenble ");
            Name2.requestFocus();

        }
        if (TextUtils.isEmpty(adresse1)){
            adresse.setError("adresse n'est pas convenable");
            adresse.requestFocus();

        }
        else {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(PSActivity.this, "inscription est avec succé", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(PSActivity.this, MainActivity2.class));
                    }else{
                        Toast.makeText(PSActivity.this, "inscription a echoué: "
                                + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

       }

        public void openActivit() {
            Intent intent = new Intent(PSActivity.this, MainActivity2.class);
            startActivity(intent);}
        }  );}
}





