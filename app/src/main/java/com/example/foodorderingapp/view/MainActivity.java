package com.example.foodorderingapp.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodorderingapp.R;

public class MainActivity extends AppCompatActivity {

    //    private NavController mNavController;
//    MainViewModel mMainViewModel;
//    public ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mNavController = Navigation.findNavController(this, R.id.fragment);
//        NavigationUI.setupActionBarWithNavController(this, mNavController);
//        mProgressBar = findViewById(R.id.progress_login);
//        mMainViewModel = new MainViewModel();
//        mMainViewModel.getLoading().observe(this, new Observer<Boolean>() {
//            @Override
//            public void onChanged(Boolean aBoolean) {
//                if (aBoolean) {
//                    mProgressBar.setVisibility(View.VISIBLE);
//                } else {
//                    mProgressBar.setVisibility(View.GONE);
//                }
//            }
//        });
//    @Override
//    public boolean onSupportNavigateUp() {
//        return NavigationUI.navigateUp(mNavController, (DrawerLayout) null);
//    }
    }

}