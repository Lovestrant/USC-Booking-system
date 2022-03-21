package com.example.groupexercisebooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.groupexercisebooking.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mMainActivityBinding;
    DatabaseHelper  DB = new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainActivityBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mMainActivityBinding.getRoot());

        SharedPreferences pref = getSharedPreferences("user_details",MODE_PRIVATE);

        mMainActivityBinding.TheBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regNo = mMainActivityBinding.RegNo.getText().toString().trim();
                if(!regNo.isEmpty()) {
                    Boolean checkUser = DB.checkUser(regNo);
                    if(checkUser == false){
                            Boolean insertAuthData = DB.insertAuthData(regNo);
                            if(insertAuthData == true) {

                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("regNo",regNo);
                                editor.commit();

                                //redirect to home page
                                Toast.makeText(getApplicationContext(), "Welcome.. Login success ", Toast.LENGTH_SHORT).show();
                                Intent intent  = new Intent(getApplicationContext(),Dashboard.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                    } else {

                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("regNo",regNo);
                        editor.commit();

                        //redirect to home page
                        Toast.makeText(getApplicationContext(), "Welcome.. Login success ", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(),Dashboard.class);
                        startActivity(intent);
                    }

                }else {
                    mMainActivityBinding.RegNo.setError("Username/Reg Number Required");
                }
            }
        });



    }
}