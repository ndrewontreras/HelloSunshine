package com.example.hellosunshine.activites.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hellosunshine.Database.entities.Child;
import com.example.hellosunshine.Database.viewmodels.AuthViewModel;
import com.example.hellosunshine.Database.viewmodels.ChildViewModel;
import com.example.hellosunshine.R;
import com.example.hellosunshine.Database.entities.User;
import com.example.hellosunshine.activites.home.HomeActivity;
import com.example.hellosunshine.activites.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class NewUserChildActivity extends AppCompatActivity {

    EditText childName, childNickname, childDay, childMonth, childYear, childGender;

    Button registerBtn;

    SwitchCompat genderSwitch;

    Child newChild;

    private ChildViewModel childViewModel;
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
        genderSwitch = findViewById(R.id.genderSwitch);

        genderSwitch.setSwitchTypeface(ResourcesCompat.getFont(getApplicationContext(), R.font.font));

        Intent intent = getIntent();
        String[] values = intent.getStringArrayExtra("newAccountInfo");


        registerBtn = findViewById(R.id.btnRegister);

        registerBtn.setOnClickListener(v -> {
            String cName = childName.getText().toString();
            String cNickname = childNickname.getText().toString();
            String cDate = (childDay.getText().toString() + "/" + childMonth.getText().toString() + "/" + childYear.getText().toString());
            String cDay = childDay.getText().toString();
            String cMonth = childMonth.getText().toString();
            String cYear = childYear.getText().toString();
            Boolean cGender = genderSwitch.isActivated();

            String fName = values[0];
            String email = values[1];
            String pass = values[2];

            AuthViewModel authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

            authViewModel.registerUser(email, pass, fName, task -> {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    addNewChild();
                    Toast.makeText(getApplicationContext(), "New User created", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(NewUserChildActivity.this, HomeActivity.class).putExtra("newChildInfo", new
                            String[]{fName, email, pass, cName, cNickname, cDate, cDay, cMonth, cYear}).putExtra("gender", cGender));
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
                startActivity(new Intent(NewUserChildActivity.this, NewUserChildActivity.class).putExtra("newChildInfo", new
                String[]{fName, email, pass, cName, cNickname, cDate, cDay, cMonth, cYear}));
            }

            */

        });
    }

    private void addNewChild() {
        String cName = childName.getText().toString();
        String cNickname = childNickname.getText().toString();
        String cDate = (childDay.getText().toString() + "/" + childMonth.getText().toString() + "/" + childYear.getText().toString());
        String cDay = childDay.getText().toString();
        String cMonth = childMonth.getText().toString();
        String cYear = childYear.getText().toString();
        Boolean cGender = genderSwitch.isActivated();
        newChild = new Child(cName, cNickname, cDate, cGender);
        childViewModel.insert(newChild);
    }
}