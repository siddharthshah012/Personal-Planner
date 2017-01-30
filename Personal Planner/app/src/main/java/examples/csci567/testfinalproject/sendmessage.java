package examples.csci567.testfinalproject;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class sendmessage extends ActionBarActivity {

    Button b_edit;
    Button b_message;

    EditText edit_alarm;
    EditText edit_mess;
    EditText ename;
    EditText edate;
    EditText econtact;
    EditText eemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendmessage);


        b_edit = (Button) findViewById(R.id.btn_edit);
        b_message = (Button) findViewById(R.id.btn_message);
        //ename = (EditText) findViewById(R.id.edit_name);

        b_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name ,date,contact,email ;

                Intent emailintent = new Intent(Intent.ACTION_SEND);


                //name= ename.getText().toString();
                eemail = (EditText) findViewById(R.id.edit_email);
                edit_mess = (EditText) findViewById(R.id.edit_message);
                //date =edate.getText().toString();

                email = eemail.getText().toString();
                String[] to = new String[] {email};
                emailintent.putExtra(Intent.EXTRA_EMAIL,to);
                emailintent.putExtra(Intent.EXTRA_SUBJECT,"Birthday Greeting");
                emailintent.putExtra(Intent.EXTRA_TEXT,edit_mess.getText().toString());
                emailintent.setType("message/rfc822");


                startActivity(emailintent.createChooser(emailintent,"Select options"));
                emailintent.putExtra(Intent.ACTION_SENDTO,to);



            }
        });

        b_message.setOnClickListener(new View.OnClickListener() {

            String contact,test="123456";
            @Override
            public void onClick(View v) {
                econtact= (EditText) findViewById(R.id.edit_number);
                contact = econtact.getText().toString();

                try {

                    SmsManager smsManager = SmsManager.getDefault();

                    smsManager.sendTextMessage(contact, null, test, null, null);
                }
                catch (Exception e)
                {}


            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sendmessage, menu);
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
