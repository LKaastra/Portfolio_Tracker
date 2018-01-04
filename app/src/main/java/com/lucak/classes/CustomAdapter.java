package com.lucak.classes;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lucak.portfolio_tracker.R;

import java.util.ArrayList;

/**
 * Created by lkaastra6885 on 12/30/2017.
 */

public class CustomAdapter extends ArrayAdapter {


    public CustomAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Coin coin = (Coin) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.homearraylayout, parent, false);
        }

        TextView name = convertView.findViewById(R.id.Name);
        TextView holding = convertView.findViewById(R.id.holding);
        TextView coins = convertView.findViewById(R.id.coins);
        TextView oneDay = convertView.findViewById(R.id.oneDay);
        TextView sevenDay = convertView.findViewById(R.id.sevenDay);

        double coinAmount = coin.getCoin_Amount();

        double totalHoldings = Math.round(coinAmount * coin.getPriceCurrent() * 1000)/ 1000;

        name.setText(coin.getSymbol());
        holding.setText("$" + Double.toString(totalHoldings));
        coins.setText(Double.toString(coin.getCoin_Amount()));



        if (coin.getOneDayChange().contains("-")){
            oneDay.setText("1day: " + coin.getOneDayChange() + "%");
            oneDay.setTextColor(Color.RED);
        }
        else{
            oneDay.setText("1day: +" + coin.getOneDayChange() + "%");
            oneDay.setTextColor(Color.parseColor("#228B22"));
        }
        if (coin.getSevenDayChange().contains("-")){
            sevenDay.setText("7day: " + coin.getSevenDayChange() + "%");
            sevenDay.setTextColor(Color.RED);
        }
        else{
            sevenDay.setText("7day: +" + coin.getSevenDayChange() + "%");
            sevenDay.setTextColor(Color.parseColor("#228B22"));
        }



        return convertView;
    }

}
