package com.example.eoghan.drinkdin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Eoghan on 07/12/2016.
 */

public class CategoriesScreen extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);

        Button craftButton = (Button) findViewById(R.id.craftBtn);
        craftButton.setOnClickListener(this);

    }

        public void onClick (View v){
            //Log.i("clicks", "You Clicked B1");
            Intent i = new Intent(CategoriesScreen.this, CraftList.class);
            startActivity(i);


        }
    }
