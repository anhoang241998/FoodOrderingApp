package com.example.foodorderingapp.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class SoftKeyboardUtil {

    Activity mActivity;
    InputMethodManager inputManager;
    boolean isKeyboardUp;

    public SoftKeyboardUtil(Activity activity) {
        mActivity = activity;
    }

    public void showSoftKeyboard(Context context, View view) {
        if (view != null) {
            inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(view, 0);
        }
    }

    public void hideSoftKeyboard(Context context, View view) {
        if (view != null) {
            inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void hideSoftKeyboardForScrollViews(Context context, View view) {
        inputManager = ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE));
        isKeyboardUp= inputManager.isAcceptingText();

        if (isKeyboardUp) inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
