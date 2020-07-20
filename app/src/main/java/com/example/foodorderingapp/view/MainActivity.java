package com.example.foodorderingapp.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.foodorderingapp.R;

public class MainActivity extends AppCompatActivity {

    //    private NavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mNavController = Navigation.findNavController(this, R.id.fragment);
//        NavigationUI.setupActionBarWithNavController(this, mNavController);
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        return NavigationUI.navigateUp(mNavController, (DrawerLayout) null);
//    }
}