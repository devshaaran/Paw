package com.disrupt.paws.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.disrupt.paws.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private ProfileViewModel profileViewModel;
    private Button button;
    ImageView imgView;
    int i=1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        imgView = root.findViewById(R.id.img_user);
        button = root.findViewById(R.id.btn_profile);
        button.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_profile:
                if(i%2==0) {
                    int id = getResources().getIdentifier("userprofile", "drawable", getContext().getPackageName());
                    imgView.setImageResource(id);
                }
                else{
                    int id = getResources().getIdentifier("dogprofile", "drawable", getContext().getPackageName());
                    imgView.setImageResource(id);
                }
                i++;
                break;

        }
    }
}