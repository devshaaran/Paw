package com.disrupt.paws.ui.explore;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


public class ExploreFragment extends Fragment implements View.OnClickListener {

    private ExploreViewModel exploreViewModel;
    private MapView mMapView;
    private ImageButton btnOptions;
    private ArrayList<MapMarker> mapMarkersList = null;
    private GoogleMap googleMap;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        exploreViewModel =
                ViewModelProviders.of(this).get(ExploreViewModel.class);

        View root = inflater.inflate(R.layout.fragment_explore, container, false);
        mMapView = (MapView) root.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        initMap();
        this.btnOptions = (ImageButton) root.findViewById(R.id.btn_options);
        btnOptions.setOnClickListener(this);
        parseMarkerData();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initMap() {
        mMapView.onResume();
        try {
            MapsInitializer.initialize(getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_options:
                ExploreOptionsFragment bottomSheetFragment = new ExploreOptionsFragment();
                bottomSheetFragment.show(getFragmentManager(), bottomSheetFragment.getTag());
                break;
        }
    }

    private void parseMarkerData() {
        googleMap = mMapView.getMap();
        if (googleMap != null) {
            float zoomLevel = 3.0f;
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.773972, -122.419418), zoomLevel));
            mapMarkersList = new ArrayList<MapMarker>();
            setStubbedData(); //TODO: remove this and make a rest api call
            for (int i = 0; i < mapMarkersList.size(); i++) {
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(mapMarkersList.get(i).getLatitude(), mapMarkersList.get(i).getLongitude())).title(mapMarkersList.get(i).getTitle())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
                        .draggable(false).visible(true));
            }
        }
    }

    public void setStubbedData() {
        MapMarker mapMarker = new MapMarker(37.774929, -122.419418, "Mission Bay", "Dog Adoption", R.drawable.ic_filter);
        MapMarker mapMarker1 = new MapMarker(32.774929, -122.419418, "Mission Bay", "Dog Adoption", R.drawable.ic_filter);
        mapMarkersList.add(mapMarker);
        mapMarkersList.add(mapMarker1);
    }
}
