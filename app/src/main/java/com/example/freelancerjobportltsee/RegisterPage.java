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

public class RegisterPage extends AppCompatActivity {

    EditText edt_user,edt_password,edt_repassword,edt_fname,edt_lname,edt_mobile;
    Button btn_reg;
    private FirebaseAuth mAuth;
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

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewUser();
            }
        });
    }
    private void registerNewUser()
    {



        // Take the value of two edit texts in Strings
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
}