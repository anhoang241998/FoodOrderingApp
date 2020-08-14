package com.example.foodorderingapp.view.authentication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.databinding.FragmentConfirmationEmailBinding;
import com.example.foodorderingapp.viewmodel.authentication.ConfirmationEmailCallbacks;
import com.example.foodorderingapp.viewmodel.authentication.ConfirmationEmailViewModel;
import com.example.foodorderingapp.viewmodel.authentication.ConfirmationEmailViewModelFactory;

public class ConfirmationEmailFragment extends Fragment implements ConfirmationEmailCallbacks
{

    View view;
    Toolbar mToolbar;
    FragmentConfirmationEmailBinding mFragmentConfirmationEmailBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentConfirmationEmailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_confirmation_email, container,false);
        view = mFragmentConfirmationEmailBinding.getRoot();
        mToolbar = view.findViewById(R.id.toolbar_confirm_sign_up_updated);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFragmentConfirmationEmailBinding.setViewModel(ViewModelProviders.of(
                this, new ConfirmationEmailViewModelFactory(this))
                .get(ConfirmationEmailViewModel.class));


        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
            setHasOptionsMenu(true);
        }

        mToolbar.setNavigationOnClickListener(v -> {
            getActivity().onBackPressed();
        });
    }


    @Override
    public void onSuccess(String message) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show();
    }
}