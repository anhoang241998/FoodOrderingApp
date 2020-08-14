package com.example.foodorderingapp.view.onboardingscreens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.foodorderingapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondScreenFragment extends Fragment {
    @BindView(R.id.next2)
    Button btn_next;
    private View view;

    private ViewPager2 mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_second_screen, container, false);
        if (getActivity() != null)
            mViewPager = getActivity().findViewById(R.id.viewPager);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_next.setOnClickListener(v -> {
            mViewPager.setCurrentItem(2);
        });
    }
}