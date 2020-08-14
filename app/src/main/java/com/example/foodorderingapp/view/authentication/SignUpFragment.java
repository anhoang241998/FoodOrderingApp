package com.example.foodorderingapp.view.authentication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.databinding.FragmentSignUpBinding;
import com.example.foodorderingapp.util.EventObserver;
import com.example.foodorderingapp.util.LoadingDialog;
import com.example.foodorderingapp.util.SoftKeyboardUtil;
import com.example.foodorderingapp.util.StrengthLevel;
import com.example.foodorderingapp.viewmodel.authentication.SignUpResultCallbacks;
import com.example.foodorderingapp.viewmodel.authentication.SignUpViewModel;
import com.example.foodorderingapp.viewmodel.authentication.SignUpViewModelFactory;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpFragment extends Fragment implements SignUpResultCallbacks {
    View view, mStrengthLevelIndicator;
    Toolbar mToolbar;
    EditText mName, mEmail, mPassword;
    NestedScrollView mNestedScrollView;
    Button mSignUp;
    TextView mStrengthLevelTxt, mUpperCaseText, mLowerCaseText, mDigitsText, mSpecialCharactersText;
    ImageView mUpperCaseImage, mLowerCaseImage, mDigitsImage, mSpecialCharImage;
    TextInputLayout mTextInputLayout;
    FragmentSignUpBinding mFragmentSignUpBinding;
    SoftKeyboardUtil mSoftKeyboardUtil;
    private LoadingDialog mLoadingDialog;


    private Integer color = R.color.weak;
    private SignUpViewModel mSignUpViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentSignUpBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false);
        view = mFragmentSignUpBinding.getRoot();
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
        mNestedScrollView = view.findViewById(R.id.nestedScrollView_sign_up);
        mTextInputLayout = view.findViewById(R.id.edt_password_container);
        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentSignUpBinding.setViewModel(ViewModelProviders.of(this, new SignUpViewModelFactory(this)).get(SignUpViewModel.class));
        mSignUpViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);
        mSoftKeyboardUtil = new SoftKeyboardUtil(getActivity());
        mLoadingDialog = new LoadingDialog(getActivity());
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
            setHasOptionsMenu(true);
        }

        mToolbar.setNavigationOnClickListener(v -> {
            getActivity().onBackPressed();
        });

        mSignUpViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);

        mPassword.addTextChangedListener(mSignUpViewModel);

        mSignUpViewModel.strengthLevel.observe(this, strengthLevel -> {
            displayStrengthLevel(strengthLevel);
        });

        mSignUpViewModel.strengthColor.observe(this, strengthColor -> {
            color = strengthColor;
        });

        mSignUpViewModel.lowercase.observe(this, value -> {
            displayPasswordSuggestion(value, mLowerCaseImage, mLowerCaseText);
        });

        mSignUpViewModel.uppercase.observe(this, value -> {
            displayPasswordSuggestion(value, mUpperCaseImage, mUpperCaseText);
        });

        mSignUpViewModel.digit.observe(this, value -> {
            displayPasswordSuggestion(value, mDigitsImage, mDigitsText);
        });

        mSignUpViewModel.specialChar.observe(this, value -> {
            displayPasswordSuggestion(value, mSpecialCharImage, mSpecialCharactersText);
        });


        mSignUpViewModel.isProgressEnabled.observe(this, new EventObserver<>(hasEnabled -> {
            if (hasEnabled) {
                mLoadingDialog.startAlertDialog();
            } else {
                mLoadingDialog.dismissDialog();
            }
        }));

//        mSignUpViewModel.loading.observe(this, isLoading -> {
//            if (isLoading) {
//                mLoadingDialog.startAlertDialog(requireContext());
//            } else
//                mLoadingDialog.dismissDialog();
//        });

        mNestedScrollView.setOnTouchListener((v, motionEvent) -> {
            if (motionEvent != null && motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                if (getContext() != null)
                    mSoftKeyboardUtil.hideSoftKeyboardForScrollViews(getContext(), v);
            }
            return false;
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


    @Override
    public void onSuccess(String message) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show();
        mTextInputLayout.setPasswordVisibilityToggleEnabled(false);
    }

    @Override
    public void onError(String message) {
        mName.setError(message);
        mEmail.setError(message);
        mPassword.setError(message);
        mTextInputLayout.setPasswordVisibilityToggleEnabled(false);
    }
}