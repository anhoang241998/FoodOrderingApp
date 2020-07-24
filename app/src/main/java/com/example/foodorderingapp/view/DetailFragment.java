package com.example.foodorderingapp.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.palette.graphics.Palette;

import com.example.foodorderingapp.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import butterknife.ButterKnife;

public class DetailFragment extends Fragment {

    private Toolbar toolbar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private View view;
    //    private SimpleSearchView searchView;
    private int mFoodUuid;
//    private Bitmap mBitmap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        mCollapsingToolbarLayout = view.findViewById(R.id.collapsingToolbar);
//        searchView = view.findViewById(R.id.searchView);
        ButterKnife.bind(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        if (getArguments() != null) {
//            mFoodUuid = DetailFragmentArgs.fromBundle(getArguments()).getFoodUuid();
//        }

        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            toolbar.setTitleTextColor(255);
            setHasOptionsMenu(true);
        }

        toolbar.setNavigationOnClickListener(v -> {
            getActivity().onBackPressed();
        });

        // for creating the palette in collapse toolbar
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.recommended1);
//        Palette.from(mBitmap).generate(palette -> {
//            if (palette != null) {
//                mCollapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(R.attr.colorPrimary));
//            }
//        });

    }

//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        inflater.inflate(R.menu.toolbar_search_menu, menu);
//       setupSearchView(menu);
//        super.onCreateOptionsMenu(menu, inflater);
//
//    }

//    private void setupSearchView(Menu menu) {
//        MenuItem item = menu.findItem(R.id.action_search);
//        searchView.setMenuItem(item);
//        Point revealCenter = searchView.getRevealAnimationCenter();
//        revealCenter.x -= DimensUtils.convertDpToPx(EXTRA_REVEAL_CENTER_PADDING, getContext());
//    }


}