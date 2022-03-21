package com.example.groupexercisebooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.groupexercisebooking.databinding.ActivityDashboardBinding;

public class Dashboard extends AppCompatActivity {
    ActivityDashboardBinding activityDashboardBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDashboardBinding = activityDashboardBinding.inflate(getLayoutInflater());
        setContentView(activityDashboardBinding.getRoot());

        activityDashboardBinding.seeBookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Bookings.class);
                startActivity(intent);
            }
        });

        activityDashboardBinding.seeAttendedSessions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RateSessions.class);
                startActivity(intent);
            }
        });

        activityDashboardBinding.printReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PrintReports.class);
                startActivity(intent);
            }
        });

        activityDashboardBinding.seeTimetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Timetable.class);
                startActivity(intent);
            }
        });
    }
}