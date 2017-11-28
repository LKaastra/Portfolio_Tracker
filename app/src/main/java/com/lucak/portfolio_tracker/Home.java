package com.lucak.portfolio_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lucak.Database.db;

public class Home extends AppCompatActivity implements View.OnClickListener{
    private Button addToken;
    private Button addCoin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent i = getIntent();
        addToken.setOnClickListener(this);
        addCoin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnAddCoin:

                break;
            case R.id.btnAddToken:
                
            default:
                Toast.makeText(this, "Error Occurred", Toast.LENGTH_LONG).show();
        }
    }
}
