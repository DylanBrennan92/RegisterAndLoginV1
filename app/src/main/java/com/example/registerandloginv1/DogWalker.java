package com.example.registerandloginv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DogWalker extends AppCompatActivity {

    private TextView dateAvailable;
    private TextView dateSelected;
    private Button goToCalendarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_walker);
        goToCalendarBtn = findViewById(R.id.goToCalendarBtn);
        dateSelected = findViewById(R.id.dateSelected);


        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        dateSelected.setText(date);

        goToCalendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(DogWalker.this, Calendar.class);
                startActivity(x);
            }
        });

    }
}
