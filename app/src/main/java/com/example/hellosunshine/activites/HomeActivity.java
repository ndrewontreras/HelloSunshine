package com.example.hellosunshine.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.hellosunshine.Database.UserViewModel;
import com.example.hellosunshine.R;
import com.example.hellosunshine.entities.Child;
import com.example.hellosunshine.entities.User;

public class HomeActivity extends AppCompatActivity {

    User user;

    Child child;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_child);

        Intent intent = getIntent();
        String[] values = intent.getStringArrayExtra("newChildInfo");

        //user = new User(values[0], values[1], values[2], values[3]);
        child = new Child(values[3], values[4], values[5]);



        //System.out.println(user.getFullName() + " " + user.getUName() + " " + user.getEmail() + " " + child.getNickname() + " Child ID: " + child.getId());


        intent.putExtra("new user", user);
        intent.putExtra("new child", child);
        setResult(Activity.RESULT_OK, intent);


        UserViewModel viewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        viewModel.insert(user);
        System.out.println(viewModel.getAllUsers().toString());











    }

}
