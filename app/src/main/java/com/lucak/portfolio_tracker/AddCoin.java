package com.lucak.portfolio_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.lucak.Database.Database;
import com.lucak.Database.db;
import com.lucak.classes.Coin;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class AddCoin extends AppCompatActivity implements View.OnClickListener {
    private Spinner coinName;
    private EditText coinAmount;
    private EditText boughtPrice;
    private Button addCoin;
    private Button back;
    Database data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coin);
        coinName = (Spinner) findViewById(R.id.addCoinName);
        coinAmount = (EditText) findViewById(R.id.coinAmount);
        boughtPrice = (EditText) findViewById(R.id.boughtPrice);
        addCoin = (Button) findViewById(R.id.btnAddCoinPage);
        back = (Button) findViewById(R.id.btnAddCoinBack);
        addCoin.setOnClickListener(this);
        back.setOnClickListener(this);
        data = new Database(this);
    }

    @Override
    protected void onResume() {
        ArrayList<Coin> coins = data.GetAllAllCoins();
        ArrayList<String> names = new ArrayList<String>();
        for (Coin coin : coins){
            names.add(coin.getSymbol());
        }
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, names);
        coinName.setAdapter(spinnerAdapter);

        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnAddCoinPage:
                String errorMessage = "";
                Coin coin = new Coin();
                String coinNameText = String.valueOf(coinName.getSelectedItem());
                String coinAmountText = coinAmount.getText().toString().trim();
                String boughtPriceText = boughtPrice.getText().toString().trim();
                if (coinNameText.equals(null) || coinNameText.equals("") ||
                        coinAmountText.equals(null) || coinAmountText.equals("") ||
                        boughtPriceText.equals(null) || boughtPriceText.equals("")){
                    errorMessage += "Please fill in all Fields\n";
                }
                if (errorMessage.equals("")) {
                    try {

                        coin.setCoin_Amount(Double.parseDouble(coinAmountText));
                        coin.setPriceBought(Double.parseDouble(boughtPriceText));
                        coin.setSymbol(coinNameText);
                        Coin dataCoin = data.GetSingleAllCoins(coin.getSymbol());
                        coin.setId(dataCoin.getId());
                        coin.setCoin_Name(dataCoin.getCoin_Name());
                        coin.setUser_id(db.myDB.loggedin.getUser_Id());
                    } catch (Exception e) {
                        if (e instanceof NumberFormatException) {
                            errorMessage += "Please enter appropriate types\n";
                        } else {
                            errorMessage += "Unexpected error\n";
                        }
                    }
                }

                if (!errorMessage.equals("")){
                    Toast.makeText(this, errorMessage.substring(0, errorMessage.length() - 1), Toast.LENGTH_LONG).show();
                }
                else{
                    boolean result = data.AddCoin(coin);
                    if (result == true) {
                        Toast.makeText(this, "Coin Added", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(this, Home.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(this, "Error: Coin Not Added", Toast.LENGTH_LONG).show();
                    }

                }
                break;
            case R.id.btnAddCoinBack:
                Intent i = new Intent(this, Home.class);
                startActivity(i);
                break;

                default:
                    Toast.makeText(this, "Unknown Error", Toast.LENGTH_LONG).show();
        }
    }
}
