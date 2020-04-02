package com.example.registerandloginv1;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class accountType extends AppCompatActivity {

    Button dogOwnerBtn, dogWalkerBtn;
    ProgressBar progressBar;

    protected void onCreate() {
        //savedInstanceState was throwing an error so I commented it out, might be an issue..
        //super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_type);
    }

    public void selectAccountType() {
        dogOwnerBtn = findViewById(R.id.dogOwnerBtn);
        dogWalkerBtn = findViewById(R.id.dogWalkerBtn);


        dogOwnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(accountType.this, AddDogOwner.class));
                Toast.makeText(accountType.this, "Dog Owner selected", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        dogWalkerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(accountType.this, "Dog Walker selected", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }
}



