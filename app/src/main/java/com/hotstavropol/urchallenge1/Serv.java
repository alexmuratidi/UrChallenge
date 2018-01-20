package com.hotstavropol.urchallenge1;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by maximgran on 18.01.2018.
 */

public abstract class Serv {

    public static String Get() {
        String req = "http://draglit.hol.es/?type=getall";
        HttpURLConnection connection = null;
        String ans = "";
        try {
            connection = (HttpURLConnection)(new URL(req).openConnection());
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(2500);
            connection.setReadTimeout(2500);
            connection.connect();

            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String s = "";
                StringBuilder sb = new StringBuilder();
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append('\n');
                }
                ans = sb.toString();
            } else {
                ans = "fail: " + connection.getResponseCode() + ", " + connection.getResponseMessage();
            }
        } catch (Throwable cause) {
            cause.printStackTrace();
        } finally {
            if (connection != null)
                connection.disconnect();
        }
        return ans;
    }

    public static void PostCreate(String vkid, String vktoken, String title, String description) {
        String myUrl = "http://draglit.hol.es";
        Log.d("Serega", title);
        Log.d("Serega1", description);
        //String params = "type=create&creator_id=" + vkid + "&param3=" + vktoken + "&name=" + title + "&description=" + description;
        String params = "type=create&creator_id" + vkid + "&name=" + title + "&description=" + description;
        byte[] data = null;
        try {
            URL url = new URL(myUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Length", "" + Integer.toString(params.getBytes().length));
            OutputStream os = conn.getOutputStream();
            data = params.getBytes("UTF-8");
            os.write(data);
            conn.connect();
            if (HttpURLConnection.HTTP_OK != conn.getResponseCode()) {
                System.out.println(conn.getResponseCode() + ", " + conn.getResponseMessage());
            }
        } catch (Throwable cause) {
            //cause.printStackTrace();
        }
    }
}
