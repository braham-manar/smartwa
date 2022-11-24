package com.example.smartway;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button2);
        m = (Button) findViewById(R.id.button1);
        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivit();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });
    }
    public void openActivit() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);}

    public void openActivity2() {
        Intent intent = new Intent(this, PSActivity.class);
        startActivity(intent);
    }

}






    //Button b = findViewById(R.id.button);
    //b.setOnCliCkLi




