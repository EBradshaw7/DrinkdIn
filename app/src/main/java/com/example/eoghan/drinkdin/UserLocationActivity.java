package com.example.eoghan.drinkdin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    String checkin;

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
        tvInfo = (TextView) findViewById(R.id.showFavs);

        //call method to show data
        showInfo();
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
                           if (postSnapshot.exists()){
                               //get data from snapshot
                               //UserInformation userInformation = postSnapshot.getValue(UserInformation.class);

                               //pass it to string
                               String favs = "Favourite Drink: " + postSnapshot.child("drink").getValue() +
                                       "\nFavourite Bar: " + postSnapshot.child("bar").getValue();

                               if (postSnapshot.child("lascheckin").getValue() != "null"){
                                   checkin = "checked in " + postSnapshot.child("lascheckin").getValue();
                               }
                               tvInfo.setText(favs + "\n " + checkin);
                           }
                       }

                       @Override
                       public void onCancelled(DatabaseError databaseError) {

                       }
                   });

         /*   @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //loop through and print data
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    //get data from snapshot
                    UserInformation userInformation = postSnapshot.getValue(UserInformation.class);

                    //pass it to string
                    String favs = "Favourite Drink: " + userInformation.getDrink()+"\nFavourite Bar: "+userInformation.getBar();
                            *//*"\nChecked in: "+
                            userInformation.getPhouseCheckIn();*//*

                            if (userInformation.getPhouseCheckIn() != null){
                                checkin = "checked in" + userInformation.getPhouseCheckIn();
                            }
                    tvInfo.setText(favs + "\n " + checkin);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

    }
}
