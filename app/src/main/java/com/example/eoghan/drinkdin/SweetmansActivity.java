package com.example.eoghan.drinkdin;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import java.text.SimpleDateFormat;
import java.util.Date;


public class SweetmansActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback
{

//google maps variables
private GoogleMap mMap;
double lat, lon;

//firebase variables
private FirebaseAuth firebaseAuth;
private DatabaseReference databaseReference;

//variables for ratings
int count = 0;
double ratingTotalDBL;
double ratingCounterDBL;
float addToRating;
float currRating;
String ratingStr;
String ratingCounter;
String newRatingTotal;
String averageRatingStr;

//layout elements
private RatingBar phRating;
private Button btnAddToList;
private Button btnSubmitRating;
private TextView ratingTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sweetmans);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        getSupportActionBar().setTitle("Sweetmans");


        //rating bar
        phRating = (RatingBar) findViewById(R.id.sweetmansRating);
        phRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //pass float value to string for storage
                ratingStr = String.valueOf(rating);
            }

        });

        //declare layout elements
        btnAddToList = (Button) findViewById(R.id.checkInBtn);
        btnAddToList.setOnClickListener(this);

        btnSubmitRating = (Button) findViewById(R.id.submitRatinBtn);
        btnSubmitRating.setOnClickListener(this);

        ratingTV = (TextView) findViewById(R.id.totalRatingTV);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        //display toast message if there is no user
        if (firebaseAuth.getCurrentUser() == null) {
            Toast.makeText(this, "Please sign in to access full features", Toast.LENGTH_LONG).show();

        }

        //retrieve the average rating to display
        Query readRatingAvg = databaseReference.child("ratings").child("sweetmans");
        readRatingAvg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //get data from snapshot ensure it is not null
                if (dataSnapshot.child("sweetmans").getValue() != "null") {

                    //get value and pass it to string
                    String avgRating = dataSnapshot.child("averageRating").getValue().toString();

                    //display rating in edit text
                    ratingTV.setText("Rating:" + avgRating + "\u2605");

                } else {
                    //if there is a problem retrieving rating
                    ratingTV.setText("Rating: Error");

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

            //to set bar for home button
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
                        Intent i = new Intent(com.example.eoghan.drinkdin.SweetmansActivity.this, MainActivity.class);
                        startActivity(i);
                        return true;
                    case R.id.userIcon:
                        Intent j = new Intent(com.example.eoghan.drinkdin.SweetmansActivity.this, LoginActivity.class);
                        startActivity(j);
                        return true;
                    default:
                        return super.onOptionsItemSelected(item);
                }
            }

            //code for map, mostly auto generated by declaring class as map activity
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                //latitude and longitude for this pub
                lat = 53.346958;
                lon = -6.2583168;

                //set camera settings, title and marker at lat and lon
                mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lat, lon)));
                mMap.moveCamera(CameraUpdateFactory.zoomTo(10));
                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(lat, lon))
                        .title("Sweetmans"));


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

            //once a user has chosen a rating and hit enter this method will be called
            private void submitRating() {

                //cheking there is a rating stored
                if (ratingStr != null) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();

                    //check if there is a user logged in
                    if (user != null) {

                        //add the rating to the rating table
                        databaseReference.child("checkin").child(
                                user.getUid()).child("sweetmans").child("Rating").setValue(ratingStr);

                        //add to the number of ratings stored once the rating is submitted
                        Query RetrieveRatingCount = databaseReference.child("ratings").child("sweetmans");
                        RetrieveRatingCount.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot postSnapshot) {

                                //get data from snapshot
                                String data = postSnapshot.child("numRating").getValue().toString();

                                if (!data.equals(null)) {
                                    //take the string from the database
                                    String numRating = (String) postSnapshot.child("numRating").getValue();

                                    //convert string to int
                                    count = Integer.valueOf(numRating);

                                    //add one to int
                                    count++;

                                    //pass int back to string
                                    ratingCounter = Integer.toString(count);

                                    //post string to ratings counter table
                                    databaseReference.child("ratings").child("sweetmans").child("numRating").setValue(ratingCounter);

                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        //this query is to add the total value of all ratings
                        Query addRatingTotal = databaseReference.child("ratings").child("sweetmans");
                        addRatingTotal.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot postSnapshot2) {
                                //get data from snapshot
                                String data2 = postSnapshot2.child("totalRating").getValue().toString();

                                if (!data2.equals(null)) {
                                    //get string of previous value from database
                                    String totRating = (String) postSnapshot2.child("totalRating").getValue();

                                    //pass string to floats
                                    addToRating = Float.valueOf(totRating);

                                    //pass current value from string to float
                                    currRating = Float.valueOf(ratingStr);

                                    //add previous total with new rating
                                    float ratingTotal = addToRating + currRating;

                                    //pass new rating from float to string
                                    newRatingTotal = Float.toString(ratingTotal);

                                    //post new value to database
                                    databaseReference.child("ratings").child("sweetmans").child("totalRating").setValue(newRatingTotal);

                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        //this is to calculate the average rating of all user ratings
                        Query calcAverage = databaseReference.child("ratings").child("sweetmans");
                        calcAverage.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot postSnapshot3) {

                                //pass previous scores to doubles
                                ratingTotalDBL = Double.valueOf(newRatingTotal);
                                ratingCounterDBL = Double.valueOf(count);

                                //divide total by number of ratings
                                double averageRating = ratingTotalDBL / ratingCounterDBL;

                                //round average to closet .5 or .0
                                double avgRatingRounded = Math.round(averageRating * 2) / 2.0;

                                //pass average to string
                                averageRatingStr = Double.toString(avgRatingRounded);

                                //post it to database
                                databaseReference.child("ratings").child("sweetmans").child("averageRating").setValue(averageRatingStr);

                            }


                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        //confirm rating was stored
                        Toast.makeText(this, "Rating Stored: " + ratingStr, Toast.LENGTH_LONG).show();


                    } else

                    {
                        //if user is null
                        Toast.makeText(this, "You must log in to register rating", Toast.LENGTH_LONG).show();

                    }


                }else{
                    //if rating is null
                    Toast.makeText(this, "Please Enter value to submit", Toast.LENGTH_LONG).show();
                }

            }


            private void AddToList() {

                //get current user as we are using their personal table
                FirebaseUser user = firebaseAuth.getCurrentUser();

                //make sure there is someone logged in
                if (user != null) {

                    //code for adding timestamp
                    //set format
                    String datePattern = "dd/MM/yyyy";
                    SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);

                    //record date
                    String date = dateFormat.format(new Date());

                    //set to true if the user hits check in as they have now visited this pub
                    databaseReference.child("checkin").child(user.getUid()).child("sweetmans").child("timeStamp").setValue(date);

                    //set this pub as last check in, over writing previous
                    databaseReference.child("users").child(user.getUid()).child("lascheckin").setValue("Sweetmans");

                    //confirm to user that this was succsessful
                    Toast.makeText(this, "Added to check ins", Toast.LENGTH_LONG).show();
                }else {

                    //otherwise let the user know there was a problem as they were not logged in
                    Toast.makeText(this, "Error, please log in to use this function", Toast.LENGTH_LONG).show();

                }
            }
        }

