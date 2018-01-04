package com.lucak.portfolio_tracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lucak.Database.db;

public class ManageAccount extends AppCompatActivity implements View.OnClickListener{
    private TextView userName;
    private TextView email;
    private TextView firstName;
    private TextView lastName;
    private Button editInfo;
    private Button emailMe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account);
        userName = findViewById(R.id.displayUserName);
        email = findViewById(R.id.displayEmail);
        firstName = findViewById(R.id.txtFirstName);
        lastName = findViewById(R.id.txtLastName);
        editInfo = findViewById(R.id.editInfo);
        emailMe = findViewById(R.id.emailMe);
        editInfo.setOnClickListener(this);
        emailMe.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        userName.setText(db.myDB.loggedin.getUser_Name());
        email.setText(db.myDB.loggedin.getEmail());
        firstName.setText(db.myDB.loggedin.getFirstName());
        lastName.setText(db.myDB.loggedin.getLastName());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.editInfo:
                Intent i = new Intent(this, EditInfo.class);
                startActivity(i);
                break;
            case R.id.emailMe:
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "lucakaas@gmail.com", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Suggestion");

                startActivity(Intent.createChooser(intent, "Send Email"));
                break;
            default:
                Toast.makeText(this, "Unknown Error", Toast.LENGTH_LONG);

        }
    }
}
