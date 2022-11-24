package com.example.smartway;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //get id and message from intent;
        int notificationId =intent.getIntExtra("notificationId",0);
        String message = intent.getStringExtra("todo");


        //when notification os tapped ,call MainActivity4
        Intent mainIntent =new Intent(context,MainActivity4.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,0,mainIntent,0);

        NotificationManager myNoticationManager =
                (NotificationManager)  context.getSystemService(Context.NOTIFICATION_SERVICE);

        //prepare notif
        Notification.Builder builder =new Notification.Builder(context);
        builder.setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("c'est le temps de prendre tes medicaments")
                .setContentText(message)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentIntent(contentIntent);
                //.setPriority(Notification.PRIORITY_MAX)
                //.setDefaults(Notification.DEFAULT_ALL);

        //notify
        myNoticationManager.notify(notificationId,builder.build());
    }
}
