package examples.csci567.testfinalproject;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

/**
 * Created by Siddharth on 5/12/2015.
 */
public class Receiver_meet extends BroadcastReceiver {

    Notification myNotification_meet;
    private static final int MY_NOTIFICATION_ID_MEET =1;

    NotificationManager notificationManager_meet;


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)

    @Override
    public void onReceive(Context context, Intent intent) {



        Toast.makeText(context, "Alarm Triggered", Toast.LENGTH_LONG).show();

        Intent notificationIntent_meet = new Intent(context, getLocation.class);
        //notificationIntent.putExtra("taskId", 20);

        TaskStackBuilder stackBuilder_meet = TaskStackBuilder.create(context);

        stackBuilder_meet.addParentStack(getLocation.class);

        stackBuilder_meet.addNextIntent(notificationIntent_meet);


        //myNotification.setContentIntent(contentIntent);



        PendingIntent contentIntent_meet = stackBuilder_meet.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        myNotification_meet = new NotificationCompat.Builder(context)
                .setContentTitle("Notification")
                .setContentText("You have a meeting scheduled ")
                .setTicker("Notification!")
                .setWhen(System.currentTimeMillis())
                .setContentIntent(contentIntent_meet)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_launcher)
                .build()
        ;
        notificationManager_meet =
                (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager_meet.notify(MY_NOTIFICATION_ID_MEET, myNotification_meet);


    }
}
