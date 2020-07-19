package com.example.foodorderingapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.adapter.ViewPagerAdapter;
import com.example.foodorderingapp.view.onboardingscreens.FirstScreenFragment;
import com.example.foodorderingapp.view.onboardingscreens.SecondScreenFragment;
import com.example.foodorderingapp.view.onboardingscreens.ThirdScreenFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerFragment extends Fragment {

    @BindView(R.id.viewPager)
    ViewPager2 viewPager;
    private View view;
    private ArrayList<Fragment> fragmentList;
    private FirstScreenFragment firstScreen;
    private SecondScreenFragment secondScreen;
    private ThirdScreenFragment thirdScreen;
    private ViewPagerAdapter mViewPagerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_view_pager, container, false);

        firstScreen = new FirstScreenFragment();
        secondScreen = new SecondScreenFragment();
        thirdScreen = new ThirdScreenFragment();

        fragmentList = new ArrayList<>();
        fragmentList.add(firstScreen);
        fragmentList.add(secondScreen);
        fragmentList.add(thirdScreen);

        mViewPagerAdapter = new ViewPagerAdapter(
                requireActivity().getSupportFragmentManager(),
                getLifecycle(),
                fragmentList);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager.setAdapter(mViewPagerAdapter);
    }
}