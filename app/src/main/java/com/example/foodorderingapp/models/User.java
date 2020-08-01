package com.example.foodorderingapp.models;

import android.text.TextUtils;
import android.util.Patterns;

import androidx.annotation.NonNull;

public class User {

    @NonNull
    private String email, password;

    public User() {
    }

    public User(@NonNull String email, @NonNull String password) {
        this.email = email;
        this.password = password;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public int isValidData() {
        if (TextUtils.isEmpty(getEmail())) return 0;
        else if (!Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches()) return 1;
        else if (TextUtils.isEmpty(getPassword())) return 2;
        else if (getPassword().length() < 6) return 3;
        else return -1;
    }
}
