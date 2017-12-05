package com.lucak.portfolio_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lucak.Database.Database;
import com.lucak.classes.Token;

public class AddToken extends AppCompatActivity implements View.OnClickListener{
    private EditText tokenName;
    private EditText tokenTicker;
    private EditText coinTicker;
    private EditText tokenAmount;
    private EditText boughtPrice;
    private Button addToken;
    private Button back;
    private Database data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_token);
        tokenName = (EditText)findViewById(R.id.tokenName);
        tokenTicker = (EditText)findViewById(R.id.tokenTicker);
        coinTicker = (EditText)findViewById(R.id.tokenCoinTicker);
        tokenAmount = (EditText) findViewById(R.id.tokenAmount);
        boughtPrice = (EditText) findViewById(R.id.tokenBoughtPrice);
        addToken = (Button)findViewById(R.id.btnAddTokenPage);
        back = (Button)findViewById(R.id.btnAddTokenBack);
        addToken.setOnClickListener(this);
        back.setOnClickListener(this);
        data = new Database(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAddTokenPage:
                Token token = new Token();
                String tokenNameText = tokenName.getText().toString().trim();
                String tokenTickerText = tokenTicker.getText().toString().trim();
                //coin ticker is currently a text field to enter but will be changed to a dropdown
                String coinTickerText = coinTicker.getText().toString().trim();
                String tokenAmountText = tokenAmount.getText().toString().trim();
                String boughtPriceText = boughtPrice.getText().toString().trim();
                String errorMessage = "";

                if (tokenNameText.equals(null) || tokenNameText.equals("") ||
                        tokenTickerText.equals(null) || tokenTickerText.equals("") ||
                        coinTickerText.equals(null) || coinTickerText.equals("") ||
                        tokenAmountText.equals(null) || tokenAmountText.equals("") ||
                        boughtPriceText.equals(null) || boughtPriceText.equals("")){
                    errorMessage += "Please fill in all fields\n";
                }
                if (errorMessage.equals("")){
                    try{
                        token.setToken_Name(tokenNameText);
                        token.setToken_Ticker(tokenTickerText);
                        token.setCoin_Id(Integer.parseInt(coinTickerText));
                        token.setToken_Amount(Double.parseDouble(tokenAmountText));
                        token.setBought_Price(Double.parseDouble(boughtPriceText));
                    }
                    catch(Exception e){
                        if (e instanceof NumberFormatException){
                            errorMessage += "Please Enter Appropriate types\n";
                        }
                    }
                }

                if (!errorMessage.equals("")){
                    Toast.makeText(this, errorMessage.substring(0, errorMessage.length() - 1), Toast.LENGTH_LONG).show();
                }
                else{
                    boolean result = data.AddToken(token);
                    if (result == true) {
                        Toast.makeText(this, "Token Added", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(this, Home.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(this, "Error: Token Not Added", Toast.LENGTH_LONG).show();
                    }
                }



                break;

            case R.id.btnAddTokenBack:
                Intent i = new Intent(this, Home.class);
                startActivity(i);
                break;

            default:
                Toast.makeText(this, "Unknown Error", Toast.LENGTH_LONG);
        }
    }
}
