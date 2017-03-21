package com.example.eoghan.drinkdin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Eoghan on 15/03/2017.
 */

public class CrawlScreen extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crawlscrn);

        Button wexBtn = (Button) findViewById(R.id.wexBtn);
        wexBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(CrawlScreen.this, WexfordStCrawl.class);
        startActivity(i);
    }
}
