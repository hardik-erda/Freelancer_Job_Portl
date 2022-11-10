package com.example.freelancerjobportltsee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class RoleSelectionPage extends AppCompatActivity {
    CardView cv_freelancer,cv_hire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_selection_page);


        cv_freelancer = findViewById(R.id.cv_freelancer);
        cv_hire = findViewById(R.id.cv_hire);


        cv_freelancer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RoleSelectionPage.this, "Freelancer", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(RoleSelectionPage.this,LoginPage.class);
                i.putExtra("Role","Freelancer");
                startActivity(i);
            }
        });

        cv_hire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RoleSelectionPage.this, "Hire", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(RoleSelectionPage.this,LoginPage.class);
                i.putExtra("Role","Hire");
                startActivity(i);
            }
        });
    }
}