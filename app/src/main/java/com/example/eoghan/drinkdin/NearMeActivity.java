package com.example.eoghan.drinkdin;

import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class NearMeActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double lat, lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_me);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        getSupportActionBar().setTitle("Pubs Near Me");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        //set camera settings, title and marker at lat and lon
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(53.3439254, -6.2715848)));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(53.3439254, -6.2715848))
                .title("Arthurs"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(53.3425732, -6.2646112))
                .title("Alfies"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(53.3439254,-6.2715848))
                .title("BeerMarket"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(53.3463936,-6.27018))
                .title("The Black Sheep"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(53.3449358,-6.2785263))
                .title("Brazen Head"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(53.3430433,-6.2609832))
                .title("The Dingle Bar"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(53.3422444,-6.2648161))
                .title("Grogans"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(53.343908,-6.267554))
                .title("Porterhouse"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(53.3438267,-6.2658528))
                .title("Stags Head"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(53.346958,-6.2583168))
                .title("Sweetmans"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(53.345474,-6.2664502))
                .title("The Temple Bar"));


        // Enabling MyLocation Layer of Google Map
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);

        // Getting LocationManager object from System Service LOCATION_SERVICE
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Creating a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Getting the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);

        // Getting Current Location
        Location location = locationManager.getLastKnownLocation(provider);

        if (location != null) {
            // Getting latitude of the current location
            double latitude = location.getLatitude();

            // Getting longitude of the current location
            double longitude = location.getLongitude();

            // Creating a LatLng object for the current location
            LatLng latLng = new LatLng(latitude, longitude);

            LatLng myPosition = new LatLng(latitude, longitude);


        }
    }
}
