package com.example.eoghan.drinkdin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserLocationActivity extends AppCompatActivity {

    private TextView tvUsername;
    private TextView tvLastcheckin;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    String lastCheckin;



    //String checkinList;

    //craft
    String phouseCheck;
    String sweetmansCheck;
    String beermarketCheck;
    String headlineCheck;
    String blackSheepCheck;

    //guinness
    String brazenheadCheck;
    String templebarCheck;
    String stagsheadCheck;
    String arthursCheck;
    String grogansCheck;

    //whiskey
    String dingleCheck;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_location);


        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        Query getLastCheckin = databaseReference.child("users").child(firebaseAuth.getCurrentUser().getUid());
        getLastCheckin.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    lastCheckin = dataSnapshot.child("lascheckin").getValue().toString();

                }




            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        if (lastCheckin == null){
            lastCheckin = "No Last Checkin Stored";
        }


        tvUsername = (TextView) findViewById(R.id.userNameTV);
        tvLastcheckin = (TextView) findViewById(R.id.lastCheckinTV);


        tvUsername.setText("User: " + user.getEmail());
        tvLastcheckin.setText("Last Check-In: " + lastCheckin);


        //call method to show data
        //showInfo();
        showCheckins();
    }



    private void showCheckins() {
            databaseReference = FirebaseDatabase.getInstance().getReference();
            Query showCheckinInfo = databaseReference.child("checkin").child(firebaseAuth.getCurrentUser().getUid());
            showCheckinInfo.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                       for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                           /*if (postSnapshot.child("Porterhouse").getValue() == null) {
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
                           if (postSnapshot.child("BlackSheep").getValue() == null) {
                               blackSheepCheck = "Black Sheep, Rating: " +
                                       dataSnapshot.child("BlackSheep").child("Rating").getValue() + ", Date : " +
                                       dataSnapshot.child("BlackSheep").child("timeStamp").getValue();

                           }else{
                               blackSheepCheck = "notChecked";
                           }
                           if (postSnapshot.child("BrazenHead").getValue() == null) {
                               brazenheadCheck = "Brazen Head, Rating: " +
                                       dataSnapshot.child("BrazenHead").child("Rating").getValue() + ", Date : " +
                                       dataSnapshot.child("BrazenHead").child("timeStamp").getValue();

                           }else{
                               brazenheadCheck = "notChecked";
                           }
                           if (postSnapshot.child("TempleBar").getValue() == null) {
                               templebarCheck = "Temple Bar, Rating: " +
                                       dataSnapshot.child("TempleBar").child("Rating").getValue() + ", Date : " +
                                       dataSnapshot.child("TempleBar").child("timeStamp").getValue();

                           }else{
                               templebarCheck = "notChecked";
                           }*/
                           if (postSnapshot.getKey().equals("StagsHead")) {
                               if (postSnapshot.hasChild("timeStamp")) {
                                   stagsheadCheck = "Stags Head, Rating: " +
                                           dataSnapshot.child("StagsHead").child("Rating").getValue() + ", Date : " +
                                           dataSnapshot.child("StagsHead").child("timeStamp").getValue();

                               }else {
                                   stagsheadCheck = "notChecked";
                               }

                           } else if (!postSnapshot.exists()){
                               stagsheadCheck = "notChecked";
                           }

                           if (postSnapshot.getKey().equals("Arthurs")) {
                               if (postSnapshot.hasChild("timeStamp")) {
                                   arthursCheck = "Arthurs, Rating: " +
                                           dataSnapshot.child("Arthurs").child("Rating").getValue() + ", Date : " +
                                           dataSnapshot.child("Arthurs").child("timeStamp").getValue();

                               }

                           } else if (!postSnapshot.exists()) {
                               arthursCheck = "notChecked";
                           }

                           if (postSnapshot.getKey().equals("Grogans")) {
                               if (postSnapshot.hasChild("timeStamp")) {
                                   grogansCheck = "Grogans, Rating: " +
                                           dataSnapshot.child("Grogans").child("Rating").getValue() + ", Date : " +
                                           dataSnapshot.child("Grogans").child("timeStamp").getValue();

                               }
                           }else if(!postSnapshot.exists()) {
                               grogansCheck = "notChecked";
                           }
                       }
                    displayValues();

                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });





        }

    private void displayValues() {


        List<Object> checkInList = new ArrayList<>();
        /*checkInList.add(phouseCheck);
        checkInList.add(sweetmansCheck);
        checkInList.add(beermarketCheck);
        checkInList.add(headlineCheck);
        checkInList.add(blackSheepCheck);
        checkInList.add(brazenheadCheck);
        checkInList.add(templebarCheck);*/
        checkInList.add(stagsheadCheck);
        checkInList.add(arthursCheck);
        checkInList.add(grogansCheck);
        //checkInList.add(dingleCheck);


        for(int i = 0 ; i<checkInList.size(); i++)
        {
            Object item = checkInList.get(i);
            if(item == null)
            {
                checkInList.remove(i);
            }
        }

      /*  for(String curVal : checkInList){
            if(curVal.contains("notChecked") || curVal.contains("null")){
                checkInList.remove(curVal);
            }
        }*/

       /*//tring remove = "notChecked";
        Iterator<String> it = checkInList.iterator();
        while (it.hasNext()) {
                if (it.next().equals("notChecked")) {
                    it.remove();
                }

        }*/

            ArrayAdapter<Object> adapter = new ArrayAdapter<>(this,
                    R.layout.activity_list, checkInList);




            ListView checkInLV = (ListView) findViewById(R.id.listCheckin);
            checkInLV.setAdapter(adapter);


            //Toast.makeText(this, "No checkins", Toast.LENGTH_LONG).show();

        }
    }

