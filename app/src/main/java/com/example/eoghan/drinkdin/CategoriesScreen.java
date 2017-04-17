package com.example.eoghan.drinkdin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Eoghan on 07/12/2016.
 */

public class CategoriesScreen extends AppCompatActivity{

    String[] menuListCat = {
            "Craft",
            "Guinness",
            "Whiskey",
            "Cocktail"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activity_list, menuListCat);

        ListView list = (ListView) findViewById(R.id.listMenuCat);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch(position){
                    case 0:  Intent phouseActivity = new Intent(CategoriesScreen.this, phouseMap.class);
                        startActivity(phouseActivity);
                        break;



                }

            }
            @SuppressWarnings("unused")
            public void onClick(View v){
            }
        });



    }
}