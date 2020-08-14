package com.example.foodorderingapp.viewmodel.authentication;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ConfirmationEmailViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private ConfirmationEmailCallbacks mConfirmationEmailCallbacks;

    public ConfirmationEmailViewModelFactory(ConfirmationEmailCallbacks confirmationEmailCallbacks) {
        mConfirmationEmailCallbacks = confirmationEmailCallbacks;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new ConfirmationEmailViewModel(mConfirmationEmailCallbacks);
    }
}
