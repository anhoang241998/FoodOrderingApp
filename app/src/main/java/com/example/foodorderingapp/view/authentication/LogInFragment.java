package com.example.foodorderingapp.view.authentication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.databinding.FragmentLogInBinding;
import com.example.foodorderingapp.viewmodel.LogInResultCallbacks;
import com.example.foodorderingapp.viewmodel.LogInViewModel;
import com.example.foodorderingapp.viewmodel.LogInViewModelFactory;

public class LogInFragment extends Fragment implements LogInResultCallbacks {

    Toolbar mToolbar;
    View view;
    TextView btnSignUp, btnForgetPassword;
    EditText mUsername, mPassword;
    FragmentLogInBinding mFragmentLogInBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentLogInBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_log_in, container, false);
        view = mFragmentLogInBinding.getRoot();
        mToolbar = view.findViewById(R.id.toolbar_login);
        btnSignUp = view.findViewById(R.id.tv_sign_up_signInScreen);
        btnForgetPassword = view.findViewById(R.id.tv_forget_pass);
        mUsername = view.findViewById(R.id.edt_email_login);
        mPassword = view.findViewById(R.id.edt_password_login);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFragmentLogInBinding.setViewModel(ViewModelProviders.of(this, new LogInViewModelFactory(this)).get(LogInViewModel.class));

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
    }

    @Override
    public void onSuccess(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}