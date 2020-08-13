package com.example.foodorderingapp.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.example.foodorderingapp.R;

public class LoadingDialog {

    Activity mActivity;
    AlertDialog mDialog;

    public LoadingDialog(Activity activity) {
        mActivity = activity;
    }

    public void startAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        LayoutInflater inflater = mActivity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog, null));
        builder.setCancelable(false);

        mDialog = builder.create();
        mDialog.show();
    }

    public void dismissDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

}
