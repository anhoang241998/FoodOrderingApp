package com.example.foodorderingapp.viewmodel;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public abstract class BaseViewModel extends ViewModel  {
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public BaseViewModel() {
        isLoading.setValue(false);
    }

    public MutableLiveData<Boolean> getLoading() {
        return isLoading;
    }

    public synchronized void setLoading(Boolean b) {
        isLoading.setValue(b);
    }

}
