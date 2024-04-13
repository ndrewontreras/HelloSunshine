package com.example.hellosunshine.Database;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class AuthViewModel extends ViewModel {
    // Firebase Authentication instance
    private FirebaseAuth mAuth;

    // Constructor to initialize Firebase Authentication
    public AuthViewModel() {
        mAuth = FirebaseAuth.getInstance();
    }

    // Method to register a new user with Firebase Authentication
    public void registerUser(String email, String password, String fullName, OnCompleteListener<AuthResult> onCompleteListener) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                if (firebaseUser != null) {
                    UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder().setDisplayName(fullName).build();
                    firebaseUser.updateProfile(profileUpdate).addOnCompleteListener(profileTask -> {
                        if (profileTask.isSuccessful()) {
                            onCompleteListener.onComplete(task);
                        } else {
                            Exception profileException = profileTask.getException();
                            onCompleteListener.onComplete(task.addOnCompleteListener(profileUpdateTask -> {
                                if (!profileTask.isSuccessful()) {
                                    onCompleteListener.onComplete(task);
                                }
                            }));
                        }
                    });
                }
            } else {
                onCompleteListener.onComplete(task);
            }
        });
    }

    // Method to sign in an existing user with Firebase Authentication
    public void loginUser(String email, String password, OnCompleteListener<AuthResult> onCompleteListener) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(onCompleteListener);
    }
}
