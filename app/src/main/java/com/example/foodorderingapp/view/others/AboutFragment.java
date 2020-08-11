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

public class AboutFragment extends Fragment {
    View v;
    Toolbar mToolbar;
    TextView mLink1, mLink2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_about, container, false);
        mToolbar = v.findViewById(R.id.toolbar_about);
        mLink1 =  v.findViewById(R.id.contact_author_detail1);
        mLink2 =  v.findViewById(R.id.contact_author_detail2);
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

        mLink1.setMovementMethod(LinkMovementMethod.getInstance());
        mLink2.setMovementMethod(LinkMovementMethod.getInstance());
    }
}