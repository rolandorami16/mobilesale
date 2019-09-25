package com.example.mobilesale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMapsLimaActivity extends AppCompatActivity implements OnMapReadyCallback {

    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps_lima);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapLima);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setTrafficEnabled(true);

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(-12.049383, -77.033689))
                .title("Tienda Lima Jirón de la Unión 795")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(-12.058241,  -77.047671))
                .title("Tienda Breña Av. Arica 598"));

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(-12.081723, -77.035889))
                .title("Tienda Lince Av. Arenales 1504"));

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-12.062273, -77.035889), 14));

    }
}
