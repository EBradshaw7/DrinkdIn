package com.example.eoghan.drinkdin;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class WhiskeyList extends AppCompatActivity {

    ImageView pbar;

    AnimationDrawable frameAnimation;

    //Popullate list views with ratings
    String dingleRating;
    String norsmanRating;


    //private ImageView pbar;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whiskey_list);

        //set title
        getSupportActionBar().setTitle("Pubs With Great Whiskey Selection");

        //using image as progress bar
        pbar = (ImageView) findViewById(R.id.pintAniImg);
        frameAnimation = (AnimationDrawable) pbar.getDrawable();
        frameAnimation.start();

        //get reference from firebase
        databaseReference = FirebaseDatabase.getInstance().getReference();

        //query firebase to display rating
        Query getRatings = databaseReference.child("ratings");
        getRatings.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot postSnapshot) {
                if (postSnapshot.child("dingle").child("averageRating").getValue() != null) {

                    //pass it to string
                    dingleRating = postSnapshot.child("dingle").child("averageRating").getValue().toString();
                    norsmanRating = postSnapshot.child("norseman").child("averageRating").getValue().toString();


                } else {
                    dingleRating = "Sorry no rating available";
                    norsmanRating = "Sorry no rating available";

                }
                setValues();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    //assigning rating values to string
    public void setValues() {

        //creating list to display with unicode stars
        String[] guinnessList = {
                "The Dingle Bar " + dingleRating + "\u2605",
                "The Norseman " + norsmanRating + "\u2605"
        };

        //adapter for list
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activity_list, guinnessList);

        //create list view and add style
        ListView list = (ListView) findViewById(R.id.listWhiskey);
        list.setAdapter(adapter);

        //set onclick for list
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        Intent dingleIntent = new Intent(WhiskeyList.this, DingleActivity.class);
                        startActivity(dingleIntent);
                        break;
                    case 1:
                        Intent norseIntent = new Intent(WhiskeyList.this, NorsemanActivity.class);
                        startActivity(norseIntent);
                        break;



                }


            }

        });

        //stop progress dialog when list is loaded
        frameAnimation.stop();
        pbar.setImageResource(android.R.color.transparent);


    }


}



