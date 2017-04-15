package com.example.eoghan.drinkdin;

import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class phouseMap extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    double lat, lon;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private DatabaseReference retrieveReference;


     //int count = 1;

    String ratingStr;
    String ratingCounter;


    double countFl;
    double averageRating;
    double ratingTotalDBL;
    double ratingCounterDBL;

    float currRating;
    int ratingTotal;

    String newRatingTotal;
    String averageRatingStr;

    int count = 0;
    float addToRating;
    private RatingBar phRating;
    private Button btnAddToList;
    private Button btnSubmitRating;
    private TextView ratingTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phouse_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        phRating = (RatingBar) findViewById(R.id.phouseRating);
        phRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingStr = String.valueOf(rating);
            }
        });

        btnAddToList = (Button) findViewById(R.id.checkInBtn);
        btnAddToList.setOnClickListener(this);
        btnSubmitRating = (Button) findViewById(R.id.submitRatinBtn);
        btnSubmitRating.setOnClickListener(this);
        ratingTV = (TextView) findViewById(R.id.totalRatingTV);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        if (firebaseAuth.getCurrentUser() == null) {
            Toast.makeText(this, "Please sign in to access full features", Toast.LENGTH_LONG).show();

        }


        Query readRatingAvg = databaseReference.child("ratings").child("porterhouse");

        readRatingAvg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //get data from snapshot
                    if (dataSnapshot.child("Porterhouse").getValue() != "null") {
                        String avgRating = "Rating:" + dataSnapshot.child("averageRating").getValue();


                    ratingTV.setText(avgRating);

                }
                else{
                        ratingTV.setText("Rating: Error");

                    }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


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

        lat = 53.343908;
        lon = -6.267554;


        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lat, lon)));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(10));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(lat, lon))
                .title("Porterhouse"));


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

    @Override
    public void onClick(View v) {
        if (v == btnAddToList) {
            AddToList();
        }
        if (v == btnSubmitRating) {
            submitRating();
        }
    }

    private void submitRating() {

        if (ratingStr != null) {
            FirebaseUser user = firebaseAuth.getCurrentUser();

            if (user != null) {

                databaseReference.child("checkin").child(user.getUid()).child("Porterhouse").child("Rating").setValue(ratingStr);

                Query RetrieveRatingCount = databaseReference.child("ratings").child("porterhouse");
                RetrieveRatingCount.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot postSnapshot) {

                        //get data from snapshot
                        String data = postSnapshot.child("numRating").getValue().toString();

                        if (!data.equals(null)) {
                            String numRating = (String) postSnapshot.child("numRating").getValue();

                            count = Integer.valueOf(numRating);
                            count++;

                            ratingCounter = Integer.toString(count);


                            databaseReference.child("ratings").child("porterhouse").child("numRating").setValue(ratingCounter);

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                Query addRatingTotal = databaseReference.child("ratings").child("porterhouse");
                addRatingTotal.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot postSnapshot2) {

                        //get data from snapshot
                        String data2 = postSnapshot2.child("totalRating").getValue().toString();

                        if (!data2.equals(null)) {
                            String totRating = (String) postSnapshot2.child("totalRating").getValue();

                            addToRating = Float.valueOf(totRating);
                            currRating = Float.valueOf(ratingStr);
                            float ratingTotal = addToRating + currRating;

                            newRatingTotal = Float.toString(ratingTotal);

                            databaseReference.child("ratings").child("porterhouse").child("totalRating").setValue(newRatingTotal);


                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                Query calcAverage = databaseReference.child("ratings").child("porterhouse");
                calcAverage.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot postSnapshot3) {

                        ratingTotalDBL = Double.valueOf(newRatingTotal);
                        ratingCounterDBL = Double.valueOf(count);

                        double averageRating = ratingTotalDBL / ratingCounterDBL;

                        averageRatingStr = Double.toString(averageRating);

                        databaseReference.child("ratings").child("porterhouse").child("averageRating").setValue(averageRatingStr);

                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                Toast.makeText(this, "Rating Stored: " + ratingStr, Toast.LENGTH_LONG).show();


            } else

            {
                Toast.makeText(this, "Error rating not saved", Toast.LENGTH_LONG).show();

            }


        }else{
            Toast.makeText(this, "Please Enter value to submit", Toast.LENGTH_LONG).show();
        }

    }




    private void AddToList() {
        FirebaseUser user = firebaseAuth.getCurrentUser();


        if (user != null) {

            //String phouseCheckIn = "Porterhouse";

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date time = new Date();

            databaseReference.child("checkin").child(user.getUid()).child("Porterhouse").child(dateFormat.format(time)).setValue(true);
            //databaseReference.child("checkin").child(user.getUid()).child("Porterhouse").child("Rating").setValue(ratingStr);
            databaseReference.child("users").child(user.getUid()).child("lascheckin").setValue("Porterhouse");


//            UserInformation UserInformation = new UserInformation(phouseCheckIn);
//            databaseReference.child(user.getUid()).setValue(UserInformation);
            Toast.makeText(this, "Added to check ins", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();

        }
    }
}