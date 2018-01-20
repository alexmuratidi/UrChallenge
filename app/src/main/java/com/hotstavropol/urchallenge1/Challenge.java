package com.hotstavropol.urchallenge1;

/**
 * Created by bkb on 20.01.2018.
 */

public class Challenge {
    String title, description;

    public Challenge(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Challenge(Challenge challenge) {
        this.title = challenge.title;
        this.description = challenge.description;
    }
}
