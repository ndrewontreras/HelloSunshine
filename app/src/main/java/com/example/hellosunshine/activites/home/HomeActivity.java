package com.example.hellosunshine.activites.home;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hellosunshine.Database.viewmodels.ChildViewModel;
import com.example.hellosunshine.Database.viewmodels.UserViewModel;
import com.example.hellosunshine.R;
import com.example.hellosunshine.Database.entities.Child;
import com.example.hellosunshine.Database.entities.User;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.Async;

import java.util.List;

public class HomeActivity extends AppCompatActivity {


    TextView childNameText;

    private FirebaseAuth mAuth;
    User user;

    Child child;

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
        homeChildImage = findViewById(R.id.childImageHome);
        homeScrollView = findViewById(R.id.homeScrollView);




        Intent intent = getIntent();

        String checkFlag = intent.getStringExtra("flag");

        if(checkFlag.equals("NC")) {
            String parentEmail = intent.getStringExtra("email");
            boolean gender = intent.getBooleanExtra("gender", false);
            String[] values = intent.getStringArrayExtra("newChild");
            AsyncTask.execute(() -> {
                childViewModel.insert(new Child(values[0], values[1], values[2], gender, userViewModel.getUserByEmail(values[3]).getId()));
                childNameText.setText(childViewModel.getChildByParent(userViewModel.getUserByEmail(parentEmail).getId()).getName());
                //Log.d("Child name", tempChild.getName());
            });
        } else if(checkFlag.equals("LG")) {
            String parentEmail = intent.getStringExtra("email");
            AsyncTask.execute(() -> {
                User tempUser = userViewModel.getUserByEmail(parentEmail);
                Child tempChild = childViewModel.getChildByParent(tempUser.getId());
                childNameText.setText(tempChild.getName());
            });
        }




        //childNameText.setText(childViewModel.getChildByParent(userViewModel.getUserByEmail(parentEmail).getId()).getName());

        //Loop through live data list, can't cast to basic list

        /*
        List<User> allUsers = new UserViewModel(getApplication()).getAllUsers();

        for(User user : allUsers) {

            /*if(user.getEmail().equals(mAuth.getCurrentUser().getEmail())) {
                childNameText.setText(new ChildViewModel(getApplication()).getChildByParent(user.getId()).getName());
            }

             */
         /*   Log.d("childs", new ChildViewModel(getApplication()).getChildByParent(user.getId()).getName());
        }

          */



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
