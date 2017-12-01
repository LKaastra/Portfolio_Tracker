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
        addToken = (Button)findViewById(R.id.btnAddToken);
        addCoin = (Button)findViewById(R.id.btnAddCoin);
        addToken.setOnClickListener(this);
        addCoin.setOnClickListener(this);
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
