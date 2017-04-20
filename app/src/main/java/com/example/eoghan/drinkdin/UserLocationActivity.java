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

                    //pass it to string
                    nameStr = "Name: " + postSnapshot.child("name").getValue();

                    faveDrinkStr = "Favourite Drink: " + postSnapshot.child("drink").getValue();

                    faveBarStr = "Favourite Bar: " + postSnapshot.child("bar").getValue();

                    if (postSnapshot.child("lascheckin").getValue() != "null") {
                        checkin = "Last check in: " + postSnapshot.child("lascheckin").getValue();

                        //tvInfo.setText(favs + "\n" + checkin);
                        displayUserInfo();
                    }
                    if (postSnapshot.child("lascheckin").getValue() == "null") {
                        // tvInfo.setText(favs + "\nLast check in: No info entered, Yet!"
                        checkin = "Last check in: No Checkins Yet!";
                        displayUserInfo();
                    } else {
                        checkin = "Error no data found";

                    }
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

                        if (postSnapshot.child("Porterhouse").getValue() != "null") {
                            phouseCheck = "Porterhouse, Rating: " +
                             dataSnapshot.child("Porterhouse").child("Rating").getValue() + ", Date : " +
                                dataSnapshot.child("Porterhouse").child("timeStamp").getValue();



                            displayValues();
                            //tvCheckins.setText(checkinList);
                        }else{
                            //tvCheckins.setText("No check-ins, yet!");
                        }

                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });





        }

    private void displayValues() {
        String[] checkInList = {
            phouseCheck

    };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activity_list, checkInList);

        ListView checkInLV = (ListView) findViewById(R.id.listCheckin);
        checkInLV.setAdapter(adapter);
    }

}

