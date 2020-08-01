package com.example.foodorderingapp.viewmodel;

import android.app.Application;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.util.StrengthLevel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpViewModel extends AndroidViewModel implements TextWatcher {

    public MutableLiveData<StrengthLevel> strengthLevel = new MutableLiveData<>();
    public MutableLiveData<Integer> strengthColor = new MutableLiveData<>();

    public MutableLiveData<Integer> lowercase = new MutableLiveData<>();
    public MutableLiveData<Integer> uppercase = new MutableLiveData<>();
    public MutableLiveData<Integer> digit = new MutableLiveData<>();
    public MutableLiveData<Integer> specialChar = new MutableLiveData<>();

    public SignUpViewModel(@NonNull Application application) {
        super(application);
        lowercase.setValue(0);
        uppercase.setValue(0);
        digit.setValue(0);
        specialChar.setValue(0);
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
}
