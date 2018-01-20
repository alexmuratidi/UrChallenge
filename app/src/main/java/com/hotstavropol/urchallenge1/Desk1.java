package com.hotstavropol.urchallenge1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ThemedSpinnerAdapter;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Desk1 extends Fragment {



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((ListView)view.findViewById(R.id.listView_desk1)).setAdapter(new ListAdapter(this));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Retrofit.Builder builder = new Retrofit.Builder().
                baseUrl("http://draglit.hol.es").
                addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        Link getter = retrofit.create(Link.class);
        Call<List<Challenge>> call = getter.get("getall");
        call.enqueue(new Callback<List<Challenge>>() {
            @Override
            public void onResponse(Call<List<Challenge>> call, retrofit2.Response<List<Challenge>> response) {
                List<Challenge> tmp = response.body();
                Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Challenge>> call, Throwable t) {
                Toast.makeText(getContext(), "error :(", Toast.LENGTH_SHORT).show();
            }
        });

        return inflater.inflate(R.layout.desk1, container, false);
    }

}
