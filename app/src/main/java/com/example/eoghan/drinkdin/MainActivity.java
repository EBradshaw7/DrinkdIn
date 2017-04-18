package com.example.eoghan.drinkdin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity  {


    String[] menuList = {
            "Categories",
            "User Area",
            "Plan My Crawl",
            "Contact us"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activity_list, menuList);

        ListView list = (ListView) findViewById(R.id.listMenu);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch(position){
                    case 0:  Intent catActivity = new Intent(MainActivity.this, CategoriesScreen.class);
                        startActivity(catActivity);
                        break;
                    case 1:  Intent userActivity = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(userActivity);
                        break;
                    case 2:  Intent crawlActivity = new Intent(MainActivity.this, CrawlScreen.class);
                        startActivity(crawlActivity);
                        break;
                    case 3:  Intent contactActivity = new Intent(MainActivity.this, ContactUsActivity.class);
                        startActivity(contactActivity);
                        break;


                }

            }

        });


    }
}


