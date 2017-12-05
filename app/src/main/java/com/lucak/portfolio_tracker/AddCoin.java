package com.lucak.portfolio_tracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lucak.Database.Database;
import com.lucak.Database.db;
import com.lucak.classes.Coin;

import java.text.ParseException;

public class AddCoin extends AppCompatActivity implements View.OnClickListener {
    private EditText coinName;
    private EditText coinTicker;
    private EditText coinAmount;
    private EditText boughtPrice;
    private Button addCoin;
    Database data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coin);
        coinName = (EditText) findViewById(R.id.coinName);
        coinTicker = (EditText) findViewById(R.id.coinTicker);
        coinAmount = (EditText) findViewById(R.id.coinAmount);
        boughtPrice = (EditText) findViewById(R.id.boughtPrice);
        addCoin = (Button) findViewById(R.id.btnAddCoinPage);
        addCoin.setOnClickListener(this);
        data = new Database(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnAddCoinPage:
                String errorMessage = "";
                Coin coin = new Coin();
                String coinNameText = coinName.getText().toString().trim();
                String coinTickerText = coinTicker.getText().toString().trim();
                String coinAmountText = coinAmount.getText().toString().trim();
                String boughtPriceText = boughtPrice.getText().toString().trim();
                if (coinNameText.equals(null) || coinNameText.equals("") ||
                        coinTickerText.equals(null) || coinTickerText.equals("") ||
                        coinAmountText.equals(null) || coinAmountText.equals("") ||
                        boughtPriceText.equals(null) || boughtPriceText.equals("")){
                    errorMessage += "Please fill in all Fields\n";
                }
                try {
                    coin.setCoin_Amount(Double.parseDouble(coinAmountText));
                    coin.setBought_Price(Double.parseDouble(boughtPriceText));
                    coin.setCoin_Name(coinNameText);
                    coin.setCoin_Ticker(coinTickerText);
                    coin.setUser_id(db.myDB.loggedin.getUser_Id());
                }
                catch(Exception e){
                    if (e instanceof NumberFormatException){
                        errorMessage += "Please enter appropriate types\n";
                    }
                    else{
                        errorMessage += "Unepected error\n";
                    }
                }


                if (!errorMessage.equals("")){
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
                }
                else{
                    boolean result = data.AddCoin(coin);
                    if (result == true) {
                        Toast.makeText(this, "Coin Added", Toast.LENGTH_LONG);
                    }
                    else{
                        Toast.makeText(this, "Coin Not Added", Toast.LENGTH_LONG);
                    }

                }



                break;
                default:
                    Toast.makeText(this, "Unknown Error", Toast.LENGTH_LONG).show();
        }
    }
}
