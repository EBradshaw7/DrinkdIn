package com.example.eoghan.drinkdin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ContactUsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        Button sendBtn = (Button) findViewById(R.id.sendEmail);
        sendBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        sendMail();
    }

    private void sendMail() {
        String[] TO = {"eoghan.bradshaw@gmail.com"};
        Intent emailMSG = new Intent(Intent.ACTION_SEND);

        emailMSG.setData(Uri.parse("mailto:"));
        emailMSG.setType("text/plain");
        emailMSG.putExtra(Intent.EXTRA_EMAIL, TO);
        emailMSG.putExtra(Intent.EXTRA_SUBJECT, "DrinkdIn, Contact Us");
        emailMSG.putExtra(Intent.EXTRA_TEXT, "Please let us know of any problems \n " +
                "Should you wish to suggest a pub, please include Category and Location");

        try {
            startActivity(Intent.createChooser(emailMSG, "Send mail..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ContactUsActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
        }
    }

