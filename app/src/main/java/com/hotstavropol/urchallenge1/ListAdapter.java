package com.hotstavropol.urchallenge1;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;
import com.hotstavropol.urchallenge1.MainActivity;

/**
 * Created by bkb on 13.01.2018.
 */

public class ListAdapter extends BaseAdapter{

    private Fragment fragmet;

    public ListAdapter(Fragment fragmet) {
        this.fragmet = fragmet;
    }

    @Override
    public int getCount() {
        return DataBase.quests.size();
    }

    @Override
    public Object getItem(int i) {
        return DataBase.quests.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chalenge_layout, viewGroup, false);
        ((TextView)view.findViewById(R.id.title_by_user)).setText(DataBase.quests.get(i).title);
        //Log.d("MESSAGE", (String) ((TextView)view.findViewById(R.id.title_by_user)).getText());
        ((TextView)view.findViewById(R.id.description_by_user)).setText(DataBase.quests.get(i).description);

        final String name = (String)((TextView)view.findViewById(R.id.title_by_user)).getText();
        final String description = (String)((TextView)view.findViewById(R.id.description_by_user)).getText();
        Button editbutton = view.findViewById(R.id.edit_challenge_info);
        editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fragmet.getActivity(), AddChallengeActivity.class);
                intent.putExtra("Name", name);
                intent.putExtra("Description", description);
                fragmet.startActivity(intent);
            }
        });
        return view;
    }
}