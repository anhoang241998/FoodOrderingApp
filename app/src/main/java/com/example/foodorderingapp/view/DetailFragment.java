package com.example.foodorderingapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.foodorderingapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment {


    Toolbar toolbar;
    private View view;
    private int mFoodUuid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        ButterKnife.bind(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            mFoodUuid = DetailFragmentArgs.fromBundle(getArguments()).getFoodUuid();
        }

        if (getActivity() != null)
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(v -> {
            getActivity().onBackPressed();
        });

    }


}