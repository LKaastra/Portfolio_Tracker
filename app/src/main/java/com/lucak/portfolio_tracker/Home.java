package com.lucak.portfolio_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lucak.Database.Database;
import com.lucak.Database.db;
import com.lucak.classes.Coin;
import com.lucak.classes.Token;

public class Home extends AppCompatActivity implements View.OnClickListener{
    private Button addToken;
    private Button addCoin;
    private ListView tokenList;
    private ListView coinList;
    private Database data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent i = getIntent();
        addToken = (Button)findViewById(R.id.btnAddToken);
        addCoin = (Button)findViewById(R.id.btnAddCoin);
        tokenList = (ListView) findViewById(R.id.tokenListView);
        coinList = (ListView) findViewById(R.id.coinListView);
        addToken.setOnClickListener(this);
        addCoin.setOnClickListener(this);
        data = new Database(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        ArrayAdapter<Coin> coinAdapter = new ArrayAdapter<Coin>(this,android.R.layout.simple_list_item_1, data.GetAllCoins());
        coinList.setAdapter(coinAdapter);
        ArrayAdapter<Token> tokenAdapter = new ArrayAdapter<Token>(this,android.R.layout.simple_list_item_1, data.GetAllTokens());
        tokenList.setAdapter(tokenAdapter);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnAddCoin:
                Intent i = new Intent(this, AddCoin.class);
                startActivity(i);
                break;
            case R.id.btnAddToken:
                i = new Intent(this, AddToken.class);
                startActivity(i);
                break;
            default:
                Toast.makeText(this, "Error Occurred", Toast.LENGTH_LONG).show();
        }
    }
}
