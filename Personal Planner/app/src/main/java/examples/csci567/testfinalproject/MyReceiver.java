/*package examples.csci567.testfinalproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by Siddharth on 5/10/2015.
 */
/*public class MyReceiver extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        this.registerReceiver(alarmreceive,new IntentFilter("com.examples.csci567.testfinalproject"));
    }



    private final BroadcastReceiver  alarmreceive = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Toast.makeText(context,"Alarm triggered",Toast.LENGTH_LONG).show();
            OnButtonListener();

        }
    };

    public void OnButtonListener(){

        AlertDialog.Builder dialogbuiler = new AlertDialog.Builder(MyReceiver.this);

        dialogbuiler.setTitle("Reminder");
        dialogbuiler.setMessage("Send Wishes to abc")
                .setCancelable(false)
                .setPositiveButton("yes",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = dialogbuiler.create();
        alertDialog.setTitle("Alert !!!!");

        alertDialog.show();


    }
}
*/

package examples.csci567.testfinalproject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by Siddharth on 5/10/2015.
 */
public class MyReceiver extends BroadcastReceiver {

        Notification myNotification;
        private static final int MY_NOTIFICATION_ID=1;

        NotificationManager notificationManager;


        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Alarm triggered", Toast.LENGTH_LONG).show();
            Intent notificationIntent = new Intent(context, sendmessage.class);
            //notificationIntent.putExtra("taskId", 20);

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);

            stackBuilder.addParentStack(sendmessage.class);

            stackBuilder.addNextIntent(notificationIntent);


            //myNotification.setContentIntent(contentIntent);



            PendingIntent contentIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
            myNotification = new NotificationCompat.Builder(context)
                    .setContentTitle("Notification!")
                    .setContentText("Press to send Message or Email")
                    .setTicker("Notification!")
                    .setWhen(System.currentTimeMillis())
                    .setContentIntent(contentIntent)
                    .setDefaults(Notification.DEFAULT_SOUND)
                    .setAutoCancel(true)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .build()
            ;
            notificationManager =
                    (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(MY_NOTIFICATION_ID, myNotification);






            //Notification notification = new Notification(icon, tickerText, when);
            //notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);



        }
    }





