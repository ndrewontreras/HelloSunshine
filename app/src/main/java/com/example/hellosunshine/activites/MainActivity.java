package com.example.hellosunshine.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hellosunshine.Database.HSViewModel;
import com.example.hellosunshine.Database.HelloSunshineDB;
import com.example.hellosunshine.Database.UserDAO;
import com.example.hellosunshine.R;
import com.example.hellosunshine.entities.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);

        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        //getActionBar().hide();

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

        User testUser = new User("Test Name1", "testuName1", "test1@email.com", "testpass1");
        HSViewModel viewModel = ViewModelProviders.of(this).get(HSViewModel.class);
        viewModel.insert(testUser);
        viewModel.getAllUsers().observe(this, userList -> {
                Log.d("usersTest", ": " + userList.size());

                for(User list: userList) {
                    Log.d("userText full name and email", list.getFullName() + " " + list.getEmail());
                }

                });





        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = username.getText().toString();
                String pword = password.getText().toString();


                if(uname.length() == 0 || pword.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Enter valid username and/or password", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(MainActivity.this, HomeActivity.class));
                }


                mAuth.signInWithEmailAndPassword(uname, pword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    // Sign in success, update UI with the signed-in user's information
                                    if(user != null) {
                                        UserDAO userDAO = HelloSunshineDB.getDatabase(MainActivity.this).userDao();
                                        User userData = userDAO.getUserByUsername(user.getEmail());
                                        Toast.makeText(getApplicationContext(), "Logged In", Toast.LENGTH_SHORT).show();
                                    }


                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

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