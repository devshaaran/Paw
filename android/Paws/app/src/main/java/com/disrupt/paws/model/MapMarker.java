package com.disrupt.paws.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ankit on 10/2/2019.
 */

public class MapMarker implements Parcelable {
    private double latitude;
    private double longitude;
    private String title;
    private String snippet;
    private int iconResId;

    public MapMarker(double latitude, double longitude, String title, String snippet, int iconResId) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.snippet = snippet;
        this.iconResId = iconResId;
    }

    public MapMarker(Parcel in){
        latitude=in.readDouble();
        longitude=in.readDouble();
        title=in.readString();
        snippet=in.readString();
        iconResId=in.readInt();
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    public static final Creator<MapMarker> CREATOR = new Creator<MapMarker>() {
        @Override
        public MapMarker createFromParcel(Parcel parcel) {
            return new MapMarker(parcel);
        }

        @Override
        public MapMarker[] newArray(int i) {
            return new MapMarker[0];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
        parcel.writeString(title);
        parcel.writeString(snippet);
        parcel.writeInt(iconResId);
    }
}
