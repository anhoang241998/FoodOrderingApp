package com.example.foodorderingapp.viewmodel;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.foodorderingapp.models.authentication.User;
import com.example.foodorderingapp.view.authentication.LogInFragmentDirections;
import com.google.firebase.auth.FirebaseAuth;

public class LogInViewModel extends BaseViewModel {
    private User user;
    private LogInResultCallbacks mLogInResultCallbacks;
    private FirebaseAuth mAuth;

    public LogInViewModel(LogInResultCallbacks logInResultCallbacks) {
        mLogInResultCallbacks = logInResultCallbacks;
        user = new User();
        mAuth = FirebaseAuth.getInstance();
    }

    public TextWatcher getEmailTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable email) {
                user.setEmail(email.toString());
            }
        };
    }

    public TextWatcher getPasswordTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable pass) {
                user.setPassword(pass.toString());
            }
        };
    }

    public void onLoginClicked(View view) {
        int errorCode = user.isValidData();
        if (errorCode == 0) mLogInResultCallbacks.onError("You must enter email address");
        else if (errorCode == 1) mLogInResultCallbacks.onError("Your email is invalid");
        else if (errorCode == 2) mLogInResultCallbacks.onError("You must enter password");
        else {
            mAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    mLogInResultCallbacks.onSuccess("Login success");
                    NavDirections actionSignIn = LogInFragmentDirections.actionList();
                    Navigation.findNavController(view).navigate(actionSignIn);
                } else {
                    mLogInResultCallbacks.onError("Login fail!");
                }

            });


        }
    }

}
