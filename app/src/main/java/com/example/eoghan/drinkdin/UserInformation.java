package com.example.eoghan.drinkdin;

/**
 * Created by Eoghan on 20/03/2017.
 */

public class UserInformation {
    public String phouseCheckIn;
    public String bar;
    public String drink;

    public UserInformation() {
        //blank Constructor
    }
    public UserInformation(String phouseCheckIn) {
        this.phouseCheckIn = phouseCheckIn;

    }
    public UserInformation(String bar, String drink) {
        this.bar = bar;
        this.drink = drink;
    }

    public String getBar() {
        return bar;
    }

    public String getDrink() {
        return drink;
    }

    public String getPhouseCheckIn(){
        return phouseCheckIn;
    }


}

