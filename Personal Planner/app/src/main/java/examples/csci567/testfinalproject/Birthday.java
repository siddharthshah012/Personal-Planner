package examples.csci567.testfinalproject;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class Birthday extends ActionBarActivity {

    Button btn_datepick;
    Button btn_timepick;

    int year_dp, month_dp, day_dp;
    static final int DATE_DIALOG_ID=0;
    static final int TIME_DIALOG_ID=1;
    int hour_tp,minute_tp;

    EditText edt_name;
    EditText edt_contact;
    EditText edt_email;

    EditText edt_date;
    EditText edt_time;

    Button btn_save;
    final Calendar calendar= Calendar.getInstance();
    final Calendar c = Calendar.getInstance();

    String name,contact,email;
    BroadcastReceiver br;
    PendingIntent pi_alarm;
   // AlarmManager am_alarm;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_birthday)
        ;



        year_dp = calendar.get(Calendar.YEAR);
        month_dp = calendar.get(Calendar.MONTH);
        day_dp = calendar.get(Calendar.DAY_OF_MONTH);
        hour_tp = calendar.get(Calendar.HOUR_OF_DAY);
        minute_tp = calendar.get(Calendar.MINUTE);
        showDateDialogOnClick();
        //this.registerReceiver(alarmreceive,new IntentFilter("com.examples.csci567.testfinalproject;"));

        showTimeDialogOnClick();
        Alarm();



    }

    public void Alarm()
    {

        Intent intent_alarm = new Intent(Birthday.this,MyReceiver.class);

        pi_alarm = PendingIntent.getBroadcast(Birthday.this,0,intent_alarm,0);
        final AlarmManager am_alarm = (AlarmManager)(getSystemService(Context.ALARM_SERVICE));


        btn_save = (Button) findViewById(R.id.button_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                am_alarm.set(AlarmManager.RTC, c.getTimeInMillis(),pi_alarm);
                long abc = System.currentTimeMillis();
                String xy = Long.toString(abc);
                Toast.makeText(Birthday.this,"reminder set",Toast.LENGTH_LONG).show();
                Log.d("AM", c.toString());

            }
        });

       /* br= new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context,"Bang bang ",Toast.LENGTH_LONG).show();
            }
        };*/
     }

    public void Getdata()
    {
        edt_name = (EditText) findViewById(R.id.edit_name);
        edt_contact = (EditText) findViewById(R.id.edit_number);
        edt_email = (EditText) findViewById(R.id.edit_email);

        name = edt_name.getText().toString();
        contact = edt_contact.getText().toString();
        email = edt_email.getText().toString();




    }

    public void showDateDialogOnClick()
    {
        btn_datepick = (Button)findViewById(R.id.button_datepick);

        btn_datepick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

    }

    public void showTimeDialogOnClick()
    {
        btn_timepick = (Button) findViewById(R.id.button_timepick);

        btn_timepick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID);
            }
        });
    }


    @Override
    protected Dialog onCreateDialog(int id)
    {
        if (id == DATE_DIALOG_ID)
        {
            return new DatePickerDialog(this, datepicker, year_dp,month_dp,day_dp);
        }
        if (id == TIME_DIALOG_ID)
        {
            return new TimePickerDialog(this,timepicker,hour_tp,minute_tp,false);
        }
        return null;
    }

    private TimePickerDialog.OnTimeSetListener timepicker = new TimePickerDialog.OnTimeSetListener()
    {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_tp = hourOfDay;
            minute_tp = minute;

            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
            c.set(Calendar.MINUTE, minute);
            edt_time = (EditText)findViewById(R.id.edit_time);
            edt_time.setText(hour_tp + ":"+ minute_tp);
            Toast.makeText(Birthday.this, hour_tp + ":"+ minute_tp,Toast.LENGTH_LONG).show();
        }
    };

    private DatePickerDialog.OnDateSetListener datepicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            year_dp = year;
            month_dp = monthOfYear+1 ;
            day_dp = dayOfMonth;
            c.set(Calendar.YEAR,year);
            c.set(Calendar.MONTH,monthOfYear);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            c.set(Calendar.SECOND,0);
            //c.set(Calendar.AM_PM,Calendar.AM);
            edt_date = (EditText)findViewById(R.id.edit_date);
            edt_date.setText(year_dp+"/"+month_dp+"/"+day_dp);
            Toast.makeText(Birthday.this, year_dp+"/"+month_dp+"/"+day_dp,Toast.LENGTH_LONG).show();

        }
    };





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_birthday, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*private final BroadcastReceiver  alarmreceive = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            OnButtonListener();

        }
    };*/

    /*private final BroadcastReceiver  alarmreceive = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Toast.makeText(context,"Alarm triggered",Toast.LENGTH_LONG).show();
            //OnButtonListener();

        }
    };

   /* public void OnButtonListener(){

        AlertDialog.Builder dialogbuiler = new AlertDialog.Builder(Birthday.this);

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


    }*/
}
