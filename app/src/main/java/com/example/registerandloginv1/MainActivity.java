package com.example.registerandloginv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText emailReg, passwordReg;
    Button registerBtn;
    TextView goToLogin;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore db;
    String userID;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailReg = findViewById(R.id.email);
        passwordReg = findViewById(R.id.password);
        registerBtn = findViewById(R.id.registerBtn);
        goToLogin = findViewById(R.id.goToLogin);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailReg.getText().toString();
                String password = passwordReg.getText().toString();
                if(email.isEmpty()){
                    emailReg.setError("Email is required");
                    emailReg.requestFocus();
                }
                if(password.isEmpty()){
                    passwordReg.setError("Password is required");
                    passwordReg.requestFocus();
                }
                //if the users inputs validate, allow them to select an account type
                else{
                    mFirebaseAuth = FirebaseAuth.getInstance();

                    mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Intent x = new Intent(MainActivity.this, AccountType.class);
                                startActivity(x);
                            }
                        }
                    });
                }//end of else
            }
        });

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }//end of onCreate


    public void createDogOwnerUser(final String ownerName, final String dogName, final String breed, final int dogAge, final int weight, final String phoneNum){
            FirebaseFirestore db = FirebaseFirestore.getInstance();

                //Firestore cloud database below

                Map<String, Object> dogOwner = new HashMap<>();
                //dogOwner.put("email", email);
                dogOwner.put("ownerName", ownerName);
                dogOwner.put("dogName", dogName);
                dogOwner.put("breed", breed);
                dogOwner.put("dogAge", dogAge);
                dogOwner.put("dogWeight", weight);
                dogOwner.put("phoneNum", phoneNum);

                db.collection("dogOwner")
                        .add(dogOwner).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        startActivity(new Intent(MainActivity.this, AccountType.class));

                    }
                });
    }//end of createDogOwnerUser()

    public void createDogWalkerUser(String name, String phoneNum, String location, int weight){

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> dogWalker = new HashMap<>();
        dogWalker.put("name", name);
        dogWalker.put("phoneNum", phoneNum);
        dogWalker.put("location", location);
        dogWalker.put("weight", weight);

        db.collection("dogWalker")
                .add(dogWalker);



       // HomeActivity h = new HomeActivity();
       // h.startCalendar();
    }


}

