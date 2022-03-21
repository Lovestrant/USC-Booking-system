package com.example.groupexercisebooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.groupexercisebooking.databinding.ActivityPrintReportsBinding;
import com.example.groupexercisebooking.databinding.ActivityRatingBinding;

public class PrintReports extends AppCompatActivity {
    ActivityPrintReportsBinding activityPrintReportsBinding;
    DatabaseHelper  DB = new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      activityPrintReportsBinding = ActivityPrintReportsBinding.inflate(getLayoutInflater());
        setContentView(activityPrintReportsBinding.getRoot());

        SharedPreferences pref = getSharedPreferences("user_details",MODE_PRIVATE);
        activityPrintReportsBinding.consolePrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchValue = activityPrintReportsBinding.input.getText().toString().trim();
                if(searchValue.isEmpty()) {
                    activityPrintReportsBinding.input.setError("This input Required");
                }else {
                    int cursor = DB.checkBookings(searchValue);
                    if(cursor > 0) {
                        Toast.makeText(getApplicationContext(), "Success Check your console", Toast.LENGTH_SHORT).show();
                        System.out.println("SYSTEM.OUT.REPORT =========================================\n " +
                                "There are "+cursor+ " Booked records for the "+ searchValue + "  exercise");

                    }
                }

            }
        });

        activityPrintReportsBinding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchValue = activityPrintReportsBinding.input.getText().toString().trim();
                if(searchValue.isEmpty()) {
                    activityPrintReportsBinding.input.setError("This input Required");
                }else {
                    int cursor = DB.checkBookings(searchValue);
                    if(cursor > 0) {
                        activityPrintReportsBinding.printReport.setText("There are "+cursor+ " Booked records for the "+ searchValue + " exercise \n ");

                    }else {
                        Toast.makeText(getApplicationContext(), "No record of report", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}