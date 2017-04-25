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

/**
 * Created by Eoghan on 07/12/2016.
 */

public class CraftList extends AppCompatActivity {

    ImageView pbar;

    AnimationDrawable frameAnimation;

    //private ProgressDialog pbar;
    String phouseRating;
    String sweetmansRating;
    String beerMarketRating;
    String headlineRating;
    String blacksheepRating;

    //private ImageView pbar;
   //rivate DatabaseReference databaseReference;

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
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        //query firebase to display rating
        Query getRatings = databaseReference.child("ratings");
        getRatings.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot postSnapshot) {
                if (postSnapshot.child("averageRating").getValue() != "null") {

                    //pass it to string
                    phouseRating = postSnapshot.child("porterhouse").child("averageRating").getValue().toString();
                    sweetmansRating = postSnapshot.child("sweetmans").child("averageRating").getValue().toString();
                    beerMarketRating = postSnapshot.child("beermarket").child("averageRating").getValue().toString();
                    headlineRating = postSnapshot.child("headline").child("averageRating").getValue().toString();
                    blacksheepRating = postSnapshot.child("blacksheep").child("averageRating").getValue().toString();

                    setValues();

                } else {
                    phouseRating = "Sorry no rating available";
                    sweetmansRating = "Sorry no rating available";
                    headlineRating = "Sorry no rating available";
                    beerMarketRating = "Sorry no rating available";
                    blacksheepRating = "Sorry no rating available";
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
        String[] craftList = {
                "Porterhouse " + phouseRating + "\u2605",
                "Sweetmans " + sweetmansRating + "\u2605",
                "Beer Market " + beerMarketRating + "\u2605",
                "57, The Headline " + headlineRating + "\u2605",
                "The Black Sheep " + blacksheepRating + "\u2605"

        };

        //adapter for list
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activity_list, craftList);

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
                        Intent phouseIntent = new Intent(CraftList.this, PorterhouseActivity.class);
                        startActivity(phouseIntent);
                        break;
                    case 1:
                        Intent sweetmansIntent = new Intent(CraftList.this, SweetmansActivity.class);
                        startActivity(sweetmansIntent);
                        break;
                    case 2:
                        Intent beermarketIntent = new Intent(CraftList.this, BeerMarketActivity.class);
                        startActivity(beermarketIntent);
                        break;
                    case 3:
                        Intent headlineIntent = new Intent(CraftList.this, HeadlineActivity.class);
                        startActivity(headlineIntent);
                        break;
                    case 4:
                        Intent blacksheepIntent = new Intent(CraftList.this, BlackSheepActivity.class);
                        startActivity(blacksheepIntent);
                        break;

                }


            }

        });

        //stop progress dialog when list is loaded
        frameAnimation.stop();
        pbar.setImageResource(android.R.color.transparent);


    }

}
