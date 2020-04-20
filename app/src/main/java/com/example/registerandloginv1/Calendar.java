package com.example.registerandloginv1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class Calendar extends AppCompatActivity{

    TextView dateSelected;
    private static final String TAG = "Calendar";
    CalendarView mCalenderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        mCalenderView = findViewById(R.id.calendarView);

        mCalenderView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = (dayOfMonth) + "/" + (month+1) + "/" + year;

                Intent i = new Intent(Calendar.this, DogWalker.class);
                i.putExtra("date", date);
                startActivity(i);
            }
        });
    }

}
