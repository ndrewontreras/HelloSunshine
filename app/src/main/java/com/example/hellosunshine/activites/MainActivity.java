package com.example.hellosunshine.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hellosunshine.Database.HelloSunshineDB;
import com.example.hellosunshine.R;
import com.example.hellosunshine.entities.User;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;

    Button login, register;

    HelloSunshineDB helloSunshineDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        //getActionBar().hide();

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
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Logged In", Toast.LENGTH_SHORT).show();
                }

            }
        });

         register.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(MainActivity.this, RegisterActivity.class));
             }
         });
    }

    public void login(View v) {

    }
}