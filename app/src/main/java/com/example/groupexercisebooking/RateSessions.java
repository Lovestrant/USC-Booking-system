package com.example.groupexercisebooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.groupexercisebooking.Adapters.BookingsAdapter;
import com.example.groupexercisebooking.Adapters.RateSessionsAdapter;
import com.example.groupexercisebooking.databinding.ActivityRateSessionsBinding;

import java.util.ArrayList;

public class RateSessions extends AppCompatActivity {
    ActivityRateSessionsBinding activityRateSessionsBinding;
    ArrayList<ModelRatingData> dataHolder = new ArrayList<ModelRatingData>();
    DatabaseHelper DB = new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRateSessionsBinding = ActivityRateSessionsBinding.inflate(getLayoutInflater());
        setContentView(activityRateSessionsBinding.getRoot());

        SharedPreferences pref = getSharedPreferences("user_details",MODE_PRIVATE);
        RecyclerView recyclerView = activityRateSessionsBinding.RatingRecyclerview;

        String regNo = pref.getString("regNo",null);
        //Display timetable to recyclerView
        Cursor checkData = DB.getBookings(regNo);
        if(checkData.getCount() >0) {
            int i;
            for(i=0; i< checkData.getCount(); i++) {
                if(checkData.moveToNext()) {
                    String id = String.valueOf(Integer.parseInt(checkData.getString(0)));
                    String date = checkData.getString(2);
                    String exercise = checkData.getString(3);
                    String status = checkData.getString(4);

                    ModelRatingData obj = new ModelRatingData(id,regNo,date,exercise,status);
                    dataHolder.add(obj);
                }
            }


            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(llm);
            RateSessionsAdapter adapter = new RateSessionsAdapter(dataHolder);
            recyclerView.setAdapter(adapter);
        }else{
            Toast.makeText(getApplicationContext(),"You need to book to rate\n Go to Timetable to book", Toast.LENGTH_SHORT).show();
        }
    }
}