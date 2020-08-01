package com.example.foodorderingapp.view.authentication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.foodorderingapp.R;
public class ConfirmPasswordUpdatedFragment extends Fragment {

    View view;
    Button mButtonLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_confirm_password_updated, container, false);
        mButtonLogin = view.findViewById(R.id.btn_log_in_new_password);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mButtonLogin.setOnClickListener(v -> {
            NavDirections actionLogin = ConfirmPasswordUpdatedFragmentDirections.actionLogin();
            Navigation.findNavController(v).navigate(actionLogin);
        });
    }
}