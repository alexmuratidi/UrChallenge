package com.hotstavropol.urchallenge1;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bkb on 13.01.2018.
 */

public abstract class DataBase {
    public static ArrayList <Challenge> quests = new ArrayList<Challenge>();
    public static ArrayList <Challenge> myquests = new ArrayList<Challenge>();
    public static boolean vk_permission = false;
}