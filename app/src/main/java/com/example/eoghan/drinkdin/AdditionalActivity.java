package com.example.eoghan.drinkdin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AdditionalActivity extends AppCompatActivity {

    String[] menuList = {
            "Plan My Crawl",
            "Pubs Near Me"

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listmain, menuList);

        ListView list = (ListView) findViewById(R.id.listMenu);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch(position){
                    case 0:  Intent catActivity = new Intent(AdditionalActivity.this, CrawlScreen.class);
                        startActivity(catActivity);
                        break;
                    case 1:  Intent userActivity = new Intent(AdditionalActivity.this, NearMeActivity.class);
                        startActivity(userActivity);
                        break;




                }

            }

        });


    }
}
