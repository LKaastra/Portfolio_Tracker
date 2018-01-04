package com.lucak.portfolio_tracker;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lucak.Database.Database;
import com.lucak.classes.Coin;

public class DisplayCoin extends AppCompatActivity implements View.OnClickListener{
    private TextView coinName;
    private TextView coinAmount;
    private TextView boughtPrice;
    private TextView currentPrice;
    private TextView oneDayChange;
    private TextView sevenDayChange;
    private Button coinMarketCap;
    private Database data;
    private Coin myCoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_coin);
        coinName = findViewById(R.id.CoinName);
        coinAmount = findViewById(R.id.disCoinAmount);
        boughtPrice = findViewById(R.id.disBoughtPrice);
        currentPrice = findViewById(R.id.disCurrentPrice);
        oneDayChange = findViewById(R.id.disOneday);
        sevenDayChange = findViewById(R.id.disSevenday);
        coinMarketCap = findViewById(R.id.coinMarketCap);
        coinMarketCap.setOnClickListener(this);
        data = new Database(this);


    }

    @Override
    protected void onResume() {
        String symbol = getIntent().getStringExtra("COIN");
        Coin coin = data.GetSingleCoin(symbol);
        myCoin = coin;
        coinName.setText(coin.getCoin_Name() + "(" + coin.getSymbol() + ")");
        coinAmount.setText(Double.toString(coin.getCoin_Amount()));
        boughtPrice.setText(Double.toString(coin.getPriceBought()));
        currentPrice.setText(Double.toString(coin.getPriceCurrent()));
        oneDayChange.setText(coin.getOneDayChange());
        sevenDayChange.setText(coin.getSevenDayChange());

        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.coinMarketCap:
                String uri = "https://coinmarketcap.com/currencies/" + myCoin.getId() + "/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(uri));
                startActivity(i);
                break;
            default:
                Toast.makeText(this, "Error Occurred", Toast.LENGTH_LONG).show();
        }

    }
}
