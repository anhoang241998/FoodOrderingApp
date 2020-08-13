package com.example.foodorderingapp.viewmodel.authentication;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class SignUpViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private SignUpResultCallbacks mSignUpResultCallbacks;

    public SignUpViewModelFactory(SignUpResultCallbacks signUpResultCallbacks) {
        mSignUpResultCallbacks = signUpResultCallbacks;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new SignUpViewModel(mSignUpResultCallbacks);
    }
}
