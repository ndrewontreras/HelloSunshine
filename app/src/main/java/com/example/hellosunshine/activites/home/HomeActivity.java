package com.example.hellosunshine.activites.home;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hellosunshine.Database.entities.TipOfTheDay;
import com.example.hellosunshine.Database.viewmodels.ChildViewModel;
import com.example.hellosunshine.Database.viewmodels.TipOfTheDayViewModel;
import com.example.hellosunshine.Database.viewmodels.UserViewModel;
import com.example.hellosunshine.R;
import com.example.hellosunshine.Database.entities.Child;
import com.example.hellosunshine.Database.entities.User;
import com.example.hellosunshine.activites.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.Async;

import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class HomeActivity extends AppCompatActivity {


    TextView childNameText, years, months, days, todTitle, todDesc;
    ImageView logoutImage;

    private FirebaseAuth mAuth;

    ChildViewModel childViewModel;

    UserViewModel userViewModel;

    ImageView homeChildImage;

    ScrollView homeScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        AsyncTask.execute(() -> {
            childViewModel = new ChildViewModel(getApplication());
            userViewModel = new UserViewModel(getApplication());
        });

        childNameText = findViewById(R.id.home_name);
        years = findViewById(R.id.years);
        months = findViewById(R.id.months);
        days = findViewById(R.id.days);
        homeChildImage = findViewById(R.id.childImageHome);
        homeScrollView = findViewById(R.id.homeScrollView);
        logoutImage = findViewById(R.id.logoutImageHome);
        todTitle = findViewById(R.id.todTitle);
        todDesc = findViewById(R.id.todDesc);

        Intent intent = getIntent();

        String checkFlag = intent.getStringExtra("flag");

        if(checkFlag.equals("NC")) {
            String parentEmail = intent.getStringExtra("email");
            boolean gender = intent.getBooleanExtra("gender", false);
            String[] values = intent.getStringArrayExtra("newChild");
            AsyncTask.execute(() -> {
                childViewModel.insert(new Child(values[0], values[1], values[2], gender, userViewModel.getUserByEmail(values[3]).getId()));
                User tempUser = userViewModel.getUserByEmail(parentEmail);
                Child tempChild = childViewModel.getChildByParent(tempUser.getId());
                childNameText.setText(values[0]);
                getAge(parentEmail);
                setTOD();
            });
        } else if(checkFlag.equals("LG")) {
            String parentEmail = intent.getStringExtra("email");
            AsyncTask.execute(() -> {
                User tempUser = userViewModel.getUserByEmail(parentEmail);
                Child tempChild = childViewModel.getChildByParent(tempUser.getId());
                childNameText.setText(tempChild.getName());
                getAge(parentEmail);
                setTOD();
            });
        }

        logoutImage.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Log out");
            builder.setMessage("Are you sure you want to log out?");
            builder.setPositiveButton("Confirm", (dialog, which) -> {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            });
            builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> {
            });

            AlertDialog dialog = builder.create();
            dialog.show();

        });
    }

    private void getAge(String email) {
        User tempUser = userViewModel.getUserByEmail(email);
        Child tempChild = childViewModel.getChildByParent(tempUser.getId());
        Log.d("child age", tempChild.getAge());
        String age = tempChild.getAge();
        String[] ageSplit = age.split("/");
        Log.d("birth month", ageSplit[0]);
        if(ageSplit.length < 3) {

        } else {
            int month = Integer.parseInt(ageSplit[0]);
            int day = Integer.parseInt(ageSplit[1]);
            int year = Integer.parseInt(ageSplit[2]);

            Calendar cal = Calendar.getInstance();

            int currDay = cal.get(Calendar.DAY_OF_MONTH);
            int currMonth = cal.get(Calendar.MONTH);
            int currYear = cal.get(Calendar.YEAR);

            int yearAge = Math.abs(currYear - year - 1);
            int monthAge = Math.abs(currMonth - month);
            int dayAge = Math.abs(currDay - day);
            years.setText(String.valueOf(yearAge));
            months.setText(String.valueOf(monthAge));
            days.setText(String.valueOf(dayAge));
        }
    }

    private void setTOD() {
        TipOfTheDayViewModel viewModel = new TipOfTheDayViewModel(getApplication());
        List<TipOfTheDay> list = viewModel.getAllTOD();
        int rand = (int)(Math.random() * 12) + 1;
        TipOfTheDay tod = viewModel.getTOD(rand);
        todTitle.setText(tod.getTitle());
        todDesc.setText(tod.getDescription());
    }
}
