package com.example.eoghan.drinkdin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserLocationActivity extends AppCompatActivity {

    private TextView tvInfo;
    //private TextView tvCheckins;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;



    String nameStr;
    String faveBarStr;
    String faveDrinkStr;
    String checkin;

    String checkinList;

    String phouseCheck;
    String sweetmansCheck;
    String beermarketCheck;
    String headlineCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_location);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();
        //tvInfo = (TextView) findViewById(R.id.showFavs);
        //tvCheckins = (TextView) findViewById(R.id.checkInsList);




        //call method to show data
        showInfo();
        showCheckins();
    }



    private void showInfo() {
        databaseReference = FirebaseDatabase.getInstance().getReference();

        //add value event listener to listen for data
        //databaseReference.child("users").child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {

        Query userInfo = databaseReference.child("users").child(firebaseAuth.getCurrentUser().getUid());
        userInfo.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot postSnapshot) {
                //loop through and print data
                if (postSnapshot.exists()) {
                    if (postSnapshot.child("name") != null) {
                        nameStr = "Name: " + postSnapshot.child("name").getValue();
                        displayUserInfo();
                    }
                    if (postSnapshot.child("name").getValue().equals("")) {
                        nameStr = "Name: No name stored";
                        displayUserInfo();
                    }

                    if (postSnapshot.child("drink") != null) {
                        faveDrinkStr = "Favourite Drink: " + postSnapshot.child("drink").getValue();
                        displayUserInfo();
                    }
                    if (postSnapshot.child("drink").getValue().equals("")) {
                        faveDrinkStr = "Favourite Drink: No favourite drink stored";
                        displayUserInfo();
                    }


                    if (postSnapshot.child("bar") != null) {
                        faveBarStr = "Favourite Bar: " + postSnapshot.child("bar").getValue();
                        displayUserInfo();
                    }
                    if (postSnapshot.child("bar").getValue().equals("")) {
                        faveBarStr = "Favourite Bar: No favourite bar stored";
                        displayUserInfo();
                    }


                    if (postSnapshot.child("lascheckin") != null) {
                        checkin = "Last check in: " + postSnapshot.child("lascheckin").getValue();
                        displayUserInfo();
                    }
                    if (postSnapshot.child("lascheckin").getValue() == null) {
                        checkin = "Last check in: No Checkins Yet!";
                        displayUserInfo();
                    } else {
                        checkin = "Error no data found";
                    }

                }else{
                    nameStr = "Name: No name stored";
                    faveBarStr = "Favourite Bar: No favourite bar stored";
                    faveDrinkStr = "Favourite Bar: No favourite drink stored";
                    checkin = "Last check in: No Checkins Yet! ";
                    displayUserInfo();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void displayUserInfo() {

        String[] userInfoList = {
                nameStr,
                faveBarStr,
                faveDrinkStr,
                checkin


        };
        ArrayAdapter<String> adapterUser = new ArrayAdapter<String>(this,
                R.layout.activity_list, userInfoList);

        ListView userInfoLV = (ListView) findViewById(R.id.listUserInfo);
        userInfoLV.setAdapter(adapterUser);
    }

    private void showCheckins() {
            databaseReference = FirebaseDatabase.getInstance().getReference();
            Query showCheckinInfo = databaseReference.child("checkin").child(firebaseAuth.getCurrentUser().getUid());
            showCheckinInfo.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                       for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                           //get data from snapshot

                           if (postSnapshot.child("Porterhouse").getValue() == null) {
                               phouseCheck = "Porterhouse, Rating: " +
                                       dataSnapshot.child("Porterhouse").child("Rating").getValue() + ", Date : " +
                                       dataSnapshot.child("Porterhouse").child("timeStamp").getValue();
                           }else{
                               phouseCheck = "notChecked";
                           }


                           if (postSnapshot.child("Sweetmans").getValue() == null) {
                               sweetmansCheck = "Sweetmans, Rating: " +
                                       dataSnapshot.child("sweetmans").child("Rating").getValue() + ", Date : " +
                                       dataSnapshot.child("sweetmans").child("timeStamp").getValue();

                           }else{
                               sweetmansCheck = "notChecked";
                           }


                           if (postSnapshot.child("BeerMarket").getValue() == null) {
                                beermarketCheck = "BeerMarket, Rating: " +
                                       dataSnapshot.child("Beermarket").child("Rating").getValue() + ", Date : " +
                                       dataSnapshot.child("Beermarket").child("timeStamp").getValue();

                           }else{
                                beermarketCheck = "notChecked";
                           }
                           if (postSnapshot.child("Headline").getValue() == null) {
                                headlineCheck = "Headline, Rating: " +
                                       dataSnapshot.child("Headline").child("Rating").getValue() + ", Date : " +
                                       dataSnapshot.child("Headline").child("timeStamp").getValue();

                           }else{
                                headlineCheck = "notChecked";
                           }

                           displayValues();

                       }


                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });





        }

    private void displayValues() {


        List<String> checkIn = new ArrayList<String>();
        checkIn.add(phouseCheck);
        checkIn.add(sweetmansCheck);
        checkIn.add(beermarketCheck);
        checkIn.add(headlineCheck);

        for(String curVal : checkIn){
            if(curVal.contains("notChecked") || curVal.contains("null")){
                checkIn.remove(curVal);
            }
        }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    R.layout.activity_list, checkIn);




            ListView checkInLV = (ListView) findViewById(R.id.listCheckin);
            checkInLV.setAdapter(adapter);


            //Toast.makeText(this, "No checkins", Toast.LENGTH_LONG).show();

        }
    }

