package com.example.foodorderingapp.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodorderingapp.R;

public class SplashFragment extends Fragment {
    private View view;
    private SharedPreferences sharedPref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_splash, container, false);
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (onBoardingFinished()) {
                NavDirections action = SplashFragmentDirections.actionList();
                Navigation.findNavController(view).navigate(action);
            } else {
                NavDirections action = SplashFragmentDirections.actionSplashFragmentToViewPagerFragment();
                Navigation.findNavController(view).navigate(action);
            }
        }, 3000);

        return view;
    }

    private boolean onBoardingFinished() {
        sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE);
        return sharedPref.getBoolean("Finished", false);
    }
}