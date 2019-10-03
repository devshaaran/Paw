package com.disrupt.paws.ui.social;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.disrupt.paws.R;

import dagger.android.support.DaggerFragment;

public class SocialFragment extends DaggerFragment {

    private SocialViewModel socialViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        socialViewModel =
                ViewModelProviders.of(this).get(SocialViewModel.class);
        View root = inflater.inflate(R.layout.fragment_social, container, false);
        final TextView textView = root.findViewById(R.id.text_social);
        socialViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}