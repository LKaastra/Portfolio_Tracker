package com.lucak.portfolio_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.lucak.Database.Database;
import com.lucak.Database.db;
import com.lucak.classes.User;

public class Login extends AppCompatActivity implements OnClickListener {

    private EditText userName;
    private EditText password;
    private Button login;
    private Button createAccount;
    Database data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName = (EditText)findViewById(R.id.userName);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.btnLogin);
        createAccount = (Button)findViewById(R.id.btnCreateAccount);
        login.setOnClickListener(this);
        createAccount.setOnClickListener(this);
        data = new Database(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                String userString = userName.getText().toString().trim();
                String passString = password.getText().toString().trim();
                String errorMessage = "";
                if (userString.equals(null) || userString.equals("")){
                    errorMessage += "Please enter your User Name\n";
                }
                if (passString.equals(null) || passString.equals("")){
                    errorMessage += "Please enter you Password\n";
                }

                if (!errorMessage.equals("")){
                    errorMessage = "Please create account if you havn't already\n" +
                            errorMessage.substring(0, errorMessage.length()-1);
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
                }
                else{
                    boolean loginSuccess = data.Login(new User(userString, passString));
                    if (loginSuccess == true){
                        Intent i = new Intent(this, Home.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(this , "Invalid Login", Toast.LENGTH_LONG).show();
                    }
                }
                break;

            case R.id.btnCreateAccount:
                Intent i = new Intent(this, CreateAccount.class);
                startActivity(i);
                break;
        }
    }
}
