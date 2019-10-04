package com.disrupt.paws.ui.explore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.disrupt.paws.R;
import com.disrupt.paws.model.MapMarker;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


/**
 * Created by ankit on 10/2/2019.
 */

public class ExploreOptionsFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    private ImageView vet, adoption, food;
    private MapView mapView;
    private GoogleMap googleMap;
    private ArrayList<MapMarker> mapMarkersList = null;

    public ExploreOptionsFragment(MapView mapView, GoogleMap googleMap) { //Really bad of me!! todo
        this.mapView = mapView;
        this.googleMap = googleMap;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.explore_bottom_sheet, container, false);
        vet = root.findViewById(R.id.vet);
        adoption = root.findViewById(R.id.adoption);
        food = root.findViewById(R.id.food);
        vet.setOnClickListener(this);
        adoption.setOnClickListener(this);
        food.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.adoption:
                googleMap.clear();
                parseMarkerData(1);
                this.dismiss();
                break;

            case R.id.vet:
                googleMap.clear();
                parseMarkerData(0);
                this.dismiss();
                break;

            case R.id.food:
                googleMap.clear();
                parseMarkerData(2);
                this.dismiss();
                break;
        }
    }

    private void parseMarkerData(int val) {

        if (googleMap != null) {
            float zoomLevel = 15.0f;

            mapMarkersList = new ArrayList<MapMarker>();
            //TODO: remove this and make a rest api call
            if (val == 1) {
                setAdoptionStubbedData();
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mapMarkersList.get(0).getLatitude(), mapMarkersList.get(0).getLongitude()), zoomLevel));
            }
            else if (val == 2){
                setFoodStubbedData();
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mapMarkersList.get(0).getLatitude(), mapMarkersList.get(0).getLongitude()), zoomLevel));
            }
            else {
                setVetStubbedData();
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mapMarkersList.get(0).getLatitude(), mapMarkersList.get(0).getLongitude()), zoomLevel));
            }
            for (int i = 0; i < mapMarkersList.size(); i++) {
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(mapMarkersList.get(i).getLatitude(), mapMarkersList.get(i).getLongitude())).title(mapMarkersList.get(i).getTitle())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
                        .draggable(false).visible(true));
            }
        }
    }

    public void setVetStubbedData() {
        MapMarker mapMarker = new MapMarker(37.762294, -122.488171, "Irving Pet Hospital", "Dog Adoption", R.drawable.ic_filter);
        MapMarker mapMarker1 = new MapMarker(37.762727, -122.490757, "SF Vet Hospital Mission", "Dog Adoption", R.drawable.ic_filter);
        MapMarker mapMarker2 = new MapMarker(37.764483, -122.490800, "Fuzzy Pet Health", "Dog Adoption", R.drawable.ic_filter);
        MapMarker mapMarker3 = new MapMarker(37.764252, -122.484820,"Sunset Pet Hospital", "Dog Adoption", R.drawable.ic_filter);
        mapMarkersList.add(mapMarker);
        mapMarkersList.add(mapMarker1);
        mapMarkersList.add(mapMarker2);
        mapMarkersList.add(mapMarker3);
    }

    public void setFoodStubbedData() {
        MapMarker mapMarker = new MapMarker(37.771812, -122.431927,  "Mission Pet Supply", "Dog Adoption", R.drawable.ic_filter);
        MapMarker mapMarker1 = new MapMarker(37.771888, -122.428944,  "Petco", "Dog Adoption", R.drawable.ic_filter);
        MapMarker mapMarker2 = new MapMarker(37.773601, -122.429727, "Jeffrey's Natural Pet Foods", "Dog Adoption", R.drawable.ic_filter);
        mapMarkersList.add(mapMarker);
        mapMarkersList.add(mapMarker1);
        mapMarkersList.add(mapMarker2);
    }

    public void setAdoptionStubbedData() {
        MapMarker mapMarker = new MapMarker(37.743497, -122.447031, "SF Pet Adoption Center", "Dog Adoption", R.drawable.ic_filter);
        MapMarker mapMarker1 = new MapMarker(37.744006, -122.451612, "Mission Bay Adoption Center", "Dog Adoption", R.drawable.ic_filter);
        MapMarker mapMarker2 = new MapMarker(37.742055, -122.450518, "Petro Adoption Center", "Dog Adoption", R.drawable.ic_filter);
        mapMarkersList.add(mapMarker);
        mapMarkersList.add(mapMarker1);
        mapMarkersList.add(mapMarker2);
    }

}
