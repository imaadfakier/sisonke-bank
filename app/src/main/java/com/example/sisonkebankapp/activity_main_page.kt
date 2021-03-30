package com.example.sisonkebankapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main_page.*

class activity_main_page : AppCompatActivity() {

    internal var dbHelper = DatabaseHelper(this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        val intent = intent
        val welcome_message: String? = intent.getStringExtra("welcome_message");
        textView6.setText(welcome_message)

        /*setContentView(R.layout.activity_login)
        Toast.makeText(
            applicationContext,
            "Welcome " + dbHelper.getUserDetails(username.text.toString()),
            Toast.LENGTH_SHORT
        ).show();*/
        val welcome_message_toast: String? = intent.getStringExtra("welcome_message_toast");
        Toast.makeText(
            applicationContext,
            welcome_message_toast,
            Toast.LENGTH_SHORT
        ).show();

        button2.setOnClickListener{
            val intent2 = Intent(this@activity_main_page, activity_view_account_balance::class.java)
            val logged_in_user: String? = intent.getStringExtra("logged_in_user");
            intent2.putExtra("logged_in_user", logged_in_user);
            /*val logged_in_user_check: String? = intent.getStringExtra("logged_in_user");
            if (logged_in_user_check != null) {
                Log.d("USER LOGGED IN STATUS: ", logged_in_user_check)
            } else {
                Log.d("USER LOGGED IN STATUS: ", "CURRENT USER IDENTITY NOT RECEIVED")
            }*/
            startActivity(intent2);
            //finish();
        }

        button3.setOnClickListener{
            val intent3 = Intent(this@activity_main_page, activity_transfer_between_accounts::class.java)
            val logged_in_user: String? = intent.getStringExtra("logged_in_user");
            intent3.putExtra("logged_in_user", logged_in_user);
            startActivity(intent3);
            //finish();
        }

        button4.setOnClickListener{
            val intent4 = Intent(this@activity_main_page, activity_login::class.java)
            startActivity(intent4);
            Toast.makeText(
            applicationContext,
            "You have been successfully logged out",
            Toast.LENGTH_SHORT
            ).show();
            finish();
        }
    }
}