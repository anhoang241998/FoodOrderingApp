package com.example.foodorderingapp.view.authentication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.databinding.FragmentLogInBinding;
import com.example.foodorderingapp.util.LoadingDialog;
import com.example.foodorderingapp.util.SoftKeyboardUtil;
import com.example.foodorderingapp.viewmodel.LogInResultCallbacks;
import com.example.foodorderingapp.viewmodel.LogInViewModel;
import com.example.foodorderingapp.viewmodel.LogInViewModelFactory;
import com.google.android.material.textfield.TextInputLayout;

public class LogInFragment extends Fragment implements LogInResultCallbacks {

    Toolbar mToolbar;
    View view;
    TextView btnSignUp, btnForgetPassword;
    EditText mEditTextEmail, mEditTextPassword;
    TextInputLayout mTextInputLayoutLogIn;
    LogInViewModel mLogInViewModel;
    FragmentLogInBinding mFragmentLogInBinding;
    private LoadingDialog loadingDialog;
    private SoftKeyboardUtil softKeyboardUtil;
    NestedScrollView mNestedScrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentLogInBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_log_in, container, false);
        view = mFragmentLogInBinding.getRoot();
        mToolbar = view.findViewById(R.id.toolbar_login);
        btnSignUp = view.findViewById(R.id.tv_sign_up_signInScreen);
        btnForgetPassword = view.findViewById(R.id.tv_forget_pass);
        mEditTextEmail = view.findViewById(R.id.edt_email_login);
        mEditTextPassword = view.findViewById(R.id.edt_password_login);
        mTextInputLayoutLogIn = view.findViewById(R.id.layout_text_input_login);
        mNestedScrollView = view.findViewById(R.id.nestedScrollView_logIn);
        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentLogInBinding.setViewModel(ViewModelProviders.of(this, new LogInViewModelFactory(this)).get(LogInViewModel.class));
        mLogInViewModel = ViewModelProviders.of(this).get(LogInViewModel.class);
        loadingDialog = new LoadingDialog(getActivity());
        softKeyboardUtil = new SoftKeyboardUtil(getActivity());

        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
            setHasOptionsMenu(true);
        }

        mToolbar.setNavigationOnClickListener(v -> {
            getActivity().onBackPressed();
        });

        btnSignUp.setOnClickListener(v -> {
            NavDirections actionSignUp = LogInFragmentDirections.actionSignUp();
            Navigation.findNavController(v).navigate(actionSignUp);
        });

        btnForgetPassword.setOnClickListener(v -> {
            NavDirections actionForget = LogInFragmentDirections.actionForget();
            Navigation.findNavController(v).navigate(actionForget);
        });

        observeViewModel();

        mNestedScrollView.setOnTouchListener((v, motionEvent) -> {
            if (motionEvent != null && motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                if (getContext() != null)
                    softKeyboardUtil.hideSoftKeyboardForScrollViews(getContext(), v);
            }
            return false;
        });
    }

    @Override
    public void onSuccess(String message) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String message) {
        mEditTextEmail.setError(message);
        mEditTextPassword.setError(message);
        mTextInputLayoutLogIn.setPasswordVisibilityToggleEnabled(false);
    }

    private void observeViewModel() {
        mLogInViewModel.loading.observe(this, isLoading -> {
            if (isLoading != null && isLoading instanceof Boolean) {
                if (isLoading) loadingDialog.startAlertDialog();
                else loadingDialog.dismissDialog();
            }
        });
    }
}