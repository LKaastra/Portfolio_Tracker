package com.lucak.portfolio_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.lucak.Database.Database;
import com.lucak.classes.Coin;
import com.lucak.classes.CustomAdapter;
import com.lucak.coinmarketcapAPI.data;

import java.util.ArrayList;

public class Home extends AppCompatActivity implements View.OnClickListener{
    private Button addCoin;
    private ListView coinList;
    private Database data;
    private ArrayList<Coin> coinArrayList = new ArrayList<Coin>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent i = getIntent();
        addCoin = (Button)findViewById(R.id.btnAddCoin);
        coinList = (ListView) findViewById(R.id.coinListView);
        addCoin.setOnClickListener(this);
        data = new Database(this);
    }

    @Override
    public void onResume(){

        CustomAdapter coinAdapter = new CustomAdapter(this, R.layout.homearraylayout);
        coinAdapter.addAll(data.GetAllCoins());
        coinList.setAdapter(coinAdapter);
        data mydata = new data(this, coinAdapter, coinList);
        mydata.execute();


        super.onResume();

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnAddCoin:
                Intent i = new Intent(this, AddCoin.class);
                startActivity(i);
                break;
            default:
                Toast.makeText(this, "Error Occurred", Toast.LENGTH_LONG).show();
        }
    }
}
