package com.example.foodorderingapp.viewmodel.authentication;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodorderingapp.util.Event;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ConfirmationEmailViewModel extends ViewModel {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private ConfirmationEmailCallbacks mConfirmationEmailCallbacks;
    public MutableLiveData<Event<Boolean>> _isProgressEnabled = new MutableLiveData<>();
    public LiveData<Event<Boolean>> isProgressEnabled = _isProgressEnabled;

    public ConfirmationEmailViewModel(ConfirmationEmailCallbacks confirmationEmailCallbacks) {
        this.mConfirmationEmailCallbacks = confirmationEmailCallbacks;
        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    public void onSendEmailConfirmation(View view) {
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser != null) {
            mFirebaseUser.sendEmailVerification().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    mConfirmationEmailCallbacks.onSuccess("Verification email had been sent");
                } else {
                    mConfirmationEmailCallbacks.onFailure("Email cannot send. Please try again");
                }
            });
        }
    }

}
