package com.example.foodorderingapp.viewmodel.authentication;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.models.authentication.UserSignUp;
import com.example.foodorderingapp.util.Event;
import com.example.foodorderingapp.util.StrengthLevel;
import com.example.foodorderingapp.view.authentication.SignUpFragmentDirections;
import com.example.foodorderingapp.viewmodel.BaseViewModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpViewModel extends BaseViewModel implements TextWatcher {

    public MutableLiveData<StrengthLevel> strengthLevel = new MutableLiveData<>();
    public MutableLiveData<Integer> strengthColor = new MutableLiveData<>();

    public MutableLiveData<Integer> lowercase = new MutableLiveData<>();
    public MutableLiveData<Integer> uppercase = new MutableLiveData<>();
    public MutableLiveData<Integer> digit = new MutableLiveData<>();
    public MutableLiveData<Integer> specialChar = new MutableLiveData<>();

//    public MutableLiveData<Boolean> loading = new MutableLiveData<>();

    private MutableLiveData<Event<Boolean>> _isProgressEnabled = new MutableLiveData<>();
    public LiveData<Event<Boolean>> isProgressEnabled = _isProgressEnabled;

    private UserSignUp mUserSignUp;
    private SignUpResultCallbacks mSignUpResultCallbacks;
    private FirebaseAuth mAuth;

    public SignUpViewModel(SignUpResultCallbacks signUpResultCallbacks) {
        lowercase.setValue(0);
        uppercase.setValue(0);
        digit.setValue(0);
        specialChar.setValue(0);

        mUserSignUp = new UserSignUp();
        mSignUpResultCallbacks = signUpResultCallbacks;
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (charSequence != null) {

            if (hasLowerCase(charSequence)) lowercase.setValue(1);
            else lowercase.setValue(0);

            if (hasUpperCase(charSequence)) uppercase.setValue(1);
            else uppercase.setValue(0);

            if (hasDigit(charSequence)) digit.setValue(1);
            else digit.setValue(0);

            if (hasSpecialChar(charSequence)) specialChar.setValue(1);
            else specialChar.setValue(0);

            calculateStrength(charSequence);

            mUserSignUp.setPassword(charSequence.toString());
        }
    }

    private void calculateStrength(CharSequence password) {
        if (password.length() <= 7) {
            strengthColor.setValue(R.color.weak);
            strengthLevel.setValue(StrengthLevel.WEAK);
        } else if (password.length() <= 10 && password.length() >= 8) {
            if (lowercase.getValue() == 1 || uppercase.getValue() == 1 || digit.getValue() == 1 || specialChar.getValue() == 1) {
                strengthColor.setValue(R.color.medium);
                strengthLevel.setValue(StrengthLevel.MEDIUM);
            }
        } else if (password.length() <= 16 && password.length() >= 11) {
            if (lowercase.getValue() == 1 || uppercase.getValue() == 1 || digit.getValue() == 1 || specialChar.getValue() == 1) {
                strengthColor.setValue(R.color.medium);
                strengthLevel.setValue(StrengthLevel.STRONG);
            }
        } else if (password.length() > 16) {
            if (lowercase.getValue() == 1 || uppercase.getValue() == 1 || digit.getValue() == 1 || specialChar.getValue() == 1) {
                strengthColor.setValue(R.color.bulletproof);
                strengthLevel.setValue(StrengthLevel.BULLETPROOF);
            }
        }
    }

    private Boolean hasLowerCase(CharSequence charSequence) {
        Pattern pattern = Pattern.compile("[a-z]");
        Matcher hasLowerCase = pattern.matcher(charSequence);
        return hasLowerCase.find();
    }

    private Boolean hasUpperCase(CharSequence charSequence) {
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher hasUpperCase = pattern.matcher(charSequence);
        return hasUpperCase.find();
    }

    private Boolean hasDigit(CharSequence charSequence) {
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher hasDigit = pattern.matcher(charSequence);
        return hasDigit.find();
    }

    private Boolean hasSpecialChar(CharSequence charSequence) {
        Pattern pattern = Pattern.compile("[!@#$%^&*()_=+{}/.<>|\\\\~]");
        Matcher hasSpecialChar = pattern.matcher(charSequence);
        return hasSpecialChar.find();
    }

    public TextWatcher getNameSignUp() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable username) {
               mUserSignUp.setName(username.toString());
            }
        };
    }

    public TextWatcher getEmailSignUp() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable email) {
                mUserSignUp.setEmail(email.toString());
            }
        };
    }

    public void onSignUpClick(View view) {
        int errorCode = mUserSignUp.isValidUser();
        if (errorCode == 0) mSignUpResultCallbacks.onError("You must fill all of the information");
        else if (errorCode == 1) mSignUpResultCallbacks.onError("Please fill the valid email");
        else if (errorCode == 2)
            mSignUpResultCallbacks.onError("Your password must greater than 6 and should be in medium");
        else {
            _isProgressEnabled.setValue(new Event<>(true));
            mAuth.createUserWithEmailAndPassword(mUserSignUp.getEmail(), mUserSignUp.getPassword()).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    _isProgressEnabled.setValue(new Event<>(false));
                    mSignUpResultCallbacks.onSuccess("Your account had been created");
                    NavDirections action = SignUpFragmentDirections.actionSignUpFragmentToConfirmationEmailFragment();
                    Navigation.findNavController(view).navigate(action);
                } else {
                    _isProgressEnabled.setValue(new Event<>(false));
                    mSignUpResultCallbacks.onError("Your account hadn't been created. There had an error, please try again");
                }
            });
        }
    }
}
