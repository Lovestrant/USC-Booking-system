package com.example.groupexercisebooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.groupexercisebooking.Adapters.BookingsAdapter;
import com.example.groupexercisebooking.Adapters.TimetableAdapter;
import com.example.groupexercisebooking.databinding.ActivityBookingsBinding;

import java.util.ArrayList;

public class Bookings extends AppCompatActivity {
    ActivityBookingsBinding activityBookingsBinding;

    ArrayList<ModelBookingsData> dataHolder = new ArrayList<ModelBookingsData>();
    DatabaseHelper DB = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      activityBookingsBinding = ActivityBookingsBinding.inflate(getLayoutInflater());
        setContentView(activityBookingsBinding.getRoot());

        RecyclerView recyclerView = activityBookingsBinding.BookingRecyclerview;
        SharedPreferences pref = getSharedPreferences("user_details",MODE_PRIVATE);
        String regNo = pref.getString("regNo", null);

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

                    ModelBookingsData obj = new ModelBookingsData(id,regNo,date,exercise,status);
                    dataHolder.add(obj);
                }
            }


            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(llm);
            BookingsAdapter adapter = new BookingsAdapter(dataHolder);
            recyclerView.setAdapter(adapter);
        }else{
            Toast.makeText(getApplicationContext(),"No Bookings Yet\n Go to Timetable to book", Toast.LENGTH_SHORT).show();
        }

    }
}