package com.example.foodorderingapp.view.authentication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.foodorderingapp.R;

public class ForgetPasswordFragment extends Fragment {
    View view;
    Toolbar mToolbar;
    Button mButtonNext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_forget_password, container, false);
        mToolbar = view.findViewById(R.id.toolbar_forgot_password);
        mButtonNext = view.findViewById(R.id.btn_next);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
            setHasOptionsMenu(true);
        }

        mToolbar.setNavigationOnClickListener(v ->{
            getActivity().onBackPressed();
        });

        mButtonNext.setOnClickListener(v ->{
            NavDirections actionNewPassword = ForgetPasswordFragmentDirections.actionNewPassword();
            Navigation.findNavController(v).navigate(actionNewPassword);
        });

    }
}