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
import java.util.Iterator;
import java.util.List;

public class UserLocationActivity extends AppCompatActivity {

    private TextView tvInfo;
    //private TextView tvCheckins;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;



    String nameStr;
    String faveBarStr;
    String faveDrinkStr;
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
    String arthursCheck = "notChecked";
    String grogansCheck;

    //whiskey
    String dingleCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_location);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        //databaseReference = FirebaseDatabase.getInstance().getReference();
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
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                   /* if(postSnapshot.getKey().equals("sweetmans")) {
                        if (postSnapshot.hasChild("timeStamp")) {
                            sweetmansCheck = "Sweetmans, Rating: " +
                                    dataSnapshot.child("sweetmans").child("Rating").getValue() + ", Date : " +
                                    dataSnapshot.child("sweetmans").child("timeStamp").getValue();

                        } else {
                            sweetmansCheck = "notChecked";
                        }*/


                    if (postSnapshot.getKey().equals("name")) {
                            if (!postSnapshot.child("name").equals(null)){
                                nameStr = "Name: " + dataSnapshot.child("name").getValue();
                            } else {
                                nameStr = "Name: No name stored";
                            }
                        }
                    if (postSnapshot.getKey().equals("drink")) {
                            if (!postSnapshot.child("drink").equals(null)) {
                                faveDrinkStr = "Favourite Drink: " + dataSnapshot.child("drink").getValue();
                            } else {
                                faveDrinkStr = "Favourite Drink: No preference Stored";
                            }
                        }
                    if (postSnapshot.getKey().equals("bar")) {
                            if (!postSnapshot.child("bar").equals(null)) {
                                faveBarStr = "Favourite Bar: " + dataSnapshot.child("bar").getValue();
                            } else {
                                faveBarStr = "Favourite Bar: No favourite bar stored";
                        }
                    }

                    if (postSnapshot.getKey().equals("lascheckin")) {
                            if (!postSnapshot.child("lascheckin").equals(null)) {
                                lastCheckin = "Last check in: " + dataSnapshot.child("lascheckin").getValue();
                            } else {
                                lastCheckin = "Last check in: No Check-ins, Yet!";
                            }
                    }
                }

                displayUserInfo();
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
                lastCheckin


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
                           //Log.e("waddup", postSnapshot.getKey());
                           //get data from snapshot

                      /*     if (postSnapshot.child("Porterhouse").hasChild("timeStamp")) {
                               phouseCheck = "Porterhouse, Rating: " +
                                       dataSnapshot.child("Porterhouse").child("Rating").getValue() + ", Date : " +
                                       dataSnapshot.child("Porterhouse").child("timeStamp").getValue();
                           }else{
                               phouseCheck = "notChecked";
                           } */

                           if(postSnapshot.getKey().equals("sweetmans")) {
                               if (postSnapshot.hasChild("timeStamp")) {
                                   sweetmansCheck = "Sweetmans, Rating: " +
                                           dataSnapshot.child("sweetmans").child("Rating").getValue() + ", Date : " +
                                           dataSnapshot.child("sweetmans").child("timeStamp").getValue();

                               } else {
                                   sweetmansCheck = "notChecked";
                               }
                           }

/*                           if (postSnapshot.child("BeerMarket").getValue() == null) {
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
                           if (postSnapshot.child("BlackSheep").exists()) {
                                blackSheepCheck = "Black Sheep, Rating: " +
                                       dataSnapshot.child("BlackSheep").child("Rating").getValue().toString() + ", Date : " +
                                       dataSnapshot.child("BlackSheep").child("timeStamp").getValue().toString();

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
                           }
                           if (postSnapshot.child("StagsHead").getValue() == null) {
                               stagsheadCheck = "Stags Head, Rating: " +
                                       dataSnapshot.child("StagsHead").child("Rating").getValue() + ", Date : " +
                                       dataSnapshot.child("StagsHead").child("timeStamp").getValue();

                           }else{
                               stagsheadCheck = "notChecked";
                           }*/
                            if(postSnapshot.getKey().equals("Arthurs")) {
                                if (postSnapshot.hasChild("timeStamp")) {
                                    arthursCheck = "Arthurs, Rating: " +
                                            dataSnapshot.child("Arthurs").child("Rating").getValue() + ", Date : " +
                                            dataSnapshot.child("Arthurs").child("timeStamp").getValue();

                                } else {
                                    arthursCheck = "notChecked";
                                }
                            }
                           /*if (!postSnapshot.child("Grogans").hasChild("timeStamp")) {
                               grogansCheck = "Grogans, Rating: " +
                                       dataSnapshot.child("Grogans").child("Rating").getValue() + ", Date : " +
                                       dataSnapshot.child("Grogans").child("timeStamp").getValue();

                           }else{
                               grogansCheck = "notChecked";
                           }*/
                           if(postSnapshot.getKey().equals("Dingle")) {
                               if (postSnapshot.hasChild("timeStamp")) {
                                   dingleCheck = "Dingle Bar, Rating: " +
                                           dataSnapshot.child("Dingle").child("Rating").getValue() + ", Date : " +
                                           dataSnapshot.child("Dingle").child("timeStamp").getValue();

                               } else {
                                   dingleCheck = "notChecked";
                               }
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


        List<String> checkInList = new ArrayList<String>();
        //checkInList.add(phouseCheck);
        checkInList.add(sweetmansCheck);
       /* checkInList.add(beermarketCheck);
        checkInList.add(headlineCheck);
        checkInList.add(blackSheepCheck);
        checkInList.add(brazenheadCheck);
        checkInList.add(templebarCheck);
        checkInList.add(stagsheadCheck);*/
        checkInList.add(arthursCheck);
        //checkInList.add(grogansCheck);
        checkInList.add(dingleCheck);

 /*       for(String curVal : checkInList){
            if(curVal.contains("notChecked")){
                checkInList.remove(curVal);
            }

        }*/

        String remove = "notChecked";
        Iterator<String> it = checkInList.iterator();
        while (it.hasNext()) {
            if (it.next().equals(remove)) {
                it.remove();
            }
        }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    R.layout.activity_list, checkInList);




            ListView checkInLV = (ListView) findViewById(R.id.listCheckin);
            checkInLV.setAdapter(adapter);


            //Toast.makeText(this, "No checkins", Toast.LENGTH_LONG).show();

        }
    }

