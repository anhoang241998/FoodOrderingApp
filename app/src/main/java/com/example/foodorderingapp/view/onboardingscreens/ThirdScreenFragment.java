package com.example.foodorderingapp.view.onboardingscreens;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.view.ViewPagerFragment;
import com.example.foodorderingapp.view.ViewPagerFragmentDirections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThirdScreenFragment extends Fragment {
    @BindView(R.id.finish)
    TextView tv_finish;
    private View view;

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_third_screen, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_finish.setOnClickListener(v -> {
            NavDirections action = ViewPagerFragmentDirections.actionViewPagerList();
            Navigation.findNavController(v).navigate(action);
            onBoardingFinished();
        });
    }

    private void onBoardingFinished() {
        sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        editor.putBoolean("Finished", true);
        editor.apply();
    }
}