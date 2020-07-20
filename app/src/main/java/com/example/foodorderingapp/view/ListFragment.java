package com.example.foodorderingapp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.adapter.PopularAdapter;
import com.example.foodorderingapp.models.FoodData;
import com.example.foodorderingapp.models.Popular;
import com.example.foodorderingapp.viewmodel.ListViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFragment extends Fragment {

    //
    @BindView(R.id.btn_cart)
    ImageView btnCart;
    @BindView(R.id.popular_recycler)
    RecyclerView popularRecycler;
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
        popularRecycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        popularRecycler.setHasFixedSize(true);
        popularRecycler.setItemViewCacheSize(20);
        popularRecycler.setAdapter(mPopularAdapter);

        observeViewModel();
    }

    private void observeViewModel() {

        mListViewModel.foods.observe(this, foods ->{
            if (foods != null && foods instanceof List){
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