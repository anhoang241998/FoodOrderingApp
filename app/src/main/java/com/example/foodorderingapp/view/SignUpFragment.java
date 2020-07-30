package com.example.foodorderingapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.util.PasswordStrengthCalculator;
import com.example.foodorderingapp.util.StrengthLevel;

public class SignUpFragment extends Fragment {
    View view, mStrengthLevelIndicator;
    Toolbar mToolbar;
    EditText mName, mEmail, mPassword;
    Button mSignUp;
    TextView mStrengthLevelTxt, mUpperCaseText, mLowerCaseText, mDigitsText, mSpecialCharactersText;
    ImageView mUpperCaseImage, mLowerCaseImage, mDigitsImage, mSpecialCharImage;

    private Integer color = R.color.weak;
    private PasswordStrengthCalculator mPasswordStrengthCalculator = new PasswordStrengthCalculator();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        mToolbar = view.findViewById(R.id.toolbar_signUp);
        mName = view.findViewById(R.id.edt_username);
        mEmail = view.findViewById(R.id.edt_email);
        mPassword = view.findViewById(R.id.edt_password);
        mSignUp = view.findViewById(R.id.btn_create_account);
        mStrengthLevelTxt = view.findViewById(R.id.strength_level_txt);
        mStrengthLevelIndicator = view.findViewById(R.id.strength_level_indicator);
        mUpperCaseImage = view.findViewById(R.id.upperCase_img);
        mLowerCaseImage = view.findViewById(R.id.lowerCase_img);
        mUpperCaseText = view.findViewById(R.id.upperCase_txt);
        mLowerCaseText = view.findViewById(R.id.lowerCase_txt);
        mDigitsImage = view.findViewById(R.id.digit_img);
        mDigitsText = view.findViewById(R.id.digit_txt);
        mSpecialCharImage = view.findViewById(R.id.specialChar_img);
        mSpecialCharactersText = view.findViewById(R.id.specialChar_txt);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
            setHasOptionsMenu(true);
        }

        mToolbar.setNavigationOnClickListener(v -> {
            getActivity().onBackPressed();
        });

        mPassword.addTextChangedListener(mPasswordStrengthCalculator);

        mPasswordStrengthCalculator.strengthLevel.observe(this, strengthLevel -> {
            displayStrengthLevel(strengthLevel);
        });

        mPasswordStrengthCalculator.strengthColor.observe(this, strengthColor -> {
            color = strengthColor;
        });

        mPasswordStrengthCalculator.lowercase.observe(this, value -> {
            displayPasswordSuggestion(value, mLowerCaseImage, mLowerCaseText);
        });

        mPasswordStrengthCalculator.uppercase.observe(this, value -> {
            displayPasswordSuggestion(value, mUpperCaseImage, mUpperCaseText);
        });

        mPasswordStrengthCalculator.digit.observe(this, value -> {
            displayPasswordSuggestion(value, mDigitsImage, mDigitsText);
        });

        mPasswordStrengthCalculator.specialChar.observe(this, value -> {
            displayPasswordSuggestion(value, mSpecialCharImage, mSpecialCharactersText);
        });

    }

    private void displayPasswordSuggestion(Integer value, ImageView imageView, TextView textView) {
        if (value == 1) {
            imageView.setColorFilter(ContextCompat.getColor(getContext(), R.color.bulletproof));
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.bulletproof));
        } else {
            imageView.setColorFilter(ContextCompat.getColor(getContext(), R.color.darkGray));
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.darkGray));
        }
    }

    private void displayStrengthLevel(StrengthLevel strengthLevel) {
        mSignUp.setEnabled(true);
        mStrengthLevelTxt.setText(strengthLevel.name());
        mStrengthLevelTxt.setTextColor(ContextCompat.getColor(getContext(), color));
        mStrengthLevelIndicator.setBackgroundColor(ContextCompat.getColor(getContext(), color));

    }



}