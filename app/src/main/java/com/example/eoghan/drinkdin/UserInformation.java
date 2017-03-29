package com.example.eoghan.drinkdin;

/**
 * Created by Eoghan on 20/03/2017.
 */

public class UserInformation {
    private String phouseString;
    public String bar;
    public String drink;

    public UserInformation() {

    }
    public UserInformation(String phouseString) {
        this.phouseString = phouseString;

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

}

