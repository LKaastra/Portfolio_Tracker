package com.lucak.classes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by lkaastra6885 on 12/30/2017.
 */

public class CustomAdapter extends ArrayAdapter {

    public CustomAdapter(@NonNull Context context, ArrayList<Coin> coins) {
        super(context, 0, coins);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Coin coin = (Coin) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
        }




        return super.getView(position, convertView, parent);
    }
}
