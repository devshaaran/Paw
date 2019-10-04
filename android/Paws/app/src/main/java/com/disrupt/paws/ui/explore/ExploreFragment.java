package com.disrupt.paws.ui.explore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.disrupt.paws.R;
import com.disrupt.paws.model.MapMarker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


public class ExploreFragment extends Fragment implements View.OnClickListener, OnMapReadyCallback {

    //private ExploreViewModel exploreViewModel;
    private MapView mMapView;
    private ImageButton btnOptions;
    GoogleMap googleMap;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_explore, container, false);
        mMapView = (MapView) root.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        initMap();
        this.btnOptions = (ImageButton) root.findViewById(R.id.btn_options);
        btnOptions.setOnClickListener(this);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initMap() {
        mMapView.onResume();
        mMapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            MapsInitializer.initialize(getContext());
            this.googleMap = googleMap;
            this.googleMap.getUiSettings().setZoomGesturesEnabled(true);
            this.googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.736381, -122.423179), 7f));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_options:
                ExploreOptionsFragment bottomSheetFragment = new ExploreOptionsFragment(mMapView, googleMap);
                bottomSheetFragment.show(getFragmentManager(), bottomSheetFragment.getTag());
                break;
        }
    }
}
