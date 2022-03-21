package com.example.groupexercisebooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.groupexercisebooking.databinding.ActivityCreateTimetableBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateTimetable extends AppCompatActivity {
    ActivityCreateTimetableBinding activityCreateTimetableBinding;
    DatabaseHelper  DB = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCreateTimetableBinding = ActivityCreateTimetableBinding.inflate(getLayoutInflater());
        setContentView(activityCreateTimetableBinding.getRoot());

//        activityCreateTimetableBinding.dateAndDay.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
//
//                Calendar calendar = Calendar.getInstance();
//                calendar.set(year, month, dayOfMonth);
//                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
//            }
//        });

        activityCreateTimetableBinding.BtnCreateTimetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

//                Calendar selected = Calendar.getInstance();
//                selected.setTimeInMillis(activityCreateTimetableBinding.dateAndDay.getDate());
//                int dayOfWeek = selected.get(Calendar.DAY_OF_WEEK);
//                String Theday = "";
//
//                if(dayOfWeek == 1) {
//                     Theday = "Sunday";
//                }else if(dayOfWeek == 2) {
//                     Theday = "Monday";
//                }else if(dayOfWeek == 3) {
//                     Theday = "Wednesday";
//                }else if(dayOfWeek == 4) {
//                     Theday = "Thursday";
//                }else if(dayOfWeek == 5) {
//                     Theday = "Friday";
//                }else if(dayOfWeek == 6) {
//                     Theday = "Saturday";
//                }else if(dayOfWeek == 7) {
//                     Theday = "Sunday";
//                }

             //   String dateAndDay =Theday+","+sdf.format(new Date(activityCreateTimetableBinding.dateAndDay.getDate()));
                String dateAndDay = activityCreateTimetableBinding.dateAndDay.getText().toString().trim();

                String firstActivity = activityCreateTimetableBinding.InputActivity1.getText().toString().trim();
                String secondActivity = activityCreateTimetableBinding.InputActivity2.getText().toString().trim();
                String thirdActivity = activityCreateTimetableBinding.InputActivity3.getText().toString().trim();
                String status = "NotYetBooked";

                //Check if inputs are null
                if(!dateAndDay.isEmpty()) {
                    if(!firstActivity.isEmpty()) {
                        if(!secondActivity.isEmpty()) {
                            if(!thirdActivity.isEmpty()) {

                                Boolean insertTimetable = DB.insertTimetable(dateAndDay,firstActivity,secondActivity,thirdActivity,status);
                                if(insertTimetable == true) {
                                    activityCreateTimetableBinding.DisplayStatus.setText("Record Insertion Success!");
                                    Toast.makeText(getApplicationContext(), "Insert Success", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                                    startActivity(intent);
                                    //clear data

                                    activityCreateTimetableBinding.InputActivity1.setText("");
                                    activityCreateTimetableBinding.InputActivity2.setText("");
                                    activityCreateTimetableBinding.InputActivity3.setText("");

                                }else {
                                    Toast.makeText(getApplicationContext(), "Failed to insert", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                activityCreateTimetableBinding.InputActivity3.setError("Third Activity Required");
                            }

                        }else {
                            activityCreateTimetableBinding.InputActivity2.setError("Second Activity Required");
                        }

                    }else {
                        activityCreateTimetableBinding.InputActivity1.setError("First Activity Required");
                    }

                }else {
                    activityCreateTimetableBinding.dateAndDay.setError("Date and Day required");
                }
            }
        });
    }
}