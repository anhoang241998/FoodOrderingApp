package com.example.foodorderingapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.adapters.AllMenuAdapter;
import com.example.foodorderingapp.adapters.PopularAdapter;
import com.example.foodorderingapp.adapters.RecommendedAdapter;
import com.example.foodorderingapp.viewmodel.ListViewModel;

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
    @BindView(R.id.loadingView)
    ProgressBar loadingView;
    @BindView(R.id.btn_details)
    Button btnDetails;

    // Function
    private View view;
    private ListViewModel mListViewModel;
    private PopularAdapter mPopularAdapter;
    private RecommendedAdapter mRecommendedAdapter;
    private AllMenuAdapter mAllMenuAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListViewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        mListViewModel.refresh();

        btnDetails.setOnClickListener(v -> {
            ListFragmentDirections.ActionDetail action = ListFragmentDirections.actionDetail();
            Navigation.findNavController(v).navigate(action);
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
    }

    private void observeViewModel() {

        mListViewModel.allMenu.observe(this, allMenus -> {
            if (allMenus != null && allMenus instanceof List) {
                allMenuRecycler.setVisibility(View.VISIBLE);
                mAllMenuAdapter.updateMenu(allMenus);
            }
        });

        mListViewModel.recommendations.observe(this, recommended -> {
            if (recommended != null && recommended instanceof List){
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

        mListViewModel.loading.observe(this, isLoading -> {
            if (isLoading != null && isLoading instanceof Boolean) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    listError.setVisibility(View.GONE);
                    popularRecycler.setVisibility(View.GONE);
                }
            }
        });
    }
}