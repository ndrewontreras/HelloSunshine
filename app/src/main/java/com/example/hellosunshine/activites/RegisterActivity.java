package com.example.hellosunshine.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.hellosunshine.R;

public class RegisterActivity extends AppCompatActivity {

    EditText fName, lName, email;

    Button btnContinue;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fName = findViewById(R.id.fullname);
        lName = findViewById(R.id.lname);
        email = findViewById(R.id.email);

        btnContinue = findViewById(R.id.btnContinue);

        btnContinue.setOnClickListener(v -> {
            String firstName = fName.getText().toString();
            String lastName = lName.getText().toString();
            String emailAd = email.getText().toString();

            startActivity(new Intent(RegisterActivity.this, RegisterConActivity.class).putExtra("Fname, Lname, email", new String[]{firstName, lastName, emailAd}));


        });





    }
}