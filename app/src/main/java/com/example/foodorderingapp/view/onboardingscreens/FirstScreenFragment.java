package com.example.foodorderingapp.view.onboardingscreens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.foodorderingapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FirstScreenFragment extends Fragment {
    @BindView(R.id.next2)
    TextView tv_next;
    private View view;

    private ViewPager2 mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_first_screen, container, false);
        if (getActivity() != null)
            mViewPager = getActivity().findViewById(R.id.viewPager);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_next.setOnClickListener(v -> {
            mViewPager.setCurrentItem(1);
        });
    }
}