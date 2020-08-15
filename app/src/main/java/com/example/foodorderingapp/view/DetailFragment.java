package com.example.foodorderingapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.models.Allmenu;
import com.example.foodorderingapp.models.Popular;
import com.example.foodorderingapp.models.Recommended;
import com.example.foodorderingapp.util.GlideUtil;
import com.example.foodorderingapp.viewmodel.DetailViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class DetailFragment extends Fragment {

    private Toolbar toolbar;
    private ImageView mImageView;
    private TextView mFoodName, mRating, mFoodPrice;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private View view;
    private FloatingActionButton mAddToCart;
    private Snackbar mSnackBar;
    //    private SimpleSearchView searchView;
    private int mPopularUuid, mRecommendUuid, mAllMenuUuid;
    private DetailViewModel mDetailViewModel;
    //    private Bitmap mBitmap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        mCollapsingToolbarLayout = view.findViewById(R.id.collapsingToolbar);
        mImageView = view.findViewById(R.id.foodImage);
        mFoodName = view.findViewById(R.id.name);
        mRating = view.findViewById(R.id.rating);
        mAddToCart = view.findViewById(R.id.button_addToCart_Detail);
        mFoodPrice = view.findViewById(R.id.price);
//        searchView = view.findViewById(R.id.searchView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            mPopularUuid = DetailFragmentArgs.fromBundle(getArguments()).getPopularUuid();
        }

        if (getArguments() != null) {
            mRecommendUuid = DetailFragmentArgs.fromBundle(getArguments()).getRecommendUuid();
        }

        if (getArguments() != null) {
            mAllMenuUuid = DetailFragmentArgs.fromBundle(getArguments()).getAllMenuUuid();
        }

        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            toolbar.setTitleTextColor(255);
            setHasOptionsMenu(true);
        }

        toolbar.setNavigationOnClickListener(v -> {
            getActivity().onBackPressed();
        });

        mDetailViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        mDetailViewModel.fetch(mPopularUuid);
        mDetailViewModel.fetchRecommend(mRecommendUuid);
        mDetailViewModel.fetchAllMenu(mAllMenuUuid);
        if (mPopularUuid != 0) {
            observePopularViewModel();
        } else if (mRecommendUuid != 0) {
            observeRecommendViewModel();
        } else if (mAllMenuUuid != 0) {
            observeAllmenuViewModel();
        }

        mAddToCart.setOnClickListener(v -> {
            mSnackBar = Snackbar.make(v, "Add to cart was successfully", BaseTransientBottomBar.LENGTH_LONG);
            mSnackBar.show();
        });

        // for creating the palette in collapse toolbar
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.recommended1);
//        Palette.from(mBitmap).generate(palette -> {
//            if (palette != null) {
//                mCollapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(R.attr.colorPrimary));
//            }
//        });

    }

    private void observePopularViewModel() {
        mDetailViewModel.popularLiveData.observe(this, popular -> {
            if (popular != null && popular instanceof Popular) {
                mFoodName.setText(popular.name);
                mFoodPrice.setText(popular.price);
                mRating.setText(popular.rating);
            }

            if (popular.imageUrl != null && getContext() != null) {
                GlideUtil.loadImages(mImageView, popular.imageUrl, new CircularProgressDrawable(getContext()));
            }
        });
    }

    private void observeRecommendViewModel() {
        mDetailViewModel.recommendedLiveData.observe(this, recommended -> {
            if (recommended != null && recommended instanceof Recommended) {
                mFoodName.setText(recommended.name);
                mFoodPrice.setText(recommended.price);
                mRating.setText(recommended.rating);
            }

            if (recommended.imageUrl != null && getContext() != null) {
                GlideUtil.loadImages(mImageView, recommended.imageUrl, new CircularProgressDrawable(getContext()));
            }
        });
    }

    private void observeAllmenuViewModel() {
        mDetailViewModel.allmenuLiveData.observe(this, allmenu -> {
            if (allmenu != null && allmenu instanceof Allmenu) {
                mFoodName.setText(allmenu.name);
                mFoodPrice.setText(allmenu.price);
                mRating.setText(allmenu.rating);
            }

            if (allmenu.imageUrl != null && getContext() != null) {
                GlideUtil.loadImages(mImageView, allmenu.imageUrl, new CircularProgressDrawable(getContext()));
            }
        });
    }
//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        inflater.inflate(R.menu.toolbar_search_menu, menu);
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