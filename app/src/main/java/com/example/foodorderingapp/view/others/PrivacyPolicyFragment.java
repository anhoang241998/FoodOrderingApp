package com.example.foodorderingapp.view.others;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.foodorderingapp.R;

public class PrivacyPolicyFragment extends Fragment {

    View v;
    Toolbar mToolbar;
    TextView mGooglePlayServices, mFirebaseCrashlytics;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_privacy_policy, container, false);
        mToolbar = v.findViewById(R.id.toolbar_privacy);
        mGooglePlayServices = v.findViewById(R.id.information_link1);
        mFirebaseCrashlytics = v.findViewById(R.id.information_link2);
        return v;
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

        mGooglePlayServices.setMovementMethod(LinkMovementMethod.getInstance());
        mFirebaseCrashlytics.setMovementMethod(LinkMovementMethod.getInstance());
    }
}