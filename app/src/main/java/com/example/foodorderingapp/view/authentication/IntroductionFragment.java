package com.example.foodorderingapp.view.authentication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.foodorderingapp.R;

public class IntroductionFragment extends Fragment {

    //UI
    Button mLoginButton;
    TextView mSignUp;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_introdution, container, false);
        mLoginButton = view.findViewById(R.id.btn_move_login);
        mSignUp = view.findViewById(R.id.tv_sign_up);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLoginButton.setOnClickListener(v -> {
            NavDirections actionLogin = IntroductionFragmentDirections.actionLogin();
            Navigation.findNavController(v).navigate(actionLogin);
        });

        mSignUp.setOnClickListener(v -> {
            NavDirections actionSignUp = IntroductionFragmentDirections.actionSignUp();
            Navigation.findNavController(v).navigate(actionSignUp);
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (getActivity() != null)
                    getActivity().finish();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }
}