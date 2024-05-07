package com.example.hellosunshine.activites.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hellosunshine.Database.viewmodels.AuthViewModel;
import com.example.hellosunshine.Database.viewmodels.ChildViewModel;
import com.example.hellosunshine.Database.viewmodels.UserViewModel;
import com.example.hellosunshine.R;
import com.example.hellosunshine.Database.entities.User;
import com.example.hellosunshine.activites.home.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class NewUserChildActivity extends AppCompatActivity {

    EditText childName, childNickname, childDay, childMonth, childYear;

    Button registerBtn;

    SwitchCompat genderSwitch;

    UserViewModel userViewModel;
    User newUser;

    private ChildViewModel childViewModel;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_child);

        mAuth = FirebaseAuth.getInstance();

        AsyncTask.execute(() -> {
            userViewModel = new UserViewModel(getApplication());
            childViewModel = new ChildViewModel(getApplication());
        });


        childName = findViewById(R.id.first_name);
        childNickname = findViewById(R.id.n_name);
        childDay = findViewById(R.id.day);
        childMonth = findViewById(R.id.month);
        childYear = findViewById(R.id.year);
        genderSwitch = findViewById(R.id.genderSwitch);

        genderSwitch.setSwitchTypeface(ResourcesCompat.getFont(getApplicationContext(), R.font.font));


        Intent intent = getIntent();
        String[] values = intent.getStringArrayExtra("newAccountInfo");


        registerBtn = findViewById(R.id.btnRegister);

        registerBtn.setOnClickListener(v -> {
            String cName = childName.getText().toString();
            String cNickname = childNickname.getText().toString();
            String cDate = (childMonth.getText().toString() + "/" + childDay.getText().toString() + "/" + childYear.getText().toString());
            Boolean cGender = genderSwitch.isActivated();

            String fName = values[0];
            String email = values[1];
            String pass = values[2];

            AuthViewModel authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

            authViewModel.registerUser(email, pass, fName, task -> {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    AsyncTask.execute(() -> {
                        addNewUser();
                    });
                    Toast.makeText(getApplicationContext(), "New User created", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(NewUserChildActivity.this, HomeActivity.class).putExtra("newChild", new String[]{cName, cNickname,
                            cDate, email}).putExtra("gender", cGender).putExtra("flag", "NC").putExtra("email", email));
                } else {
                    Exception e = task.getException();
                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void addNewUser() {
        Intent intent = getIntent();
        String[] values = intent.getStringArrayExtra("newAccountInfo");
        String fName = values[0];
        String email = values[1];
        String pass = values[2];
        addNewUser(email, pass, fName);
    }

    private void addNewUser(String email, String password, String fullName) {
        newUser = new User(email, password, fullName);
        userViewModel.insert(newUser);
    }
}