package com.example.groupexercisebooking.Adapters;

import static android.content.Context.MODE_PRIVATE;

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
import com.example.groupexercisebooking.ModelTimetableData;
import com.example.groupexercisebooking.R;

import java.util.ArrayList;

public class TimetableAdapter extends RecyclerView.Adapter<TimetableAdapter.myViewHolder>{
    ArrayList<ModelTimetableData> dataHolder;

    public TimetableAdapter(ArrayList<ModelTimetableData> dataHolder) {
        this.dataHolder = dataHolder;

    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timetable_single,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder,int position) {

        holder.displayDate.setText(dataHolder.get(position).getDateAndDay());
        holder.displayExercise1.setText(dataHolder.get(position).getExercise1());
        holder.displayExercise2.setText(dataHolder.get(position).getExercise2());
        holder.displayExercise3.setText(dataHolder.get(position).getExercise3());

        //Object in Model
        ModelTimetableData s = dataHolder.get(position);

        holder.displayExercise1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(v.getContext(), BookNow.class);
                intent.putExtra("id", s.getId());
                intent.putExtra("dateAndDay", s.getDateAndDay());
                intent.putExtra("status", s.getStatus());
                intent.putExtra("exercise", s.getExercise1());
                v.getContext().startActivity(intent);


            }
        });

        holder.displayExercise2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(v.getContext(), BookNow.class);
                intent.putExtra("id", s.getId());
                intent.putExtra("dateAndDay", s.getDateAndDay());
                intent.putExtra("status", s.getStatus());
                intent.putExtra("exercise", s.getExercise2());
                v.getContext().startActivity(intent);
            }
        });

        holder.displayExercise3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(v.getContext(), BookNow.class);
                intent.putExtra("id", s.getId());
                intent.putExtra("dateAndDay", s.getDateAndDay());
                intent.putExtra("status", s.getStatus());
                intent.putExtra("exercise", s.getExercise3());
                v.getContext().startActivity(intent);
            }
        });



//        //set onclick listener to the delete btn
//        holder.delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //use od Dialog Builder
//                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(), R.style.MyAlertDialogStyle);
//                builder.setTitle("Delete Confirmation!");
//                builder.setMessage("Confirm Delete...");
//                builder.setCancelable(false);
//                builder.setNegativeButton("no",null);
//                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        DatabaseHelper dbHelper = new DatabaseHelper(v.getContext());
//                        Boolean result = dbHelper.deleteUserdata(s.getId());
//                        if(result == true) {
//                            Toast.makeText(v.getContext(), "Delete Success", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(v.getContext(), Home.class);
//                            v.getContext().startActivity(intent);
//
//                        }else{
//                            Toast.makeText(v.getContext(), "Delete Failed", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//                builder.show();
//
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        TextView displayDate,displayExercise1,displayExercise2,displayExercise3;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            displayDate = (TextView)itemView.findViewById(R.id.displayDate);
            displayExercise1 = (TextView)itemView.findViewById(R.id.displayActivity1);
            displayExercise2 = (TextView)itemView.findViewById(R.id.displayActivity2);
            displayExercise3 = (TextView) itemView.findViewById(R.id.displayActivity3);


        }
    }

}




