package com.example.freelancerjobportltsee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterPage extends AppCompatActivity {

    EditText edt_user,edt_password,edt_repassword,edt_fname,edt_lname,edt_mobile;
    Button btn_reg;
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ResgisterInfo registerinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        edt_user = findViewById(R.id.edt_user);
        edt_password =findViewById(R.id.edt_password);
        btn_reg = findViewById(R.id.btn_register);
        edt_fname = findViewById(R.id.edt_fname);
        edt_lname = findViewById(R.id.edt_lname);
        edt_repassword =findViewById(R.id.edt_repassword);
        edt_mobile = findViewById(R.id.edt_mobile);

        mAuth = FirebaseAuth.getInstance();

        // instance of our Firebase database.
        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("ResgisterInfo");
        // initializing our object
        // class variable.
        registerinfo = new ResgisterInfo();

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewUser();
            }
        });
    }
    private void registerNewUser()
    {



        // Take the value of edit texts in Strings
        String email, password,repassword,mobile,fname,lname;
        email = edt_user.getText().toString();
        password = edt_password.getText().toString();
        fname = edt_fname.getText().toString();
        lname = edt_lname.getText().toString();
        repassword = edt_repassword.getText().toString();
        mobile = edt_mobile.getText().toString();


        // Validations for input email and password
        if (TextUtils.isEmpty(fname)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter first name!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }

        if (TextUtils.isEmpty(lname)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter last name!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter email!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter password!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }

        if (TextUtils.isEmpty(repassword)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter repassword!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }

        if (TextUtils.equals(password,repassword)) {

        }
        else {
            Toast.makeText(getApplicationContext(),
                            "Password and re-password are not same!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }

        if (TextUtils.isEmpty(mobile)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter mobile number!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // create new user or register new user
        mAuth
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),
                                            "Registration successful!",
                                            Toast.LENGTH_LONG)
                                    .show();

                            addDatatoFirebase(fname,lname,email,mobile,mAuth.getUid().toString());
                            // if the user created intent to login activity
                            Intent intent
                                    = new Intent(RegisterPage.this,
                                    LoginPage.class);
                            startActivity(intent);
                        }

                        else {

                            // Registration failed
                            Toast.makeText(
                                            getApplicationContext(),
                                            "Registration failed!!"
                                                    + " Please try again later",
                                            Toast.LENGTH_LONG)
                                    .show();

                            // hide the progress bar

                        }
                    }
                });
    }
    private void addDatatoFirebase(String fname, String lname, String email,String mobile,String uid) {
        // below 3 lines of code is used to set
        // data in our object class.
        registerinfo.setFname(fname);
        registerinfo.setLname(lname);
        registerinfo.setEmail(email);
        registerinfo.setMobile(mobile);




        // we are use add value event listener method
        // which is called with database reference.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                databaseReference.child(uid).setValue(registerinfo);

                // after adding this data we are showing toast message.
                Toast.makeText(RegisterPage.this, "data added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(RegisterPage.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}