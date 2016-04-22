package com.sattler.greg.cs449.studygroup.db;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.sattler.greg.cs449.studygroup.R.layout.activity_create_task);

        Calendar c = Calendar.getInstance();
        System.out.println("Current time =&gt; " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String formattedDate = df.format(c.getTime());
        Toast.makeText(this, formattedDate, Toast.LENGTH_LONG).show();
        String date_alert = "";
        String input_date = "";
        //Tries to parse the input date into an actual date format.
        //Exception ex is thrown if this action fails.
        Date due_date = null;
        try {
            due_date = df.parse(input_date);
        } catch (Exception ex) {
            System.out.println("Invalid date. " + "Reason: " + ex.getMessage());
        }
        boolean valid_date = c.after(due_date);
        if (valid_date == true) {
            date_alert = "Date is valid";
        } else {
            date_alert = "Date is invalid, must be after current date";

        }
        Toast.makeText(this, date_alert, Toast.LENGTH_LONG).show();

    }
    public void backToMain() {
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}