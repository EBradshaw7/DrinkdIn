package com.example.eoghan.drinkdin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Eoghan on 07/12/2016.
 */

public class CraftList extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_craft);

        Button porterBtn = (Button) findViewById(R.id.phBtn);
        porterBtn.setOnClickListener(this);
    }



    public void onClick (View v){
        //Log.i("clicks", "You Clicked B1");
        Intent i = new Intent(CraftList.this, phouseMap.class);
        startActivity(i);
    }
}
