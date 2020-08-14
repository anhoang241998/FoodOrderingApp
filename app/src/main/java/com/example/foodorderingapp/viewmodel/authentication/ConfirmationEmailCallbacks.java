package com.example.foodorderingapp.viewmodel.authentication;

public interface ConfirmationEmailCallbacks {
    void onSuccess(String message);
    void onFailure(String message);
}
