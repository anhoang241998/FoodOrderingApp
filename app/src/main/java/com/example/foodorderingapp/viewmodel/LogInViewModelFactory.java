package com.example.foodorderingapp.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class LogInViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private LogInResultCallbacks logInResultCallbacks;

    public LogInViewModelFactory(LogInResultCallbacks logInResultCallbacks) {
        this.logInResultCallbacks = logInResultCallbacks;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new LogInViewModel(logInResultCallbacks);
    }
}
