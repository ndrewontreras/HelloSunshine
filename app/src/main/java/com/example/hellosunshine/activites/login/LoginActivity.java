package com.example.hellosunshine.activites.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hellosunshine.Database.daos.ChildDAO;
import com.example.hellosunshine.Database.entities.Child;
import com.example.hellosunshine.Database.entities.TipOfTheDay;
import com.example.hellosunshine.Database.entities.User;
import com.example.hellosunshine.Database.viewmodels.AuthViewModel;
import com.example.hellosunshine.Database.HelloSunshineDB;
import com.example.hellosunshine.Database.viewmodels.ChildViewModel;
import com.example.hellosunshine.Database.viewmodels.TipOfTheDayViewModel;
import com.example.hellosunshine.Database.viewmodels.UserViewModel;
import com.example.hellosunshine.R;
import com.example.hellosunshine.activites.home.HomeActivity;
import com.example.hellosunshine.activites.register.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

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

        AsyncTask.execute(() -> {
            prepopulateTOD();
        });


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

        login.setOnClickListener(v -> {
            String uname = username.getText().toString();
            String pword = password.getText().toString();


            if(uname.length() == 0 || pword.length() == 0) {
                Toast.makeText(getApplicationContext(), "Enter valid username and/or password", Toast.LENGTH_SHORT).show();
            }

            AuthViewModel authViewModel = new ViewModelProvider(LoginActivity.this).get(AuthViewModel.class);
            authViewModel.loginUser(uname, pword, task -> {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    Toast.makeText(getApplicationContext(), "Logged in", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class).putExtra("email", username.getText().toString()).putExtra("flag",
                            "LG").putExtra("email", username.getText().toString()));
                } else {
                    Exception e = task.getException();
                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

         register.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class).putExtra("email", username.getText().toString())));
    }

    private void prepopulateTOD() {

        TipOfTheDayViewModel viewModel = new TipOfTheDayViewModel(getApplication());
        if(viewModel.getAllTOD().size() == 0) {
            TipOfTheDay tod1 = new TipOfTheDay("Live in the now!", "You hereby have permission to stop worrying about your checklist—doing the laundry, pumping, buying diapers—and learn to be present with your baby. Enjoy your precious moments together. —Wayne Fleisig, Ph.D.");
            TipOfTheDay tod2 = new TipOfTheDay("Chill out about toddler meals", "Expect odd food habits. Offer a variety. Don't push, don't panic. They'll eat when they're hungry. —Connie Diekman, R.D., Washington University in St. Louis");
            TipOfTheDay tod3 = new TipOfTheDay("Stick to an early bedtime", "Your child will get the sleep they need, and you'll get to recharge your batteries. —Jodi Mindell, Ph.D., author of Sleeping Through the Night");
            TipOfTheDay tod4 = new TipOfTheDay("Say \"no\"", "The better you get at turning down requests that aren't in your child's best interest, the fewer times you'll need to do so. You can say no once in the supermarket when your child asks to buy a carton of ice cream, or you can say it every night once that carton is sitting in your freezer at home. —David Ludwig, M.D., Ph.D., author of Ending the Food Fight");
            TipOfTheDay tod5 = new TipOfTheDay("Create mini-traditions", "Hang balloons around the kitchen table the night before your child's birthday so they wake up to a special day. Make a funny noise when it's just you and your kids in an elevator. Create a handshake that only they know—and save it for big moments. —Harley A. Rotbart, M.D., author of No Regrets Parenting");
            TipOfTheDay tod6 = new TipOfTheDay("Be ready for sick days", "Stock up on rehydration drinks like Pedialyte, Gatorade, or Vitamin Water so you don't have to run to the store in the middle of the night when your little one is vomiting. —Wendy Hunter, M.D., Rady Children's Hospital, University of California, San Diego");
            TipOfTheDay tod7 = new TipOfTheDay("Know your kid", "Each child is a unique combination of strengths and challenges. Try to tailor your response to fit the kid in front of you. —Eileen Kennedy-Moore, Ph.D., author of Smart Parenting for Smart Kids");
            TipOfTheDay tod8 = new TipOfTheDay("Let your partner take over", "They're all in, so encourage them to be in charge of bathing, reading, or tummy time (or all three). They're great bonding activities—and an opportunity for you to take a breather. —David L. Hill, M.D., author of Dad to Dad: Parenting Like a Pro");
            TipOfTheDay tod9 = new TipOfTheDay("Read to your child every single day", "It helps build imagination and is time well spent. —Christine Hohlbaum, mom of two and author of The Power of Slow");
            TipOfTheDay tod10 = new TipOfTheDay("Make time for yourself", "Make time for yourself without the guilt. Every kid needs a happy parent! — Yamel Belen, R.N., CLC");
            TipOfTheDay tod11 = new TipOfTheDay("Help your baby fall asleep on their own", "Feed them at the start of your bedtime routine. After a bath, books, and cuddling, put them down while they're drowsy but still awake. If you feed or rock them to sleep, they'll always need your help to nod off. —Dr. Mindell");
            TipOfTheDay tod12 = new TipOfTheDay("Trust your instincts", "Even if you can't diagnose what's wrong when your child doesn't feel well, your gut will tell you that they need to be checked out. —Ari Brown, M.D., author of Baby 411");
            viewModel.insert(tod1);
            viewModel.insert(tod2);
            viewModel.insert(tod3);
            viewModel.insert(tod4);
            viewModel.insert(tod5);
            viewModel.insert(tod6);
            viewModel.insert(tod7);
            viewModel.insert(tod8);
            viewModel.insert(tod9);
            viewModel.insert(tod10);
            viewModel.insert(tod11);
            viewModel.insert(tod12);
        }
    }
}