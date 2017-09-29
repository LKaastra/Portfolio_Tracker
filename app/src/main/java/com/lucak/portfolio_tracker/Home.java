package com.lucak.portfolio_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    private TextView greeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        greeting = (TextView)findViewById(R.id.greeting);
        Intent i = getIntent();
        greeting.setText("Welcome " + i.getStringExtra(Intent.EXTRA_TEXT));
    }
}
