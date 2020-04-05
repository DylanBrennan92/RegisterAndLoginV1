package com.example.registerandloginv1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class AddDogOwner extends AppCompatActivity {


    //Declaring vars - ending in ET for edit text fields
    EditText ownerNameET, dogNameET, breedET, dogAgeET, weightET, phoneNumET;
    RadioButton radio_kg, radio_lb;
    Button dogSubmitBtn;
    protected boolean kgChecked, lbChecked;

    private static final String TAG = "AddDog";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_dog_details);

        //assigning vars to input values
        ownerNameET = findViewById(R.id.ownerName);
        dogNameET = findViewById(R.id.dogName);
        breedET = findViewById(R.id.breed);
        dogAgeET = findViewById(R.id.dogAge);
        weightET = findViewById(R.id.weight);
        phoneNumET = findViewById(R.id.phoneNum);
        radio_kg = findViewById(R.id.radio_kg);
        radio_lb = findViewById(R.id.radio_lb);
        dogSubmitBtn = findViewById(R.id.dogSubmitBtn);


        //handling radio buttons set a checkedID to the radio buttons and alternate it based on user choice
        radio_kg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kgChecked=true;
                lbChecked=false;
            }
        });
        radio_lb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lbChecked=true;
                kgChecked=false;
            }
        });
        dogSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //have to use .toString() to use inputs from editText fields, messy
                String ownerName = ownerNameET.getText().toString();
                String dogName = dogNameET.getText().toString();
                String breed = breedET.getText().toString();
                String phoneNum = phoneNumET.getText().toString();

                int weight=0;
                try {
                    weight = Integer.parseInt(String.valueOf(weightET.getText()));
                    if (lbChecked) {
                        weight = weight / 2;
                    }
                    if (weight <= 0) {
                        weightET.setError("Please enter a valid weight");
                        weightET.requestFocus();
                    } else if (weight > 150) {
                        weightET.setError("Please enter a valid weight");
                        weightET.requestFocus();
                    }
                } catch (Exception e) {
                    weightET.setError("Error reading dog weight");
                    weightET.requestFocus();
                }

                //try - catch for dogAge int validation

                int dogAge=0;
                try {
                    dogAge = Integer.parseInt(String.valueOf(dogAgeET.getText()));
                    if (!(dogAge >= 0)) {
                        dogAgeET.setError("Please enter your dogs age (numeric)");
                        dogAgeET.requestFocus();
                    } else if (dogAge > 18) {
                        dogAgeET.setError("Dogs must be under 18 years of age");
                        dogAgeET.requestFocus();
                    }
                } catch (Exception e) {
                    dogAgeET.setError("error reading dog age");
                    dogAgeET.requestFocus();
                }


                //try - catch for weight int input validation

                //Basic input validation for empty Strings
                if (ownerName.isEmpty()) {
                    ownerNameET.setError("Name is required");
                    ownerNameET.requestFocus();
                }
                if (dogName.isEmpty()) {
                    dogNameET.setError("Dog name is required");
                    dogNameET.requestFocus();
                }
                if (breed.isEmpty()) {
                    breedET.setError("Breed is required");
                    breedET.requestFocus();
                }

                if (phoneNum.isEmpty()) {
                    phoneNumET.setError("A phone number is required");
                    phoneNumET.requestFocus();
                }
                if (phoneNum.length() != 10) {
                    phoneNumET.setError("Must be 10 digits long");
                    phoneNumET.requestFocus();
                }
                MainActivity m = new MainActivity();

                m.createDogOwnerUser(ownerName, dogName, breed, dogAge, weight,phoneNum);



            }

        });//end of dogSubmitBtn onclick listener

    }//end of onCreate
}
