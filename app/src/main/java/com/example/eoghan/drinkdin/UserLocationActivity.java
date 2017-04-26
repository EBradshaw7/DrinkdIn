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

    //cocktail
    String alfiesCheck;

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


       /* if (lastCheckin == null){
            lastCheckin = "No Last Checkin Stored";
        }*/


        tvUsername = (TextView) findViewById(R.id.userNameTV);
        tvLastcheckin = (TextView) findViewById(R.id.lastCheckinTV);


        tvUsername.setText("User: " + user.getEmail());



        //call method to show data
        showInfo();
        showCheckins();
    }

    private void showInfo() {

        databaseReference = FirebaseDatabase.getInstance().getReference();
        Query getLastCheckin = databaseReference.child("users").child(firebaseAuth.getCurrentUser().getUid());
        getLastCheckin.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot postSnapshot) {
                if (postSnapshot.exists()){
                        if (postSnapshot.child("lascheckin").getValue() != null) {
                            lastCheckin = "Last Check-in: " + postSnapshot.child("lascheckin").getValue();
                        }else {
                            lastCheckin = "Last Check-in: No last check-in found";
                        }
                }
                setInfo();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void setInfo() {

        tvLastcheckin.setText(lastCheckin);

    }


    private void showCheckins() {
            databaseReference = FirebaseDatabase.getInstance().getReference();
            Query showCheckinInfo = databaseReference.child("checkin").child(firebaseAuth.getCurrentUser().getUid());
            showCheckinInfo.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                       for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                           if (postSnapshot.getKey().equals("Porterhouse")) {
                               if (postSnapshot.hasChild("timeStamp")) {
                                   phouseCheck = "Porterhouse, Rating: " +
                                       dataSnapshot.child("Porterhouse").child("Rating").getValue() + ", Date : " +
                                       dataSnapshot.child("Porterhouse").child("timeStamp").getValue();
                                }

                           }
                           if (postSnapshot.getKey().equals("sweetmans")) {
                               if (postSnapshot.hasChild("timeStamp")) {
                                   sweetmansCheck = "Sweetmans, Rating: " +
                                       dataSnapshot.child("sweetmans").child("Rating").getValue() + ", Date : " +
                                       dataSnapshot.child("sweetmans").child("timeStamp").getValue();

                                }

                           }
                           if (postSnapshot.getKey().equals("Beermarket")) {
                               if (postSnapshot.hasChild("timeStamp")) {
                                   beermarketCheck = "BeerMarket, Rating: " +
                                       dataSnapshot.child("Beermarket").child("Rating").getValue() + ", Date : " +
                                       dataSnapshot.child("Beermarket").child("timeStamp").getValue();

                                }

                           }
                           if (postSnapshot.getKey().equals("Headline")) {
                               if (postSnapshot.hasChild("timeStamp")) {
                                   headlineCheck = "Headline, Rating: " +
                                       dataSnapshot.child("Headline").child("Rating").getValue() +  ", Date : " +
                                       dataSnapshot.child("Headline").child("timeStamp").getValue();

                                }

                           }
                           if (postSnapshot.getKey().equals("BlackSheep")) {
                               if (postSnapshot.hasChild("timeStamp")) {
                                   blackSheepCheck = "Black Sheep, Rating: " +
                                       dataSnapshot.child("BlackSheep").child("Rating").getValue() + ", Date : " +
                                       dataSnapshot.child("BlackSheep").child("timeStamp").getValue();

                                }

                           }
                           if (postSnapshot.getKey().equals("BrazenHead")) {
                               if (postSnapshot.hasChild("timeStamp")) {
                                   brazenheadCheck = "Brazen Head, Rating: " +
                                       dataSnapshot.child("BrazenHead").child("Rating").getValue() + ", Date : " +
                                       dataSnapshot.child("BrazenHead").child("timeStamp").getValue();

                                }

                           }
                           if (postSnapshot.getKey().equals("TempleBar")) {
                               if (postSnapshot.hasChild("timeStamp")) {
                                   templebarCheck = "Temple Bar, Rating: " +
                                       dataSnapshot.child("TempleBar").child("Rating").getValue() + ", Date : " +
                                       dataSnapshot.child("TempleBar").child("timeStamp").getValue();
                                 }

                           }
                           if (postSnapshot.getKey().equals("StagsHead")) {
                               if (postSnapshot.hasChild("timeStamp")) {
                                   stagsheadCheck = "Stags Head, Rating: " +
                                           dataSnapshot.child("StagsHead").child("Rating").getValue() + ", Date : " +
                                           dataSnapshot.child("StagsHead").child("timeStamp").getValue();

                               }

                           }

                           if (postSnapshot.getKey().equals("Arthurs")) {
                               if (postSnapshot.hasChild("timeStamp")) {
                                   arthursCheck = "Arthurs, Rating: " +
                                           dataSnapshot.child("Arthurs").child("Rating").getValue() + ", Date : " +
                                           dataSnapshot.child("Arthurs").child("timeStamp").getValue();

                               }

                           }

                           if (postSnapshot.getKey().equals("Grogans")) {
                               if (postSnapshot.hasChild("timeStamp")) {
                                   grogansCheck = "Grogans, Rating: " +
                                           dataSnapshot.child("Grogans").child("Rating").getValue() + ", Date : " +
                                           dataSnapshot.child("Grogans").child("timeStamp").getValue();

                               }
                           }
                           if (postSnapshot.getKey().equals("Dingle")) {
                               if (postSnapshot.hasChild("timeStamp")) {
                                   dingleCheck = "Dingle, Rating: " +
                                           dataSnapshot.child("Dingle").child("Rating").getValue() + ", Date : " +
                                           dataSnapshot.child("Dingle").child("timeStamp").getValue();

                               }
                           }
                           if (postSnapshot.getKey().equals("Alfies")) {
                               if (postSnapshot.hasChild("timeStamp")) {
                                    alfiesCheck  = "Alfies, Rating: " +
                                           dataSnapshot.child("Alfies").child("Rating").getValue() + ", Date : " +
                                           dataSnapshot.child("Alfies").child("timeStamp").getValue();

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


        List<Object> checkInList = new ArrayList<>();
        checkInList.add(phouseCheck);
        checkInList.add(sweetmansCheck);
        checkInList.add(beermarketCheck);
        checkInList.add(headlineCheck);
        checkInList.add(blackSheepCheck);
        checkInList.add(brazenheadCheck);
        checkInList.add(templebarCheck);
        checkInList.add(stagsheadCheck);
        checkInList.add(arthursCheck);
        checkInList.add(grogansCheck);
        checkInList.add(dingleCheck);
        checkInList.add(alfiesCheck);


            for (int i = 0; i < checkInList.size(); i++) {
                Object item = checkInList.get(i);
                if (item == null) {
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



        }
    }

