package com.lucak.portfolio_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lucak.Database.Database;
import com.lucak.Database.db;
import com.lucak.classes.User;

public class CreateAccount extends AppCompatActivity implements View.OnClickListener{
    private EditText userName;
    private EditText password;
    private EditText confirmPassword;
    private EditText email;
    private Button createAccount;
    Database data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        userName = (EditText)findViewById(R.id.userNameCreate);
        password = (EditText)findViewById(R.id.passCreate);
        confirmPassword = (EditText)findViewById(R.id.passCreateConfirm);
        email = (EditText)findViewById(R.id.email);
        createAccount = (Button)findViewById(R.id.btnCreateAccountCreate);
        createAccount.setOnClickListener(this);
        data = db.myDB.instance;
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnCreateAccountCreate:
                String userNameString = userName.getText().toString().trim();
                String passwordString = password.getText().toString().trim();
                String confirmPassString = confirmPassword.getText().toString().trim();
                String emailString = email.getText().toString().trim();
                String errorMessage = "";

                if (userNameString.equals(null) || userNameString.equals("")
                        || passwordString.equals(null) || passwordString.equals("")
                        || confirmPassString.equals(null) || confirmPassString.equals("")
                        || emailString.equals(null) || emailString.equals("")){
                    errorMessage += "Please fill in all fields\n";
                }
                if (!passwordString.equals(confirmPassString)){
                    errorMessage += "Passwords do not match\n";
                }
                if (passwordString.length() < 7){
                    errorMessage += "Password must be 8 characters in length\n";
                }
                if (!errorMessage.equals("")){
                    Toast.makeText(this, errorMessage.substring(0, errorMessage.length()-1), Toast.LENGTH_LONG).show();
                }
                else{
                    User user = new User(userNameString, passwordString, emailString);
                    boolean result = data.AddUser(user);
                    if (result == true) {
                        Toast.makeText(this, "User Created", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(this, Login.class);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(this, "Error Creating User", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            default:
                Toast.makeText(this, "Error Occurred", Toast.LENGTH_LONG).show();

        }
    }
}
