package examples.csci567.testfinalproject;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
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


public class payments extends ActionBarActivity {

    Button btn_datepick_pay;
    Button btn_timepick_pay;
    Button btn_save_pay;

    int year_dp_pay, month_dp_pay, day_dp_pay;
    static final int DATE_DIALOG_ID_PAY=0;
    static final int TIME_DIALOG_ID_PAY=1;
    int hour_tp_pay,minute_tp_pay;

    Button btn_save;
    final Calendar c_pay = Calendar.getInstance();

    String name,contact,email;
    BroadcastReceiver br;
    PendingIntent pi_alarm_pay;
    //AlarmManager am_alarm;

    EditText edt_time_pay;
    EditText edt_date_pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);

        final Calendar calendar= Calendar.getInstance();

        year_dp_pay = calendar.get(Calendar.YEAR);
        month_dp_pay = calendar.get(Calendar.MONTH);
        day_dp_pay = calendar.get(Calendar.DAY_OF_MONTH);
        hour_tp_pay = calendar.get(Calendar.HOUR_OF_DAY);
        minute_tp_pay = calendar.get(Calendar.MINUTE);
        showDateDialogOnClick();
        // this.registerReceiver(alarmreceive,new IntentFilter("com.examples.csci567.testfinalproject;"));

        showTimeDialogOnClick();
        Alarm();
    }



    public void Alarm() {

        Intent intent_alarm = new Intent(payments.this, Receiver_pay.class);

        pi_alarm_pay = PendingIntent.getBroadcast(payments.this, 0, intent_alarm, 0);
        final AlarmManager am_alarm = (AlarmManager) (getSystemService(Context.ALARM_SERVICE));

        btn_save_pay = (Button) findViewById(R.id.button_pay_save);
        btn_save_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                am_alarm.set(AlarmManager.RTC, c_pay.getTimeInMillis(), pi_alarm_pay);

                Toast.makeText(payments.this, "Reminder set ", Toast.LENGTH_LONG).show();
                Log.d("AM", c_pay.toString());

            }
        });
    }

    public void showDateDialogOnClick()
    {
        btn_datepick_pay = (Button)findViewById(R.id.button_pay_date);

        btn_datepick_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID_PAY);
            }
        });

    }


    public void showTimeDialogOnClick()
    {
        btn_timepick_pay = (Button) findViewById(R.id.button_pay_time);

        btn_timepick_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID_PAY);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id)
    {
        if (id == DATE_DIALOG_ID_PAY)
        {
            return new DatePickerDialog(this, datepicker_pay, year_dp_pay,month_dp_pay,day_dp_pay);
        }
        if (id == TIME_DIALOG_ID_PAY)
        {
            return new TimePickerDialog(this,timepicker_pay,hour_tp_pay,minute_tp_pay,false);
        }
        return null;
    }


    private TimePickerDialog.OnTimeSetListener timepicker_pay = new TimePickerDialog.OnTimeSetListener()
    {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_tp_pay = hourOfDay;
            minute_tp_pay = minute;
            c_pay.set(Calendar.HOUR_OF_DAY, hourOfDay);
            c_pay.set(Calendar.MINUTE, minute);
            edt_time_pay = (EditText)findViewById(R.id.edit_pay_time);
            edt_time_pay.setText(hour_tp_pay + ":"+ minute_tp_pay);
            Toast.makeText(payments.this, hour_tp_pay + ":"+ minute_tp_pay,Toast.LENGTH_LONG).show();
        }
    };

    private DatePickerDialog.OnDateSetListener datepicker_pay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            year_dp_pay = year;
            month_dp_pay = monthOfYear +1 ;
            day_dp_pay = dayOfMonth;
            c_pay.set(Calendar.YEAR,year);
            c_pay.set(Calendar.MONTH,monthOfYear);
            c_pay.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            c_pay.set(Calendar.SECOND,0);
            edt_date_pay = (EditText)findViewById(R.id.edit_pay_date);
            edt_date_pay.setText(year_dp_pay+"/"+month_dp_pay+"/"+day_dp_pay);
            Toast.makeText(payments.this, year_dp_pay+"/"+month_dp_pay+"/"+day_dp_pay,Toast.LENGTH_LONG).show();

        }
    };



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_payments, menu);
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
