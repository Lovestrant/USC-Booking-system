package com.example.groupexercisebooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.groupexercisebooking.databinding.ActivityBookNowBinding;
import com.example.groupexercisebooking.databinding.ActivityCreateTimetableBinding;

public class BookNow extends AppCompatActivity {
    ActivityBookNowBinding activityBookNowBinding;
    DatabaseHelper  DB = new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBookNowBinding = ActivityBookNowBinding.inflate(getLayoutInflater());
        setContentView(activityBookNowBinding.getRoot());

        SharedPreferences pref = getSharedPreferences("user_details",MODE_PRIVATE);


        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id");
        String exercise = bundle.getString("exercise");
        String dateAndDay = bundle.getString("dateAndDay");
        String status = bundle.getString("status");

        String regNo = pref.getString("regNo",null);

        if(status.equals("Booked")) {
            View b = findViewById(R.id.BtnBook);
            b.setVisibility(View.GONE);
            activityBookNowBinding.DisplayErr.setText("Sorry You,This exercise is already booked");
            Toast.makeText(getApplicationContext(), "Sorry, This exercise is booked already \n Try again another time.", Toast.LENGTH_SHORT).show();
        }

        //check Number of students who are booked if space is there or not
        int Number = DB.getBookingsByExercise(exercise,dateAndDay);
        if(Number >= 4){
            View b = findViewById(R.id.BtnBook);
            b.setVisibility(View.GONE);
            activityBookNowBinding.DisplayErr.setText("Sorry,Slots for this Exercise are full\n Choose another exercise");
            Toast.makeText(getApplicationContext(), "Sorry,Slots for this Exercise are full", Toast.LENGTH_SHORT).show();
        }

        //Make sure a user only get to book once
        int regValExist = DB.getBookingsByExerciseByReg(regNo,exercise,dateAndDay);
        if(regValExist >0) {
            View b = findViewById(R.id.BtnBook);
            b.setVisibility(View.GONE);
            activityBookNowBinding.DisplayErr.setText("You have already booked This exercise");
            Toast.makeText(getApplicationContext(), "You have already booked", Toast.LENGTH_SHORT).show();

        }

        activityBookNowBinding.status.setText("Status: "+status);
        activityBookNowBinding.GameType.setText("exercise: "+exercise);
        activityBookNowBinding.DateandTime.setText(dateAndDay);
        activityBookNowBinding.BtnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //String id = pref.getString("TheId",null);

                //insertBookings(String regNo, String date, String numOfSessions, String status)



                Boolean bookNow = DB.insertBookings(regNo,dateAndDay, exercise,"Booked");

                if(bookNow == true) {
                    Toast.makeText(getApplicationContext(), "Booking Success", Toast.LENGTH_SHORT).show();

                    //set status in timetable to Booked if users Booked are 4 already
                    if(Number == 4) {
                        Cursor allRowRecords = DB.getTimetableDataById(id);
                        if(allRowRecords.getCount() > 0) {
                            while (allRowRecords.moveToNext()) {
                                String date = allRowRecords.getString(1);
                                String first = allRowRecords.getString(2);
                                String second = allRowRecords.getString(3);
                                String third = allRowRecords.getString(4);

                                Boolean setStatus = DB.updateTableTimeTable(id,date,first,second,third,"Booked");
                                if(setStatus == true) {
                                    Toast.makeText(getApplicationContext(), "Booked successfully, You just filled the final slot", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(getApplicationContext(), "Booking failed, server error", Toast.LENGTH_SHORT).show();
                                }
                                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                                startActivity(intent);
                            }

                        }else {
                            Toast.makeText(getApplicationContext(), "No record with that Id", Toast.LENGTH_SHORT).show();
                        }
                    }


                }else {
                    Toast.makeText(getApplicationContext(), "Failed server error", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}