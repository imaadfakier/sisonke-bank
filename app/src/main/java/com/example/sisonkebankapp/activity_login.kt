package com.example.sisonkebankapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main_page.*


class activity_login : AppCompatActivity() {
    internal var dbHelper = DatabaseHelper(this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var correct_fields = 0;

        login.setOnClickListener {
            if (username.text.toString().isEmpty() && password.text.toString().isEmpty()) {
                Toast.makeText(
                    applicationContext,
                    "Please enter a username and password",
                    Toast.LENGTH_SHORT
                ).show();
                correct_fields = 0;
            } else {
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(username.text.toString()).matches()) {
                    Toast.makeText(
                        applicationContext,
                        "Please enter a valid username",
                        Toast.LENGTH_SHORT
                    ).show();
                    //correct_fields = 0;
                } else if (!dbHelper.doesUsernameExist(username.text.toString())) {
                    Toast.makeText(
                        applicationContext,
                        "Incorrect username, please try again",
                        Toast.LENGTH_SHORT
                    ).show();
                    //correct_fields = 0;
                } else {
                    correct_fields++;
                }

                if (password.text.toString().length < 5) {
                    Toast.makeText(
                        applicationContext,
                        "Minimum password length is 5",
                        Toast.LENGTH_SHORT
                    ).show();
                    //correct_fields = 0;
                } else if (!dbHelper.doesPasswordExist(password.text.toString())) {
                    Toast.makeText(
                        applicationContext,
                        "Incorrect password, please try again",
                        Toast.LENGTH_SHORT
                    ).show();
                    //correct_fields = 0;
                } else {
                    correct_fields++;
                }

                if (correct_fields < 2 || correct_fields > 2) {
                    correct_fields = 0;
                }
            }

            if (correct_fields == 2) {
                Toast.makeText(
                    applicationContext,
                    "Success! Thank you for banking with us",
                    Toast.LENGTH_SHORT
                ).show();
                val intent = Intent(this@activity_login, activity_main_page::class.java)
                //setContentView(R.layout.activity_main_page)
                val t = findViewById<TextView>(R.id.textView6)
                //t.setText("Welcome " + dbHelper.getUserDetails(username.text.toString()));
                //t.setText(getString(R.string.welcome_user, dbHelper.getUserDetails(username.text.toString())));
                intent.putExtra("welcome_message", getString(R.string.welcome_user, dbHelper.getUserDetails(username.text.toString(), true)));
                intent.putExtra("welcome_message_toast", "Welcome " + dbHelper.getUserDetails(username.text.toString(), true));
                intent.putExtra("logged_in_user", getString(R.string.currently_logged_in_user, username.text.toString()));
                /*val logged_in_user: String? = intent.getStringExtra("logged_in_user");
                if (logged_in_user != null) {
                    Log.d("USER LOGGED IN STATUS: ", logged_in_user)
                } else {
                    Log.d("USER LOGGED IN STATUS: ", "CURRENT USER IDENTITY NOT RECEIVED")
                }*/
                startActivity(intent);
                /*Toast.makeText(
                    applicationContext,
                    "Welcome " + dbHelper.getUserDetails(username.text.toString()),
                    Toast.LENGTH_SHORT
                ).show();*/
                //applicationContext.deleteDatabase(dbHelper.deleteDB());
                finish();
            }
        }

        textView2.setOnClickListener{
            val intent = Intent(this@activity_login, activity_registration::class.java)
            startActivity(intent);
            finish();
        }
    }
}