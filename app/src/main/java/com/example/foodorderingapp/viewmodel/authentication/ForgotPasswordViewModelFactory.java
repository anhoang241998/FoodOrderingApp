package com.example.foodorderingapp.viewmodel.authentication;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ForgotPasswordViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private ForgotPasswordCallbacks mForgotPasswordCallbacks;

    public ForgotPasswordViewModelFactory(ForgotPasswordCallbacks forgotPasswordCallbacks) {
        mForgotPasswordCallbacks = forgotPasswordCallbacks;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new ForgotPasswordViewModel(mForgotPasswordCallbacks);
    }
}
