package com.example.sisonkebankapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main_page.*
import kotlinx.android.synthetic.main.activity_view_account_balance.*

class activity_view_account_balance : AppCompatActivity() {
    internal var dbHelper = DatabaseHelper(this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_account_balance)

        val intent = intent
        val logged_in_user: String? = intent.getStringExtra("logged_in_user");
        /*if (logged_in_user != null) {
            Log.d("User logged in: ", logged_in_user)
        } else {
            Log.d("USER LOGGED IN STATUS: ", "CURRENT USER IDENTITY NOT RECEIVED")
        }*/
        if (logged_in_user != null) {
            val user_account_details: String = dbHelper.getUserDetails(logged_in_user, false)

            /****************************************************************/
            /*val currentString = "Fruit: they taste good"
            val separated = currentString.split(":".toRegex()).toTypedArray()
            separated[0] // this will contain "Fruit"
            separated[1] // this will contain " they taste good"

            separated[1] = separated[1].trim();

            val currentString2 = "Fruit: they taste good.very nice actually"
            val separated2 =
                currentString2.split("\\.".toRegex()).toTypedArray()
            separated2[0] // this will contain "Fruit: they taste good"

            separated2[1] // this will contain "very nice actually"*/
            /****************************************************************/

            val user_account_details_segments = user_account_details.split(":".toRegex()).toTypedArray()
            //Log.d("User Account Details: ", user_account_details);

            val first_name = user_account_details_segments[0];
            val last_name = user_account_details_segments[1];
            val current_account_balance = user_account_details_segments[3];
            val current_savings_account_balance = user_account_details_segments[4];

            textView10.setText(first_name);
            textView12.setText(last_name);
            textView14.setText(current_account_balance);
            textView16.setText(current_savings_account_balance);
        }/* else {
            Log.d("USER LOGGED IN STATUS: ", "CURRENT USER IDENTITY NOT RECEIVED")
        }*/
    }
}