package examples.csci567.testfinalproject;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

/**
 * Created by Siddharth on 5/12/2015.
 */
public class Receiver_pay extends BroadcastReceiver {

    Notification myNotification_pay;
    private static final int MY_NOTIFICATION_ID_PAY =1;

    NotificationManager notificationManager_pay;


    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "Alarm Triggered payments", Toast.LENGTH_LONG).show();

        myNotification_pay = new NotificationCompat.Builder(context)
                .setContentTitle("Notification")
                .setContentText("You have a payment scheduled ")
                .setTicker("Notification!")
                .setWhen(System.currentTimeMillis())
                //.setContentIntent(contentIntent_pay)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_launcher)
                .build()
        ;
        notificationManager_pay =
                (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager_pay.notify(MY_NOTIFICATION_ID_PAY, myNotification_pay);

    }
}
