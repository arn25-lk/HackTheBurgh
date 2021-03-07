package com.example.projectfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirestoreRegistrar;

import java.util.HashMap;
import java.util.Map;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private EditText editName, editTextEmail, editTextPassword;
    private ImageView banner;
    private static String userPreferences = "";
    static final int CHOOSE_STUFF = 30;
    private FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        mAuth = FirebaseAuth.getInstance();



        editName = (EditText) findViewById(R.id.name);
        editName.setOnClickListener(this);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextEmail.setOnClickListener(this);
        editTextPassword = (EditText) findViewById(R.id.password);
        editTextPassword.setOnClickListener(this);

        fStore = FirebaseFirestore.getInstance();
        findViewById(R.id.registerUser).setOnClickListener(this);

    }



    public void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String name = editName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(name.isEmpty()){
            editName.setError("Name is required");
            editName.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }if(email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide valid email");
            editTextEmail.requestFocus();
            return;
        }
        if(password.length()<6){
            editTextPassword.setError("Password must have more than 6 characters");
            editTextPassword.requestFocus();
            return;
        }

        //User user  = new User(name, email, (new ChooseClassifications().getUserSelection()));
        Intent chooseStuff = new Intent(this, ChooseClassifications.class);
        startActivityForResult(chooseStuff, 30);





        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>(){

                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //TODO: Add user class
                            DocumentReference documentReference = fStore.collection("users").document(mAuth.getCurrentUser().getUid());
                            Map<String, Object> user = new HashMap<>();
                            user.put("name", name);
                            user.put("email", email);
                            user.put("preferences", userPreferences);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG", "onSuccess: user Profile is created" + mAuth.getCurrentUser().getUid());
                                }
                            });
                           /* FirebaseFirestore.getInstance().collection("users")
                                    .add(user)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Toast.makeText(RegisterUser.this,
                                                    "User has been registered successfully",
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(RegisterUser.this,
                                                    "Failed to register user",
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    });*/
                        }else{
                            Toast.makeText(RegisterUser.this,
                                    "Failed to Register User",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CHOOSE_STUFF) {
            if(resultCode == Activity.RESULT_OK){
                String result = data.getStringExtra("result");
                userPreferences = result;
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    @Override
    public void onClick(@NotNull View v) {
        switch(v.getId()){
            case R.id.registerUser:
                registerUser();
                break;
        }
    }
}