package com.example.smartway;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity4 extends AppCompatActivity implements View.OnClickListener {
    private int notificationId = 1;
    private Button m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        findViewById(R.id.setBtn).setOnClickListener(this);
        findViewById(R.id.cancelBtn).setOnClickListener(this);
        m=findViewById(R.id.button4);
        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });
    }
    @Override
    public void onClick(View view){
        EditText editText = findViewById(R.id.editText);
        TimePicker timePicker = findViewById(R.id.timePicker);

        //set notificationid and text
        Intent intent = new Intent(MainActivity4.this,AlarmReceiver.class);
        intent.putExtra("notificationId" , notificationId);
        intent.putExtra("todo" ,editText.getText().toString());

        //getbroadcast(context, requestCode,intent,flags)
        PendingIntent alarmIntent = PendingIntent.getBroadcast(MainActivity4.this,0,
                intent,PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarm =(AlarmManager)  getSystemService(ALARM_SERVICE);
        switch (view.getId()) {
            case R.id.setBtn:
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                //create time
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY,hour);
                startTime.set(Calendar.MINUTE,minute);
                startTime.set(Calendar.SECOND,0);
                long alarmStartTime = startTime.getTimeInMillis();

                //set alarme
                //set(type,milliseconds,intent);
                alarm.set(AlarmManager.RTC_WAKEUP,alarmStartTime,alarmIntent);

                Toast.makeText(this, "c'est bon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cancelBtn:
                alarm.cancel(alarmIntent);
                Toast.makeText(this, "annuler", Toast.LENGTH_SHORT).show();
                break;
        }

    }
    public void openActivity2() {
        Intent intent = new Intent(this, MainActivity5.class);
        startActivity(intent);
    }

}