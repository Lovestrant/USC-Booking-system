package com.example.groupexercisebooking.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupexercisebooking.BookNow;
import com.example.groupexercisebooking.Dashboard;
import com.example.groupexercisebooking.DatabaseHelper;
import com.example.groupexercisebooking.ModelBookingsData;
import com.example.groupexercisebooking.ModelRatingData;
import com.example.groupexercisebooking.ModelTimetableData;
import com.example.groupexercisebooking.R;
import com.example.groupexercisebooking.RateSessions;
import com.example.groupexercisebooking.RatingActivity;

import java.util.ArrayList;

public class RateSessionsAdapter extends RecyclerView.Adapter<RateSessionsAdapter.myViewHolder>{
    ArrayList<ModelRatingData> dataHolder;

    public RateSessionsAdapter(ArrayList<ModelRatingData> dataHolder) {
        this.dataHolder = dataHolder;

    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rate_sessions_single,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        holder.displayDate.setText(dataHolder.get(position).getDate());
        holder.status.setText("Attended");
        holder.game.setText(dataHolder.get(position).getSession());


        //Object in Model
        ModelRatingData s = dataHolder.get(position);


        //set onclick listener to the delete btn
        holder.rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RatingActivity.class);
                intent.putExtra("id", s.getId());
                intent.putExtra("dateAndDay", s.getDate());
                intent.putExtra("session", s.getSession());

                v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        TextView displayDate,status,game;Button rate;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            displayDate = (TextView)itemView.findViewById(R.id.displayBookingDateandTime);
            status = (TextView)itemView.findViewById(R.id.status);
            game = (TextView)itemView.findViewById(R.id.displayBookingGame);
            rate = (Button) itemView.findViewById(R.id.Rate);


        }
    }

}






