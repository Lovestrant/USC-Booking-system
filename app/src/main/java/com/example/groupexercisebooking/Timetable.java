package com.example.groupexercisebooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.groupexercisebooking.Adapters.TimetableAdapter;
import com.example.groupexercisebooking.databinding.ActivityTimetableBinding;

import java.util.ArrayList;

public class Timetable extends AppCompatActivity {
    ActivityTimetableBinding activityTimetableBinding;

    ArrayList<ModelTimetableData> dataHolder = new ArrayList<>();
    DatabaseHelper DB = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTimetableBinding = ActivityTimetableBinding.inflate(getLayoutInflater());
        setContentView(activityTimetableBinding.getRoot());

        SharedPreferences pref = getSharedPreferences("user_details",MODE_PRIVATE);
        RecyclerView recyclerView = activityTimetableBinding.TimetableRecyclerView;

        if(pref.contains("regNo")) {
            String userName = pref.getString("regNo",null);
            if(userName.equals("Admin")) {
                activityTimetableBinding.createTimetable.setVisibility(View.VISIBLE);
            }else{
                activityTimetableBinding.createTimetable.setVisibility(View.GONE);
            }
        }else {
            Toast.makeText(getApplicationContext(), "Sorry, You need to login first", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

        //Display timetable to recyclerView
        Cursor checkData = DB.getTimetableData();
        if(checkData.getCount() >0) {
            int i;
            for(i=0; i< checkData.getCount(); i++) {
                if(checkData.moveToNext()) {
                    String id = checkData.getString(0);
                    String date = checkData.getString(1);
                    String first = checkData.getString(2);
                    String second = checkData.getString(3);
                    String third = checkData.getString(4);
                    String status = checkData.getString(5);

                    ModelTimetableData obj = new ModelTimetableData(id,date, first, second,third,status);
                    dataHolder.add(obj);
                }
            }

            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(llm);
            TimetableAdapter adapter = new TimetableAdapter(dataHolder);
            recyclerView.setAdapter(adapter);
        }else{
            Toast.makeText(getApplicationContext(),"Timetable data Missing", Toast.LENGTH_SHORT).show();
        }

        activityTimetableBinding.createTimetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateTimetable.class);
                startActivity(intent);
            }
        });

        //activityTimetableBinding.TimetableRecyclerView
    }
}