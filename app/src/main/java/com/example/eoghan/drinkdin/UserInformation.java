package com.example.eoghan.drinkdin;

/**
 * Created by Eoghan on 20/03/2017.
 */

public class UserInformation {
    public String phouseCheckIn;
    public String bar;
    public String drink;
    public String name;
    public String ratingStr;

    public UserInformation() {
        //blank Constructor
    }
    public UserInformation(String phouseCheckIn, String ratingStr ) {
        this.phouseCheckIn = phouseCheckIn;
        this.ratingStr = ratingStr;

    }
    public UserInformation(String bar, String drink, String name) {
        this.bar = bar;
        this.drink = drink;
        this.name = name;
    }

    public String getBar() {
        return bar;
    }

    public String getDrink() {
        return drink;
    }
    public String getName() {
        return name;
    }

    public String getPhouseCheckIn(){
        return phouseCheckIn;
    }

    public String getRatingStr(){
        return ratingStr;
    }


}

