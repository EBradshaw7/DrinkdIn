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

    //private ImageView pbar;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_craft);

        pbar = (ImageView) findViewById(R.id.pintAniImg);

            frameAnimation = (AnimationDrawable) pbar.getDrawable();

            frameAnimation.start();



            databaseReference = FirebaseDatabase.getInstance().getReference();

            Query getRatings = databaseReference.child("ratings");
            getRatings.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot postSnapshot) {
                    if (postSnapshot.child("averageRating").getValue() != "null") {

                        //pass it to string
                        phouseRating = postSnapshot.child("porterhouse").child("averageRating").getValue().toString();
                        sweetmansRating = postSnapshot.child("sweetmans").child("averageRating").getValue().toString();
                        beerMarketRating = postSnapshot.child("beermarket").child("averageRating").getValue().toString();

                        setValues();

                    } else {
                        phouseRating = "Sorry no rating available";
                        sweetmansRating = "Sorry no rating available";
                        beerMarketRating = "Sorry no rating available";
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }


    public void setValues() {

        String[] craftList = {
                "Porterhouse " + phouseRating + "\u2605",
                "Sweetmans " + sweetmansRating + "\u2605",
                "BeerMarket " + beerMarketRating + "\u2605"

        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activity_list, craftList);

        ListView list = (ListView) findViewById(R.id.listCraft);
        list.setAdapter(adapter);

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

                }


            }

        });
        frameAnimation.stop();
        pbar.setImageResource(android.R.color.transparent);



    }

}
