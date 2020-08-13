package com.example.foodorderingapp.view.authentication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.foodorderingapp.R;

public class NewPasswordFragment extends Fragment {
    View view;
    Toolbar mToolbar;
    Button mButtonConfirm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_new_password, container, false);
        mToolbar = view.findViewById(R.id.toolbar_new_password);
        mButtonConfirm = view.findViewById(R.id.btn_ok);
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

//        mButtonConfirm.setOnClickListener(v -> {
//            NavDirections actionConfirm = NewPasswordFragmentDirections.actionNewPasswordUpdated();
//            Navigation.findNavController(v).navigate(actionConfirm);
//        });
    }
}