package com.example.hellosunshine.activites.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.hellosunshine.Database.viewmodels.ChildViewModel;
import com.example.hellosunshine.Database.viewmodels.UserViewModel;
import com.example.hellosunshine.R;
import com.example.hellosunshine.Database.entities.Child;
import com.example.hellosunshine.Database.entities.User;

public class HomeActivity extends AppCompatActivity {

    User user;

    Child child;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /*
        Intent intent = getIntent();
        String[] values = intent.getStringArrayExtra("newChildInfo");
        boolean gender = intent.getBooleanExtra("gender", false);


        child = new Child(values[3], values[4], values[5], gender);
        ChildViewModel viewModel1 = new ViewModelProvider(HomeActivity.this).get(ChildViewModel.class);
        viewModel1.insert(child);


        //System.out.println(user.getFullName() + " " + user.getUName() + " " + user.getEmail() + " " + child.getNickname() + " Child ID: " + child.getId());


        intent.putExtra("new user", user);
        intent.putExtra("new child", child);
        setResult(Activity.RESULT_OK, intent);
         */

    }

}
