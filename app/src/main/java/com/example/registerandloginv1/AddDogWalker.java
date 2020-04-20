package com.example.registerandloginv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddDogWalker extends AppCompatActivity {

    EditText nameET, phoneNumET, locationET, weightET;
    Button submitWalkerBtn;
    CheckBox ageCheck, termsCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_walker_details);

        nameET = findViewById(R.id.name);
        phoneNumET = findViewById(R.id.phoneNum);
        locationET = findViewById(R.id.location);
        weightET = findViewById(R.id.weight);
        submitWalkerBtn = findViewById(R.id.submitWalkerBtn);
        ageCheck = findViewById(R.id.ageCheck);
        termsCheck = findViewById(R.id.termsCheck);

        submitWalkerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameET.getText().toString();
                String phoneNum = phoneNumET.getText().toString();
                String location = locationET.getText().toString();


                if (name.isEmpty()) {
                    nameET.setError("Error, name required");
                    nameET.requestFocus();

                } else if (name.length() < 3) {
                    nameET.setError("Name too short");
                    nameET.requestFocus();
                }
                if (phoneNum.length() != 10) {
                    phoneNumET.setError("Not a valid phone number");
                    phoneNumET.requestFocus();
                }
                if (location.length() < 3) {
                    locationET.setError("Not a valid location");
                    locationET.requestFocus();
                }
                if (!ageCheck.isChecked() && !termsCheck.isChecked()) {
                    ageCheck.setError("Error, must be 18 or over");
                    ageCheck.requestFocus();
                    termsCheck.setError("Please review our T&C's");
                    termsCheck.requestFocus();
                }

                int weight=0;
                try{
                    weight= Integer.parseInt(String.valueOf((weightET.getText())));
                    //if statement just to confirm weight restirctions at 0 if an empty weightET
                    if(weightET.getText().toString().isEmpty()){
                        weight=0;
                    }
                }
                catch(Exception e){

                }
                //MainActivity m = new MainActivity();
               createDogWalkerUser(name, phoneNum, location, weight);
            }

            });


    }//end of onCreate()

    public void openCalender() {
        Intent goToCal = new Intent(this, Calendar.class);
        startActivity(goToCal);
    }

    public void createDogWalkerUser(String name, String phoneNum, String location, int weight){

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> dogWalker = new HashMap<>();
        dogWalker.put("name", name);
        dogWalker.put("phoneNum", phoneNum);
        dogWalker.put("location", location);
        dogWalker.put("weight", weight);

        db.collection("dogWalker")
                .add(dogWalker);
        openCalender();
    }
}

