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
import com.example.groupexercisebooking.ModelTimetableData;
import com.example.groupexercisebooking.R;

import java.util.ArrayList;

public class BookingsAdapter extends RecyclerView.Adapter<BookingsAdapter.myViewHolder>{
    ArrayList<ModelBookingsData> dataHolder;

    public BookingsAdapter(ArrayList<ModelBookingsData> dataHolder) {
        this.dataHolder = dataHolder;

    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_single,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        holder.displayDate.setText(dataHolder.get(position).getDate());
        holder.status.setText(dataHolder.get(position).getStatus());
        holder.game.setText(dataHolder.get(position).getNumOfSessions());


        //Object in Model
        ModelBookingsData s = dataHolder.get(position);


        //set onclick listener to the delete btn
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //use od Dialog Builder
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(), R.style.MyAlertDialogStyle);
                builder.setTitle("Delete Confirmation!");
                builder.setMessage("Confirm Delete...");
                builder.setCancelable(false);
                builder.setNegativeButton("no",null);
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHelper dbHelper = new DatabaseHelper(v.getContext());
                        Boolean result = dbHelper.deleteUserdata(Integer.parseInt(s.getId()));
                        if(result == true) {
                            Toast.makeText(v.getContext(), "Delete Success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(v.getContext(), Dashboard.class);
                            v.getContext().startActivity(intent);

                        }else{
                            Toast.makeText(v.getContext(), "Delete Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        TextView displayDate,status,game;Button delete;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            displayDate = (TextView)itemView.findViewById(R.id.displayBookingDateandTime);
            status = (TextView)itemView.findViewById(R.id.status);
            game = (TextView)itemView.findViewById(R.id.displayBookingGame);
            delete = (Button) itemView.findViewById(R.id.Delete);


        }
    }

}





