package com.example.hellosunshine.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hellosunshine.Database.AuthViewModel;
import com.example.hellosunshine.Database.HelloSunshineDB;
import com.example.hellosunshine.Database.UserDAO;
import com.example.hellosunshine.R;
import com.example.hellosunshine.entities.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;

public class NewUserChildActivity extends AppCompatActivity {

    EditText childName, childNickname, childDay, childMonth, childYear, childGender;

    Button registerBtn;

    User user;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_child);

        mAuth = FirebaseAuth.getInstance();

        childName = findViewById(R.id.first_name);
        childNickname = findViewById(R.id.n_name);
        childDay = findViewById(R.id.day);
        childMonth = findViewById(R.id.month);
        childYear = findViewById(R.id.year);


        Intent intent = getIntent();
        String[] values = intent.getStringArrayExtra("newAccountInfo");

        childName.setText(values[1]);

        registerBtn = findViewById(R.id.btnRegister);

        registerBtn.setOnClickListener(v -> {
            String cName = childName.getText().toString();
            String cNickname = childNickname.getText().toString();
            String cDate = (childDay.getText().toString() + "/" + childMonth.getText().toString() + "/" + childYear.getText().toString());
            String cDay = childDay.getText().toString();
            String cMonth = childMonth.getText().toString();
            String cYear = childYear.getText().toString();

            String fName = values[0];
            String email = values[1];
            String pass = values[2];

            AuthViewModel authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

            authViewModel.registerUser(email, pass, fName, task -> {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    Toast.makeText(getApplicationContext(), "New User created", Toast.LENGTH_SHORT).show();
                } else {
                    Exception e = task.getException();
                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

           /*
            if (cName.length() == 0) {

                Toast toast = Toast.makeText(NewUserChildActivity.this, "Name?", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                startActivity(new Intent(NewUserChildActivity.this, NewUserChildActivity.class).putExtra("newChildInfo", new String[]{fName, email, pass, cName, cNickname, cDate, cDay, cMonth, cYear}));
            }

            */

        });
    }
}