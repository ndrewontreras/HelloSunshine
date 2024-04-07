package com.example.hellosunshine.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hellosunshine.R;
import com.example.hellosunshine.entities.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewUserChildActivity extends AppCompatActivity {

    EditText childName, childNickname, childDay, childMonth, childYear, childGender;

    Button registerBtn;

    User user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_child);

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
            String uName = values[1];
            String email = values[2];
            String pass = values[3];



            if(true) {
                Toast toast = Toast.makeText(NewUserChildActivity.this, "Passwords don't match", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                startActivity(new Intent(NewUserChildActivity.this, NewUserChildActivity.class).putExtra("newChildInfo", new String[]{fName, uName, email, pass, cName, cNickname, cDate, cDay, cMonth, cYear}));
            }
        });


    }


}