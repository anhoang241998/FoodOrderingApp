package com.example.foodorderingapp.viewmodel.authentication;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodorderingapp.models.authentication.User;

public class ForgotPasswordViewModel extends ViewModel {
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();
    private User mUser;
    private ForgotPasswordCallbacks mForgotPasswordCallbacks;

    public ForgotPasswordViewModel(ForgotPasswordCallbacks forgotPasswordCallbacks) {
        mUser = new User();
        this.mForgotPasswordCallbacks = forgotPasswordCallbacks;
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
                mUser.setEmail(email.toString());

            }
        };
    }

    public void onForgotPasswordClicked(View view){
        int errorCode = mUser.isValidData();
        if (errorCode == 0) mForgotPasswordCallbacks.onError("You must enter email address");
        else if (errorCode == 1) mForgotPasswordCallbacks.onError("Your email is invalid");
    }
};

