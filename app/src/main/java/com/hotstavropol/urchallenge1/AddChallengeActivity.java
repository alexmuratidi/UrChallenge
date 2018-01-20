package com.hotstavropol.urchallenge1;

/**
 * Created by maximgran on 15.01.2018.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vk.sdk.api.VKApi;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddChallengeActivity extends AppCompatActivity {
    private final String url = "http://draglit.hol.es";
    private Gson gson = new GsonBuilder().create();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    private Link intf = retrofit.create(Link.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_challenge_button);

        final Intent intent = getIntent();
        EditText editTextName = findViewById(R.id.add_challenge_name_editText);
        editTextName.setText(intent.getStringExtra("Name"));

        EditText editTextDescription = findViewById(R.id.add_challenge_description_editText);
        editTextDescription.setText(intent.getStringExtra("Description"));

        Button button = findViewById(R.id.add_challenge_confirm_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView NameTextView = findViewById(R.id.add_challenge_name_editText);
                TextView ChallengeTextView = findViewById(R.id.add_challenge_description_editText);
                String challengeName = NameTextView.getText().toString();
                String challengeDescription = ChallengeTextView.getText().toString();

                if (challengeName.equals("")) {
                    Toast.makeText(getApplicationContext(), "Название пустое!", Toast.LENGTH_LONG).show();
                    return;
                } else if (challengeDescription.equals("")) {
                    Toast.makeText(getApplicationContext(), "Описание пустое!", Toast.LENGTH_LONG).show();
                    return;
                }

                if (VK.vkAccessToken == null) {
                    Toast.makeText(getApplicationContext(), "Авторизуйтесь через ВКонтакте", Toast.LENGTH_LONG).show();
                    return;
                }

                String s = "all is bad";
                Map<String, String> map = new HashMap<String, String>();
                map.put("type", "create");
                map.put("creator_id", VK.vkAccessToken.userId);
                map.put("name", challengeName);
                map.put("description", challengeDescription);

                final Call<String> call = intf.post(map);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            call.execute();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                DataBase.quests.add(new Challenge(challengeName, challengeDescription));
                boolean f = true;
                String msg;

                if (f) {
                    msg = "Добавлено";
                } else {
                    msg = "Что-то пошло не так. Предагаю обвинить разработчиков";
                }

                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}