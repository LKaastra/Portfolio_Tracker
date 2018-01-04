package com.lucak.portfolio_tracker;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lucak.Database.db;


public class header extends Fragment {
    TextView totalNumber;
    TextView totalPercent;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_header, container, false);
        totalNumber = v.findViewById(R.id.totalAmount);
        totalPercent = v.findViewById(R.id.percentTotal);
        totalPercent.setText(Double.toString(db.myDB.TotalPercent));
        totalNumber.setText(Double.toString(db.myDB.TotalNumber));
        return v;
    }

    @Override
    public void onResume() {
        String percentString = Double.toString(Math.round(db.myDB.TotalPercent * 1000)/ 1000d);
        if (!percentString.contains("-")){
            percentString = "+" + percentString;
            totalPercent.setTextColor(Color.parseColor("#228B22"));
        }
        else{
            totalPercent.setTextColor(Color.RED);
        }

        totalPercent.setText("1 day: " + percentString);

        totalNumber.setText("$" + Double.toString(Math.round(db.myDB.TotalNumber * 1000)/ 1000d));

        super.onResume();
    }
}
