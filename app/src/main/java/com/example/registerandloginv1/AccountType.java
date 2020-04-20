package com.example.registerandloginv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AccountType extends AppCompatActivity {

    Button dogOwnerBtn, dogWalkerBtn;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_type);
        //savedInstanceState was throwing an error so I commented it out, might be an issue..

        dogOwnerBtn = findViewById(R.id.dogOwnerBtn);
        dogWalkerBtn = findViewById(R.id.dogWalkerBtn);

        dogOwnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountType.this, AddDogOwner.class));
                Toast.makeText(AccountType.this, "Dog Owner selected", Toast.LENGTH_LONG).show();
            }
        });

        dogWalkerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(AccountType.this, AddDogWalker.class));
                Toast.makeText(AccountType.this, "Dog Walker selected", Toast.LENGTH_LONG).show();
            }

        });
    }
}



