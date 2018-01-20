package com.hotstavropol.urchallenge1;

import android.util.Log;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by bkb on 18.01.2018.
 */

public interface Link {

    @FormUrlEncoded
    @POST("http://draglit.hol.es")
    Call<String> post(@FieldMap Map<String, String> map);

    @GET("./")
    Call<List<Challenge>> get(@Query("type") String s);
}