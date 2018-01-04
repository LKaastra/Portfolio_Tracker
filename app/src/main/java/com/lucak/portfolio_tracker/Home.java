package com.lucak.portfolio_tracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lucak.Database.Database;
import com.lucak.classes.Coin;
import com.lucak.classes.CustomAdapter;
import com.lucak.coinmarketcapAPI.data;

import java.util.ArrayList;

public class Home extends AppCompatActivity implements View.OnClickListener{
    private Button addCoin;
    private Button manageAccount;
    private ListView coinList;
    private Database data;
    private ArrayList<Coin> coinArrayList = new ArrayList<Coin>();
    private CustomAdapter coinAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        addCoin = (Button)findViewById(R.id.btnAddCoin);
        coinList = (ListView) findViewById(R.id.coinListView);
        manageAccount = findViewById(R.id.btnManageAccount);
        manageAccount.setOnClickListener(this);
        addCoin.setOnClickListener(this);
        data = new Database(this);
        coinAdapter = new CustomAdapter(this, R.layout.homearraylayout);
    }

    @Override
    public void onResume(){
        coinAdapter.addAll(data.GetAllCoins());
        coinList.setAdapter(coinAdapter);
        coinList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String symbol = ((TextView)view.findViewById(R.id.Name)).getText().toString();

                Intent intent = new Intent(Home.this, DisplayCoin.class);
                intent.putExtra("COIN", symbol);
                startActivity(intent);

            }
        });
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
            case R.id.btnManageAccount:
                Intent intent = new Intent(this, ManageAccount.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(this, "Error Occurred", Toast.LENGTH_LONG).show();
        }
    }
}
