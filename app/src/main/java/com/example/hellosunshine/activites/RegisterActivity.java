package com.example.hellosunshine.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hellosunshine.R;

public class RegisterActivity extends AppCompatActivity {

    EditText fullName, userName, email, password, cPassword;

    Button btnContinue;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullName = findViewById(R.id.fullname);
        userName = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        cPassword = findViewById(R.id.retype_password);


        btnContinue = findViewById(R.id.btnContinue);

        btnContinue.setOnClickListener(v -> {

            String fName = fullName.getText().toString();
            String emailAdd = email.getText().toString();
            String pass = password.getText().toString();
            String rePass = cPassword.getText().toString();

            if (!rePass.equals(pass)) {
                Toast toastPass = Toast.makeText(RegisterActivity.this, "Passwords don't match", Toast.LENGTH_SHORT);
                toastPass.show();
            } else if(fName.length() == 0) {
                Toast toastFName = Toast.makeText(RegisterActivity.this, "Please enter full name", Toast.LENGTH_SHORT);
                toastFName.show();
            } else {
                startActivity(new Intent(RegisterActivity.this, NewUserChildActivity.class).putExtra("newAccountInfo", new String[]{fName, emailAdd, pass}));
                finish();
            }

        });





    }
}