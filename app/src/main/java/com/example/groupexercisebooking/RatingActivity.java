package com.example.groupexercisebooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.groupexercisebooking.databinding.ActivityRatingBinding;

import java.util.Objects;

public class RatingActivity extends AppCompatActivity {
    ActivityRatingBinding activityRatingBinding;
    DatabaseHelper  DB = new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      activityRatingBinding = ActivityRatingBinding.inflate(getLayoutInflater());
        setContentView(activityRatingBinding.getRoot());

        SharedPreferences pref = getSharedPreferences("user_details",MODE_PRIVATE);

        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id");
        String dateAndDay = bundle.getString("dateAndDay");
        String session = bundle.getString("session");

        activityRatingBinding.displayBookingDateandTime.setText("Date: "+dateAndDay);
        activityRatingBinding.displayBookingGame.setText("Game: "+session);
        activityRatingBinding.displayStatus.setText("Status: Attended " + id);

        activityRatingBinding.RateNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selected = activityRatingBinding.ratingBar.getCheckedRadioButtonId();
                RadioButton val = (RadioButton) findViewById(selected);
                String valueOfRating = val.getText().toString();

                if(valueOfRating == "") {
                    Toast.makeText(getApplicationContext(), "You need to rate", Toast.LENGTH_SHORT).show();
                }else {

                    String regNo = pref.getString("regNo", null);


                    Cursor rateNow = DB.getBookingsById(id);
                    if(rateNow.getCount() > 0) {
                        while(rateNow.moveToNext()) {
                            String date = rateNow.getString(2);
                            String session = rateNow.getString(3);


                            Boolean addRating = DB.insertRatings("",regNo,date,session,valueOfRating);
                            if(addRating == true) {
                                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(),Dashboard.class);
                                startActivity(intent);

                            }else {
                                Toast.makeText(getApplicationContext(), "Rating failed, Server error", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }else {
                        Toast.makeText(getApplicationContext(), "Sorry, No record", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),Dashboard.class);
                        startActivity(intent);
                    }
                }

            }
        });




    }
}