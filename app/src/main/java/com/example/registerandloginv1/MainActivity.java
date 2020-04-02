package com.example.registerandloginv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    protected void onCreate(Bundle savedInstanceState) {
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
                else if(email.isEmpty() && password.isEmpty()){
                    Toast.makeText(MainActivity.this, "Fields are not filled in!", Toast.LENGTH_SHORT).show();
                }
                //if the users inputs validate, allow them to select an account type
                else{
                   accountType a = new accountType();
                   a.onCreate();
                   a.selectAccountType();

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


    public void createDogOwnerUser(){
      final String email = emailReg.getText().toString();
      String password = passwordReg.getText().toString();

         mFirebaseAuth = FirebaseAuth.getInstance();
         final FirebaseFirestore db = FirebaseFirestore.getInstance();

    mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful()){
                //Firestore cloud database below
                userID = mFirebaseAuth.getCurrentUser().getUid();
                DocumentReference documentReference = db.collection("users").document(userID);
                Map<String, Object> user = new HashMap<>();
                user.put("email", email);
                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Log.d(TAG, "onSuccess: User profile is created for  " + userID );
                    }
                });
                startActivity(new Intent(MainActivity.this, accountType.class));
                Toast.makeText(MainActivity.this, "Account stored in database", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(MainActivity.this, "Error creating account in database", Toast.LENGTH_SHORT).show();
            }
        }
    });

    }//end of createDogOwnerUser()
}
