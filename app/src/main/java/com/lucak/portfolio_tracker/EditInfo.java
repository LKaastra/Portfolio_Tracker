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

public class EditInfo extends AppCompatActivity implements View.OnClickListener{
    EditText userName;
    EditText email;
    EditText firstName;
    EditText lastName;
    Button submit;
    Database data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        userName = findViewById(R.id.editUserName);
        email = findViewById(R.id.editEmail);
        firstName = findViewById(R.id.editFirstName);
        lastName = findViewById(R.id.editLastName);
        submit = findViewById(R.id.submitButton);
        submit.setOnClickListener(this);
        data = new Database(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.submitButton:
                User user = db.myDB.loggedin;
                String userNameString = userName.getText().toString().trim();
                String emailString = email.getText().toString().trim();
                String firstNameString = firstName.getText().toString().trim();
                String lastNameString = lastName.getText().toString().trim();
                if (!userNameString.equals("")){
                    user.setUser_Name(userNameString);
                }
                if (!emailString.equals("")){
                    user.setEmail(emailString);
                }
                if (!firstNameString.equals("")){
                    user.setFirstName(firstNameString);
                }
                if (!lastNameString.equals("")){
                    user.setLastName(lastNameString);
                }
                db.myDB.loggedin = user;
                data.UpdateUser(user);
                Intent i = new Intent(this, ManageAccount.class);
                startActivity(i);

                break;
            default:
                Toast.makeText(this, "Unknown Error", Toast.LENGTH_LONG);
        }
    }
}
