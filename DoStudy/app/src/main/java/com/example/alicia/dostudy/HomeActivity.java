package com.example.alicia.dostudy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alicia.dostudy.TimeTable.WeekActivity;

public class HomeActivity extends AppCompatActivity {

    private ImageView toDo, timeTable, calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initUI();
    }

    private void initUI() {
        toDo = (ImageView) findViewById(R.id.home_calendar);
        toDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, ToDoListActivity.class);
                startActivity(i);
            }
        });

        calendar = (ImageView) findViewById(R.id.home_calendar);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, CalendarActivity.class);
                startActivity(i);
            }
        });

        timeTable = (ImageView) findViewById(R.id.home_timetable);
        timeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toTimeTable = new Intent(HomeActivity.this, WeekActivity.class);
                startActivity(toTimeTable);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        checkNetworkConnection();
    }

    private void checkNetworkConnection() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean notification = sharedPreferences.getBoolean(getResources().getString(R.string.preferences_key_notifications), true);
        if(notification) {
            startService(new Intent(this, NotificationService.class));
        } else {
                stopService(new Intent(this, NotificationService.class));
        }
    }
}

