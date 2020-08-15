package com.example.foodorderingapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.adapters.AllMenuAdapter;
import com.example.foodorderingapp.adapters.PopularAdapter;
import com.example.foodorderingapp.adapters.RecommendedAdapter;
import com.example.foodorderingapp.util.EventObserver;
import com.example.foodorderingapp.util.LoadingDialog;
import com.example.foodorderingapp.viewmodel.ListViewModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFragment extends Fragment {

    // UI
    @BindView(R.id.popular_recycler)
    RecyclerView popularRecycler;
    @BindView(R.id.recommended_recycler)
    RecyclerView recommendedRecycler;
    @BindView(R.id.all_menu_recycler)
    RecyclerView allMenuRecycler;
    @BindView(R.id.listError)
    TextView listError;
    @BindView(R.id.btn_search)
    ImageView mSearch;

    Toolbar mToolbar;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    private static final String TAG = "BBB";

    // Function
    private View view;
    private ListViewModel mListViewModel;
    private PopularAdapter mPopularAdapter;
    private RecommendedAdapter mRecommendedAdapter;
    private AllMenuAdapter mAllMenuAdapter;
    private LoadingDialog mLoadingDialog;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list, container, false);
        mToolbar = view.findViewById(R.id.toolbarList);
        mDrawerLayout = view.findViewById(R.id.drawerLayout);
        mNavigationView = view.findViewById(R.id.navigation);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFirebaseAuth = FirebaseAuth.getInstance();


        mListViewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        mLoadingDialog = new LoadingDialog(getActivity());
        mListViewModel.refresh();

        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
            setHasOptionsMenu(true);
        }

        mToolbar.setNavigationOnClickListener(v -> {
            mDrawerLayout.openDrawer(GravityCompat.START);
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavController navController = Navigation.findNavController(view);
        NavigationUI.setupWithNavController(mNavigationView, navController);


        mSearch.setOnClickListener(v -> {
            NavDirections actionSearch = ListFragmentDirections.actionSearch();
            Navigation.findNavController(v).navigate(actionSearch);
        });

        mPopularAdapter = new PopularAdapter(new ArrayList<>());
        popularRecycler.setHasFixedSize(true);
        popularRecycler.setItemViewCacheSize(20);
        popularRecycler.setAdapter(mPopularAdapter);

        mRecommendedAdapter = new RecommendedAdapter(new ArrayList<>());
        recommendedRecycler.setHasFixedSize(true);
        recommendedRecycler.setItemViewCacheSize(20);
        recommendedRecycler.setAdapter(mRecommendedAdapter);

        mAllMenuAdapter = new AllMenuAdapter(new ArrayList<>());
        allMenuRecycler.setHasFixedSize(true);
        allMenuRecycler.setItemViewCacheSize(20);
        allMenuRecycler.setAdapter(mAllMenuAdapter);

        observeViewModel();

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                }

                if (getActivity() != null)
                    getActivity().finish();

            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }


    private void observeViewModel() {

        mListViewModel.allMenu.observe(this, allMenus -> {
            if (allMenus != null && allMenus instanceof List) {
                allMenuRecycler.setVisibility(View.VISIBLE);
                mAllMenuAdapter.updateMenu(allMenus);
            }
        });

        mListViewModel.recommendations.observe(this, recommended -> {
            if (recommended != null && recommended instanceof List) {
                recommendedRecycler.setVisibility(View.VISIBLE);
                mRecommendedAdapter.updateRecommendedList(recommended);
            }
        });

        mListViewModel.foods.observe(this, foods -> {
            if (foods != null && foods instanceof List) {
                popularRecycler.setVisibility(View.VISIBLE);
                mPopularAdapter.updatePopularList(foods);
            }
        });

        mListViewModel.foodLoadError.observe(this, isError -> {
            if (isError != null && isError instanceof Boolean) {
                listError.setVisibility(isError ? View.VISIBLE : View.GONE);
            }
        });

        mListViewModel.isProgressEnabled.observe(this, new EventObserver<>(hasEnabled ->{
            if (hasEnabled) {
                mLoadingDialog.startAlertDialog();
            } else {
                mLoadingDialog.dismissDialog();
            }
        }));

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_search_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_cart:
                NavDirections actionCart = ListFragmentDirections.actionAddToCart();
                Navigation.findNavController(view).navigate(actionCart);
            default: return super.onOptionsItemSelected(item);
        }
    }
}