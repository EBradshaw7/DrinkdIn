package com.example.eoghan.drinkdin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Eoghan on 15/03/2017.
 */

public class CrawlScreen extends AppCompatActivity {

    String[] crawlList = {
            "Camden Street Crawl"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crawlscrn);

        getSupportActionBar().setTitle("List of Bar Crawls");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activity_list, crawlList);

        ListView list = (ListView) findViewById(R.id.crawlList);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        Intent camdenIntent = new Intent(CrawlScreen.this, CamdenStCrawl.class);
                        startActivity(camdenIntent);
                        break;
                }
            }
        });


    }

}