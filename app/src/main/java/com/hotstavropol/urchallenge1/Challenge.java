package com.hotstavropol.urchallenge1;

/**
 * Created by bkb on 20.01.2018.
 */

public class Challenge {
    String pid_challenge, title, description, user_id, rating, completecount, startcount;

    public Challenge(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Challenge(Challenge challenge) {
        this.title = challenge.title;
        this.description = challenge.description;
        this.pid_challenge = challenge.pid_challenge;
        this.rating = challenge.rating;
        this.completecount = challenge.completecount;
        this.user_id = challenge.user_id;
        this.startcount = challenge.startcount;
    }

    public Challenge(String pid_challenge, String title, String description, String creator_id, String rating, String completecount, String startcount) {
        this.pid_challenge = pid_challenge;
        this.title = title;
        this.description = description;
        this.user_id = creator_id;
        this.rating = rating;
        this.completecount = completecount;
        this.startcount = startcount;
    }
}