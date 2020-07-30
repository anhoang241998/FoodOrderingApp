package com.example.foodorderingapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.foodorderingapp.R;

public class LogInFragment extends Fragment {

    Toolbar mToolbar;
    View view;
    Button btnSignIn;
    TextView btnSignUp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_log_in, container, false);
        mToolbar = view.findViewById(R.id.toolbar_login);
        btnSignIn = view.findViewById(R.id.btn_signIn);
        btnSignUp = view.findViewById(R.id.tv_sign_up_signInScreen);
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

        btnSignIn.setOnClickListener(v -> {
            NavDirections actionSignIn = LogInFragmentDirections.actionList();
            Navigation.findNavController(v).navigate(actionSignIn);
        });

        btnSignUp.setOnClickListener(v ->{
            NavDirections actionSignUp = LogInFragmentDirections.actionSignUp();
            Navigation.findNavController(v).navigate(actionSignUp);
        });
    }


}