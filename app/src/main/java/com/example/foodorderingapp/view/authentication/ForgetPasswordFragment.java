package com.example.foodorderingapp.view.authentication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.databinding.FragmentForgetPasswordBinding;
import com.example.foodorderingapp.util.SoftKeyboardUtil;
import com.example.foodorderingapp.viewmodel.authentication.ForgotPasswordCallbacks;
import com.example.foodorderingapp.viewmodel.authentication.ForgotPasswordViewModel;
import com.example.foodorderingapp.viewmodel.authentication.ForgotPasswordViewModelFactory;

public class ForgetPasswordFragment extends Fragment implements ForgotPasswordCallbacks {
    View view;
    Toolbar mToolbar;
    EditText mEmail;
    ForgotPasswordViewModel mForgotPasswordViewModel;
    FragmentForgetPasswordBinding mFragmentForgetPasswordBinding;
    NestedScrollView mNestedScrollView;
    private SoftKeyboardUtil softKeyboardUtil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentForgetPasswordBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_forget_password, container, false);
        view = mFragmentForgetPasswordBinding.getRoot();
        mEmail = view.findViewById(R.id.edt_email_forgot_password);
        mToolbar = view.findViewById(R.id.toolbar_forgot_password);
        mNestedScrollView = view.findViewById(R.id.nestedScrollView_forgot_password);

        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFragmentForgetPasswordBinding.setViewModel(ViewModelProviders.of(this, new ForgotPasswordViewModelFactory(this)).get(ForgotPasswordViewModel.class));
        softKeyboardUtil = new SoftKeyboardUtil(getActivity());

        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
            setHasOptionsMenu(true);
        }

        mToolbar.setNavigationOnClickListener(v -> {
            getActivity().onBackPressed();
        });

//        mButtonNext.setOnClickListener(v ->{
//            NavDirections actionNewPassword = ForgetPasswordFragmentDirections.actionNewPassword();
//            Navigation.findNavController(v).navigate(actionNewPassword);
//        });

        mNestedScrollView.setOnTouchListener((v, motionEvent) -> {
            if (motionEvent != null && motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                if (getContext() != null)
                    softKeyboardUtil.hideSoftKeyboardForScrollViews(getContext(), v);
            }
            return false;
        });

    }

    @Override
    public void onSuccess(String message) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String message) {
        mEmail.setError(message);
    }
}