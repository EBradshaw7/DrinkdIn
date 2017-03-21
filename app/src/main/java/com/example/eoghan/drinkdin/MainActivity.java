package com.example.eoghan.drinkdin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button catBtn;
    private Button crwlBtn;
    private Button usrBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        catBtn = (Button) findViewById(R.id.catogoriesBtn);
        crwlBtn = (Button) findViewById(R.id.crawlBtn);
        usrBtn = (Button) findViewById(R.id.userBtn);

        catBtn.setOnClickListener(this);
        crwlBtn.setOnClickListener(this);
        usrBtn.setOnClickListener(this);
    }


/*
    public void onClickCrawl(View v) {

        //Log.i("clicks", "You Clicked B1");
        Intent i = new Intent(MainActivity.this, CrawlScreen.class);
        startActivity(i);
    }*/

    @Override
    public void onClick(View v) {
    /*    //Log.i("clicks", "You Clicked B1");
        Intent j = new Intent(MainActivity.this, CategoriesScreen.class);
        startActivity(j); */
    if (v == catBtn){
        startActivity(new Intent(this, CategoriesScreen.class));

    }else if (v == crwlBtn){
        startActivity(new Intent(this, CrawlScreen.class));

    }else if (v == usrBtn){
        startActivity(new Intent(this, LoginActivity.class));

    }

    }
}
