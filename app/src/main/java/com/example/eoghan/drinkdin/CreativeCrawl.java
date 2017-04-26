package com.example.eoghan.drinkdin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class CreativeCrawl extends AppCompatActivity implements OnMapReadyCallback {


    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creative_crawl);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflate = getMenuInflater();

        inflate.inflate(R.menu.home, menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.homeIcon:
                Intent i = new Intent(CreativeCrawl.this, MainActivity.class);
                startActivity(i);
                return true;

            case R.id.userIcon:
                Intent j = new Intent(CreativeCrawl.this, LoginActivity.class);
                startActivity(j);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        PolylineOptions line = new PolylineOptions().add(new LatLng(53.3416705,-6.2626347),
                new LatLng(53.342174,-6.2629205),
                new LatLng(53.3426618,-6.2645171)).width(5).color(Color.RED);
        mMap.addPolyline(line);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(53.342174,-6.2629205)));

        mMap.moveCamera(CameraUpdateFactory.zoomTo(16));
        mMap.addMarker(new MarkerOptions().position(new LatLng(53.3416705,-6.2626347)).title("South William Bar"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(53.3426618,-6.2645171)).title("Dakota"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(53.342174,-6.2629205)).title("Pygmalion"));

    }
}

