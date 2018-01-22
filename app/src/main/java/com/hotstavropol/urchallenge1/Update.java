package com.hotstavropol.urchallenge1;

import android.provider.ContactsContract;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bkb on 21.01.2018.
 */

public class Update {
    public static void update() {
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
                Challenge t;
                DataBase.quests.clear();
                for (int i = 0; i < tmp.size(); i++)
                    DataBase.quests.add(new Challenge(tmp.get(i).pid_challenge, tmp.get(i).title, tmp.get(i).description, tmp.get(i).user_id, tmp.get(i).rating, tmp.get(i).completecount, tmp.get(i).startcount));
                //Log.d("msg", DataBase.quests.size() + "");
                //Log.d("msg", "" + DataBase.quests.size());
                //Toast.makeText(getContext(), "ok", Toast.LENGTH_SH-ORT).show();
                if (VK.vkAccessToken != null) {
                    Log.d("id: ", VK.vkAccessToken.userId + "");
                    //Log.d("realid: ", DataBase.quests.get(DataBase.quests.size() - 1).creator_id + "");
                    DataBase.myquests.clear();
                    for (int i = 0; i < DataBase.quests.size(); i++)
                        if (Objects.equals(DataBase.quests.get(i).user_id, VK.vkAccessToken.userId))
                            DataBase.myquests.add(new Challenge(DataBase.quests.get(i)));
                    ((BaseAdapter)MainActivity.desk2.getListView().getAdapter()).notifyDataSetChanged();
                }
                Log.d("size quests: ", "" + DataBase.quests.size());
                Log.d("size myquests: ", "" + DataBase.myquests.size());
                //for (int i = 0; i < DataBase.quests.size(); i++)
                //    Log.d("msg: ", DataBase.quests.get(i).pid_challenge + " " + DataBase.quests.get(i).creator_id + " " + DataBase.quests.get(i).completecount + " " + DataBase.quests.get(i).startcount);
            }

            @Override
            public void onFailure(Call<List<Challenge>> call, Throwable t) {
                //Toast.makeText(getContext(), "error :(", Toast.LENGTH_SHORT).show();
            }
        });
        //Log.d("size quests: ", "" + DataBase.quests.size());
        //Log.d("size myquests: ", "" + DataBase.myquests.size());
    }
}
