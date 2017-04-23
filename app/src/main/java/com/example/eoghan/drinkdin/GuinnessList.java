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

public class GuinnessList extends AppCompatActivity {


    ImageView pbar;

    AnimationDrawable frameAnimation;

    //private ProgressDialog pbar;
    String phouseRating;
    String sweetmansRating;
    String beerMarketRating;
    String headlineRating;
    String blacksheepRating;

    //private ImageView pbar;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_craft);

        //set title
        getSupportActionBar().setTitle("List of Craft Pubs");

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
                if (postSnapshot.child("averageRating").getValue() != "null") {

                    //pass it to string
                    phouseRating = postSnapshot.child("porterhouse").child("averageRating").getValue().toString();

                    setValues();

                } else {
                    phouseRating = "Sorry no rating available";

                }

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
                "Porterhouse " + phouseRating + "\u2605"


        };

        //adapter for list
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activity_list, guinnessList);

        //create list view and add style
        ListView list = (ListView) findViewById(R.id.listCraft);
        list.setAdapter(adapter);

        //set onclick for list
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        Intent phouseIntent = new Intent(GuinnessList.this, PorterhouseActivity.class);
                        startActivity(phouseIntent);
                        break;


                }


            }

        });

        //stop progress dialog when list is loaded
        frameAnimation.stop();
        pbar.setImageResource(android.R.color.transparent);


    }

    
}
