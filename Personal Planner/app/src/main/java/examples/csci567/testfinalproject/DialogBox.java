package examples.csci567.testfinalproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Siddharth on 5/10/2015.
 */
public class DialogBox extends ActionBarActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
       // OnButtonListener();

    }

   /* public void OnButtonListener(){

                AlertDialog.Builder dialogbuiler = new AlertDialog.Builder(DialogBox.this);

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
