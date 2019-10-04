package com.disrupt.paws.ui.explore;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.disrupt.paws.MainActivity;
import com.disrupt.paws.R;
import com.disrupt.paws.dependency_injection.AppComponent;
import com.disrupt.paws.dependency_injection.DaggerAppComponent;
import com.disrupt.paws.model.MapMarker;
import com.disrupt.paws.network.PawsApi;
import com.disrupt.paws.network.RetroInstance;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import dagger.android.AndroidInjector;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by ankit on 10/2/2019.
 */

public class ExploreOptionsFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    private ImageView vet, adoption, food;
    private MapView mapView;
    private GoogleMap googleMap;
    private ArrayList<MapMarker> mapMarkersList = null;
    ProgressDialog progressDialog;

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
                for (int i = 0; i < mapMarkersList.size(); i++) {
                    googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(mapMarkersList.get(i).getLatitude(), mapMarkersList.get(i).getLongitude())).title(mapMarkersList.get(i).getTitle())
                            .icon(BitmapDescriptorFactory.fromResource(mapMarkersList.get(i).getIconResId()))
                            .draggable(false).visible(true));
                }
            } else if (val == 2) {
                setFoodStubbedData();
            } else {
                setVetStubbedData();
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mapMarkersList.get(0).getLatitude(), mapMarkersList.get(0).getLongitude()), zoomLevel));
                for (int i = 0; i < mapMarkersList.size(); i++) {
                    googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(mapMarkersList.get(i).getLatitude(), mapMarkersList.get(i).getLongitude())).title(mapMarkersList.get(i).getTitle())
                            .icon(BitmapDescriptorFactory.fromResource(mapMarkersList.get(i).getIconResId()))
                            .draggable(false).visible(true));
                }
            }
            for (int i = 0; i < mapMarkersList.size(); i++) {
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(mapMarkersList.get(i).getLatitude(), mapMarkersList.get(i).getLongitude())).title(mapMarkersList.get(i).getTitle())
                        .icon(BitmapDescriptorFactory.fromResource(mapMarkersList.get(i).getIconResId()))
                        .draggable(false).visible(true));
            }
        }
    }

    public void setVetStubbedData() {
        MapMarker mapMarker = new MapMarker(37.762294, -122.488171, "Irving Pet Hospital", "Dog Adoption", R.drawable.ic_filter);
        mapMarker.setIconResId(R.drawable.doctor_male_50);
        MapMarker mapMarker1 = new MapMarker(37.762727, -122.490757, "SF Vet Hospital Mission", "Dog Adoption", R.drawable.ic_filter);
        mapMarker1.setIconResId(R.drawable.doctor_male_50);
        MapMarker mapMarker2 = new MapMarker(37.764483, -122.490800, "Fuzzy Pet Health", "Dog Adoption", R.drawable.ic_filter);
        mapMarker2.setIconResId(R.drawable.doctor_male_50);
        MapMarker mapMarker3 = new MapMarker(37.764252, -122.484820, "Sunset Pet Hospital", "Dog Adoption", R.drawable.ic_filter);
        mapMarker3.setIconResId(R.drawable.doctor_male_50);
        mapMarkersList.add(mapMarker);
        mapMarkersList.add(mapMarker1);
        mapMarkersList.add(mapMarker2);
        mapMarkersList.add(mapMarker3);
    }

    public void setFoodStubbedData() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.show();
        PawsApi service = RetroInstance.getRetrofitInstance().create(PawsApi.class);
        Call<MapMarker> call = service.getLocation(1);
        call.enqueue(new Callback<MapMarker>() {
            @Override
            public void onResponse(Call<MapMarker> call, Response<MapMarker> response) {
                progressDialog.dismiss();
                response.body();
                MapMarker mapMarker = new MapMarker(37.771812, -122.431927, "Mission Pet Supply", "Dog Adoption", R.drawable.ic_filter);
                mapMarker.setIconResId(R.drawable.dog_bowl_50);
                MapMarker mapMarker1 = new MapMarker(37.771888, -122.428944, "Petco", "Dog Adoption", R.drawable.ic_filter);
                mapMarker1.setIconResId(R.drawable.dog_bowl_50);
                MapMarker mapMarker2 = new MapMarker(37.773601, -122.429727, "Jeffrey's Natural Pet Foods", "Dog Adoption", R.drawable.ic_filter);
                mapMarker2.setIconResId(R.drawable.dog_bowl_50);
                mapMarkersList.add(mapMarker);
                mapMarkersList.add(mapMarker1);
                mapMarkersList.add(mapMarker2);
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mapMarkersList.get(0).getLatitude(), mapMarkersList.get(0).getLongitude()), 15f));
                for (int i = 0; i < mapMarkersList.size(); i++) {
                    googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(mapMarkersList.get(i).getLatitude(), mapMarkersList.get(i).getLongitude())).title(mapMarkersList.get(i).getTitle())
                            .icon(BitmapDescriptorFactory.fromResource(mapMarkersList.get(i).getIconResId()))
                            .draggable(false).visible(true));
                }
            }

            @Override
            public void onFailure(Call<MapMarker> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setAdoptionStubbedData() {
        MapMarker mapMarker = new MapMarker(37.743497, -122.447031, "SF Pet Adoption Center", "Dog Adoption", R.drawable.ic_filter);
        mapMarker.setIconResId(R.drawable.dog_paw_50);
        MapMarker mapMarker1 = new MapMarker(37.744006, -122.451612, "Mission Bay Adoption Center", "Dog Adoption", R.drawable.ic_filter);
        mapMarker1.setIconResId(R.drawable.dog_paw_50);
        MapMarker mapMarker2 = new MapMarker(37.742055, -122.450518, "Petro Adoption Center", "Dog Adoption", R.drawable.ic_filter);
        mapMarker2.setIconResId(R.drawable.dog_paw_50);
        mapMarkersList.add(mapMarker);
        mapMarkersList.add(mapMarker1);
        mapMarkersList.add(mapMarker2);
    }
}
