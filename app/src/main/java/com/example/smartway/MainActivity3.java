package com.example.smartway;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity3 extends AppCompatActivity{
    FirebaseAuth mAuth;
    private Button localisation;
    private Button notif ;
    private Button deconnexion;
    private Button appel ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        notif = (Button) findViewById(R.id.notif);
        notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivit();
            }
        });

        deconnexion = findViewById(R.id.button6);
        deconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(MainActivity3.this,MainActivity2.class));
            }
        });
        localisation= (Button) findViewById(R.id.localisation);

        localisation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivit1();
            }
        });
        mAuth = FirebaseAuth.getInstance();
    }




    public void openActivit() {
        Intent intent = new Intent(this, MainActivity4.class);
        startActivity(intent);}
    public void openActivit1() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);}







}