package examples.csci567.testfinalproject;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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


public class meeting extends ActionBarActivity {


    Button btn_datepick_meet;
    Button btn_timepick_meet;
    Button btn_save_meet;

    int year_dp_meet, month_dp_meet, day_dp_meet;
    static final int DATE_DIALOG_ID_MEET=0;
    static final int TIME_DIALOG_ID_MEET=1;
    int hour_tp_meet,minute_tp_meet;

    Button btn_save;
    final Calendar c_meet = Calendar.getInstance();

    String name,contact,email;
    BroadcastReceiver br;
    PendingIntent pi_alarm_meet;
    //AlarmManager am_alarm;

    EditText edt_time_meet;
    EditText edt_date_meet;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);

        final Calendar calendar= Calendar.getInstance();

        year_dp_meet = calendar.get(Calendar.YEAR);
        month_dp_meet = calendar.get(Calendar.MONTH);
        day_dp_meet = calendar.get(Calendar.DAY_OF_MONTH);
        hour_tp_meet = calendar.get(Calendar.HOUR_OF_DAY);
        minute_tp_meet = calendar.get(Calendar.MINUTE);
        showDateDialogOnClick();
       // this.registerReceiver(alarmreceive,new IntentFilter("com.examples.csci567.testfinalproject;"));

        showTimeDialogOnClick();
        Alarm();
    }

    public void Alarm() {

        Intent intent_alarm = new Intent(meeting.this, Receiver_meet.class);

        pi_alarm_meet = PendingIntent.getBroadcast(meeting.this, 0, intent_alarm, 0);
        final AlarmManager am_alarm = (AlarmManager) (getSystemService(Context.ALARM_SERVICE));

        btn_save_meet = (Button) findViewById(R.id.button_save_meeting);
        btn_save_meet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                am_alarm.set(AlarmManager.RTC, c_meet.getTimeInMillis(), pi_alarm_meet);

                Toast.makeText(meeting.this, "Reminder set ", Toast.LENGTH_LONG).show();
                Log.d("AM", c_meet.toString());

            }
        });
    }

    public void showDateDialogOnClick()
    {
        btn_datepick_meet = (Button)findViewById(R.id.button_datepick_meeting);

        btn_datepick_meet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID_MEET);
            }
        });

    }

    public void showTimeDialogOnClick()
    {
        btn_timepick_meet = (Button) findViewById(R.id.button_timepick_meeting);

        btn_timepick_meet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID_MEET);
            }
        });
    }


    @Override
    protected Dialog onCreateDialog(int id)
    {
        if (id == DATE_DIALOG_ID_MEET)
        {
            return new DatePickerDialog(this, datepicker_meet, year_dp_meet,month_dp_meet,day_dp_meet);
        }
        if (id == TIME_DIALOG_ID_MEET)
        {
            return new TimePickerDialog(this,timepicker_meet,hour_tp_meet,minute_tp_meet,false);
        }
        return null;
    }

    private TimePickerDialog.OnTimeSetListener timepicker_meet = new TimePickerDialog.OnTimeSetListener()
    {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_tp_meet = hourOfDay;
            minute_tp_meet = minute;
            c_meet.set(Calendar.HOUR_OF_DAY, hourOfDay);
            c_meet.set(Calendar.MINUTE, minute);
            edt_time_meet = (EditText)findViewById(R.id.edit_time_meeting);
            edt_time_meet.setText(hour_tp_meet + ":"+ minute_tp_meet);
            Toast.makeText(meeting.this, hour_tp_meet + "/"+ minute_tp_meet,Toast.LENGTH_LONG).show();
        }
    };

    private DatePickerDialog.OnDateSetListener datepicker_meet = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            year_dp_meet = year;
            month_dp_meet = monthOfYear +1 ;
            day_dp_meet = dayOfMonth;
            c_meet.set(Calendar.YEAR,year);
            c_meet.set(Calendar.MONTH,monthOfYear);
            c_meet.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            c_meet.set(Calendar.SECOND,0);
            edt_date_meet = (EditText)findViewById(R.id.edit_date_meeting);
            edt_date_meet.setText(year_dp_meet+"/"+month_dp_meet+"/"+day_dp_meet);
            Toast.makeText(meeting.this, year_dp_meet+"/"+month_dp_meet+"/"+day_dp_meet,Toast.LENGTH_LONG).show();

        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_meeting, menu);
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
}
