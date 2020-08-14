package com.example.foodorderingapp.view.authentication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.foodorderingapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SuccessConfirmEmailFragment extends Fragment {

    View mView;
    Toolbar mToolbar;
    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;
    Button mLogin, mResendVerify;
    TextView mFailTitle, mFailMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_success_confirm_email, container, false);
        mToolbar = mView.findViewById(R.id.toolbar_success);
        mLogin = mView.findViewById(R.id.btn_List_fragment);
        mResendVerify = mView.findViewById(R.id.btn_resend);
        mFailTitle = mView.findViewById(R.id.tv_not_success);
        mFailMessage = mView.findViewById(R.id.tv_sorry);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        mLogin.setOnClickListener(v -> {
            if (!mFirebaseUser.isEmailVerified()) {
                NavDirections actionReload = SuccessConfirmEmailFragmentDirections.actionSuccessConfirmEmailFragmentToLogInFragment();
                Navigation.findNavController(v).navigate(actionReload);
            }
        });

        mResendVerify.setOnClickListener(v -> {
            mFirebaseUser.sendEmailVerification().addOnCompleteListener(task -> {
                if (task.isSuccessful()) Toast.makeText(requireActivity(), "Verification email had been sent", Toast.LENGTH_SHORT).show();
                else Toast.makeText(requireActivity(), "Email cannot send. Please try again", Toast.LENGTH_SHORT).show();
            });
        });

        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
            setHasOptionsMenu(true);
        }

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (getActivity() != null)
                    getActivity().finish();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }
}