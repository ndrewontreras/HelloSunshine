package com.example.hellosunshine.activites.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hellosunshine.Database.viewmodels.AuthViewModel;
import com.example.hellosunshine.Database.HelloSunshineDB;
import com.example.hellosunshine.R;
import com.example.hellosunshine.activites.home.HomeActivity;
import com.example.hellosunshine.activites.register.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;

    Button login, register;

    HelloSunshineDB helloSunshineDB;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        login = findViewById(R.id.loginButtom);
        register = findViewById(R.id.buttonRegister);

        RoomDatabase.Callback myCallback = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }
        };

        helloSunshineDB = Room.databaseBuilder(getApplicationContext(), HelloSunshineDB.class,
                "HelloSunshineDB").addCallback(myCallback).build();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = username.getText().toString();
                String pword = password.getText().toString();


                if(uname.length() == 0 || pword.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Enter valid username and/or password", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(MainActivity.this, HomeActivity.class));
                }

                AuthViewModel authViewModel = new ViewModelProvider(LoginActivity.this).get(AuthViewModel.class);
                authViewModel.loginUser(uname, pword, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        Toast.makeText(getApplicationContext(), "Logged in", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    } else {
                        Exception e = task.getException();
                        Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

         register.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(LoginActivity.this, RegisterActivity.class).putExtra("email", username.getText().toString()));
             }
         });
    }
}