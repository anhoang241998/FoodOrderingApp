package com.example.foodorderingapp.models.authentication;

import android.text.TextUtils;
import android.util.Patterns;

import androidx.annotation.NonNull;

public class UserSignUp {

    @NonNull
    String name, email, password;

    public UserSignUp() {
    }

    public UserSignUp(@NonNull String name, @NonNull String email, @NonNull String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }


    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
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

    public int isValidUser(){
        if (TextUtils.isEmpty(getName()) && TextUtils.isEmpty(getEmail()) && TextUtils.isEmpty(getPassword())) return 0;
        else if (!Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches()) return 1;
        else if (getPassword().length() < 6) return 2;
        else return -1;
    }
}
